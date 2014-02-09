
package net.frostalf.translate.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.frostalf.translate.Translate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Frostalf
 */
public class ConfigManager {

    private Translate plugin;
    
    public ConfigManager (Translate plugin) {
        this.plugin = plugin;
    }  
     
    FileConfiguration config;
    File cfile;
     
    public void setup() {
        config = plugin.getConfig();
        cfile = new File(plugin.getDataFolder(), "config.yml");
        
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch (IOException ex) {
                plugin.getServer().getLogger().log(Level.SEVERE, "{0}Could not create config file!", ChatColor.RED);
            }
        }
        try {
            config.load(cfile);
        } catch (FileNotFoundException ex) {
            plugin.getServer().getLogger().log(Level.SEVERE, "{0}Config file not found!", ChatColor.RED);
        } catch (InvalidConfigurationException ex) {
            plugin.getServer().getLogger().log(Level.SEVERE, "{0}Config file is invalid!", ChatColor.RED);
        } catch (IOException ex) {
            plugin.getServer().getLogger().log(Level.SEVERE, "{0}Could not load config file!", ChatColor.RED);
        }
        
        if (config.get("Google-API-Key", null) == null) {
            config.set("Google-API-Key", "Put-Google-API-Key-HERE");
            config.set("Server-Language", "EN");
            try {
                config.save(cfile);
            } catch (IOException e) {
                plugin.getServer().getLogger().log(Level.SEVERE, "{0}Could not save config.yml", ChatColor.RED);
            }
        }
    }
    
}
