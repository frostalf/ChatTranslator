
package net.frostalf.translate.config;

import net.frostalf.translate.Translate;

/**
 *
 * @author Frostalf
 */
public class ConfigManager {

    Translate plugin;
    
    private static ConfigManager instance = new ConfigManager();
    
    public static ConfigManager getInstance() {
        return instance;
    }
}
