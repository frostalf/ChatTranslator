
package net.frostalf.translate.util;

import org.bukkit.conversations.ConversationContext;
import net.frostalf.translate.Translate;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public class Conversation {
    
    private Translate plugin;
    private ConversationFactory factory;
    private String playerName;
    private boolean enabled;
    private String languageCode;
    private Player player;
    
    public Conversation (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void addPlayerDB(Player player, String playerName) {
        this.player = player;
        this.playerName = player.getName();
        this.enabled = false;
        this.languageCode = null;
        this.factory = new ConversationFactory(plugin).withModality(true).withFirstPrompt(new Welcome()).withEscapeSequence("/quit").withTimeout(120).thatExcludesNonPlayersWithMessage("Must be a Player!");
        factory.buildConversation(player).begin();
        
    }
    
    private class Welcome extends MessagePrompt {
        
        @Override
        public String getPromptText(ConversationContext context) {
            context.setSessionData("playerName", playerName);
            return ChatColor.translateAlternateColorCodes('&', "&2This Server uses a Language translator.\nThese next questions will guide you through asking which language preference you prefer\nand whether or not you want it enabled/disabled.\nYou can disable/enable later as well.");
        }
        
        @Override
        protected Prompt getNextPrompt(ConversationContext context) {
            return new chooseLanguage();
        }
        
    }
    
    private class chooseLanguage extends StringPrompt {
        @Override
        public String getPromptText(ConversationContext context) {
            return ChatColor.translateAlternateColorCodes('&', "&8>&7First, set your two character language code.\n&7Type a 2 character language code to continue!");
        }

        @Override
        public Prompt acceptInput(ConversationContext context, String input) {
            if (input.length() == 2) {
                languageCode = input.toUpperCase();
                context.setSessionData("languagecode", languageCode);
                return new enable();
            } else {
                return new chooseLanguage();
            }
        }
    }
    
    private class enable extends StringPrompt {
        
        @Override
        public String getPromptText(ConversationContext context) {
            return ChatColor.translateAlternateColorCodes('&', "&8>&7Would you like to enable the translator?\n&7Type yes to enable or no to not enalbe.\nYou can enable translator later with a command or disable");
        }
        
        @Override
        public Prompt acceptInput(ConversationContext context, String input) {
            if(input.equalsIgnoreCase("yes")){
                enabled = true;
                context.setSessionData("enabled", enabled);
                return new endMessage();
            } else if(input.equalsIgnoreCase("no")) {
                enabled = false;
                context.setSessionData("enabled", enabled);
                return new endMessage();
            }
            return new enable();
        }
        
    }
    
    private class endMessage extends StringPrompt {
        
        @Override
        public String getPromptText(ConversationContext context) {
            return ChatColor.translateAlternateColorCodes('&', "&2Is this correct?\nLanguage Code: " + context.getSessionData("languagecode").toString() + "\nEnabled: " + context.getSessionData("enabled").toString());
        }
        
        @Override
        public Prompt acceptInput (ConversationContext context, String input) {
            if(input.equalsIgnoreCase("yes")) {
                return new finishMessage();
            } else if (input.equalsIgnoreCase("no")) {
                return new chooseLanguage();
            }
            return new endMessage();
        }
    }
    
    private class finishMessage extends MessagePrompt {
        
        @Override
        public String getPromptText(ConversationContext context) {
            return ChatColor.translateAlternateColorCodes('&', "&2Thank You!\nEnjoy translated messages if you chose to enable it!");
        }
        
        @Override
        public Prompt getNextPrompt(ConversationContext context) {
            addPlayerInfo(player, playerName, languageCode, enabled);
            return END_OF_CONVERSATION;
        }
    }
    
    public void addPlayerInfo(Player player, String playerName, String languageCode, boolean enabled) {
        plugin.addPlayertoCache(player, languageCode, enabled);
    }

}
