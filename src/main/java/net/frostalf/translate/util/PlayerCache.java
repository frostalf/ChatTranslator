
package net.frostalf.translate.util;

import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public class PlayerCache {
    
    private String languageCode;
    private String playerName;
    private boolean enabled;
    
    public PlayerCache (Player player, String languageCode, boolean enabled) {
        this.playerName = player.getName();
        this.languageCode = languageCode;
        this.enabled = enabled;
    }
    
    public String getLanguageCode() {
        return this.languageCode;
    }
    
    public String getPlayerCacheName() {
        return this.playerName;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }

}
