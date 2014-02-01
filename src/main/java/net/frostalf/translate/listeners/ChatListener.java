
package net.frostalf.translate.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.frostalf.translate.Translate;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Frostalf
 */
public class ChatListener implements Listener {

    Translate plugin;
    
    public ChatListener (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String sourceMesage = event.getMessage();
        Set<Player> playerRecipients = event.getRecipients();
        List<Player> playersList = new ArrayList<Player>(playerRecipients);
        
    }
    
}
