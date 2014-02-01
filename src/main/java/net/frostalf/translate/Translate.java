
package net.frostalf.translate;

import java.io.File;
import java.util.logging.Level;
import net.frostalf.translate.updater.Updater;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Frostalf
 */
public class Translate extends JavaPlugin {

    static boolean UPDATE;
    static String NEWVERSION;
    
    @Override
    public void onEnable() {
        this.checkUpdate();
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
}
