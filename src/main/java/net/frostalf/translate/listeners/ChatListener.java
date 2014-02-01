
package net.frostalf.translate.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.frostalf.translate.Translate;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Frostalf
 */
public class ChatListener implements Listener {

    Translate plugin;
    //private HashMap<Player, String> playerRecipients = new HashMap<Player, String>();
    
    public ChatListener (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String sourceMesage = event.getMessage();
        Set<Player> playerSet = event.getRecipients();
        List<Player> playersList = new ArrayList<Player>(playerSet);
        
        
        for (int i = 0; i < playersList.size(); i++) {
            Player playerRecipient = playersList.get(i);
            String playerNames = playerRecipient.getName();
        }
    }
    
    
}
