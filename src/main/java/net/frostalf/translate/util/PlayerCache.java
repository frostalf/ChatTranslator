
package net.frostalf.translate.util;

import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public class PlayerCache {
    
    private String languageCode;
    private String playerName;
    
    public PlayerCache (Player player, String languageCode) {
        this.playerName = player.getName();
        this.languageCode = languageCode;
    }
    
    public String getLanguageCode() {
        return this.languageCode;
    }
    
    public String getPlayerCacheName() {
        return this.playerName;
    }

}
