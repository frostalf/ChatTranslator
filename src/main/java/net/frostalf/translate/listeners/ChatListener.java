
package net.frostalf.translate.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.frostalf.translate.Translate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Frostalf
 */
public class ChatListener implements Listener {

    private Translate plugin;
    //private HashMap<Player, String> playerRecipients = new HashMap<Player, String>();
    
    public ChatListener (Translate plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        String sourceMesage = event.getMessage();
        Set<Player> playerSet = event.getRecipients();
        List<Player> playersList = new ArrayList<>(playerSet);
        
        
        for (int i = 0; i < playersList.size(); i++) {
            Player playerRecipient = playersList.get(i);
            String playerNames = playerRecipient.getName();
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.addPlayertoCache(player);
    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.removePlayerfromCache(player);
    }
    
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        plugin.removePlayerfromCache(player);
    }    
}
