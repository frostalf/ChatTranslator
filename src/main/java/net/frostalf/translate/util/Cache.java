
package net.frostalf.translate.util;

import java.util.HashMap;
import net.frostalf.translate.Translate;
import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public class Cache {

    private final Translate plugin;
    private final HashMap<String, PlayerCache> playerCacheMap = new HashMap<String, PlayerCache>();
    
    public Cache (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void cachePlayerInfo() {
        
    }
    
    public void addPlayertoCache(Player player) {
        String languageCode = "";
        String playerName = player.getName();
        PlayerCache playerCache = new PlayerCache(player, languageCode);
        this.playerCacheMap.put(playerName, playerCache);
    }
    
    public void removePlayerfromCache(Player player) {
        this.playerCacheMap.remove(player.getName());
    }
    
    public String getPlayerName(Player player) {
        return this.playerCacheMap.get(player.getName()).getPlayerCacheName();
    }
}
