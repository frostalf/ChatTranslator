
package net.frostalf.translate.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Frostalf
 * 
 * This code setup is based on Gravity's Updater code that uses JSON.
 */
public class GoogleAPI {
    
    private Plugin plugin;
    private URL url;
    //Google API key
    private String apiKey = null;
    private String message = null;
    private String translatedMessage = null;
    private Player player;
    private List<Player> players = new ArrayList<Player>();
    private List<String> playerNames = new ArrayList<String>();
    private Thread thread;
    private static final String HOST = "https://www.googleapis.com";
    private static final String QUERY = "/language/translate/v2?key=";
    private String languageSourceCode = null;
    private String languageTranslateTo = null;
    private static final String SOURCE_MESSAGE = "&source=en&target=de&callback=translateText&q=";
    private YamlConfiguration config;
    
    
    public GoogleAPI (Plugin plugin, String message, Player player, List<Player> players) {
        this.plugin = plugin;
        this.message = message;
        this.player = player;
        this.players = players;
        
        for (int i = 0; i < players.size(); i++) {
            Player playerList = players.get(i);
            this.playerNames.add(playerList.getName());
        }
        this.apiKey = plugin.getConfig().getString("Google-API-Key");
        this.message = SOURCE_MESSAGE + message;
        
    }

}
