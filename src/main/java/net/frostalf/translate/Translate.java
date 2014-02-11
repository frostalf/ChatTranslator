
package net.frostalf.translate;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import net.frostalf.translate.updater.Updater;
import net.frostalf.translate.util.DBStorage;
import net.frostalf.translate.util.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class Translate extends JavaPlugin {

    static boolean UPDATE;
    static String NEWVERSION;
    private HashMap<String, PlayerCache> playerCacheMap = new HashMap<String, PlayerCache>();
    private DBStorage dbStorage = new DBStorage();
    
    @Override
    public void onEnable() {
        this.checkUpdate();
        dbStorage.DBStorage(this);
    }
    @Override
    public void onDisable() {
        
    }
    
    private void checkUpdate() {
        if (getConfig().get("auto-update") == null) {
            getConfig().set("auto-update", true);
            saveConfig();
        }
        if (getConfig().getBoolean("auto-update")) {
            final Translate plugin = this;
            final File file = this.getFile();
            final Updater.UpdateType updateType = Updater.UpdateType.DEFAULT;
            getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
                @Override
                public void run() {
                    Updater updater = new Updater(plugin, 69090, file, updateType, false);
                    Translate.UPDATE = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
                    Translate.NEWVERSION = updater.getLatestName();
                    if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
                        getLogger().log(Level.INFO, "Successfully updated ServerTutorial to version {0} for next restart!", updater.getLatestName());
                    }
                }
            });
        }
    }
    
    public void Cache() {
    }

    public void addPlayertoCache(Player player) {
        String languageCode = "";
        String playerName = player.getName();
        boolean enabled = true;
        PlayerCache playerCache = new PlayerCache(player, languageCode, enabled);
        this.playerCacheMap.put(playerName, playerCache);
    }
    
    public void removePlayerfromCache(Player player) {
        this.playerCacheMap.remove(player.getName());
    }

    public String getPlayerName(Player player) {
        return this.playerCacheMap.get(player.getName()).getPlayerCacheName();
    }
    
    public boolean isEnabled(Player player) {
        return this.playerCacheMap.get(player.getName()).isEnabled();
    }
    
    public String getLanguageCode(Player player) {
        return this.playerCacheMap.get(player.getName()).getLanguageCode();
    }
}
