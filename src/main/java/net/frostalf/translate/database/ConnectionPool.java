
package net.frostalf.translate.database;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import net.frostalf.translate.Translate;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Frostalf
 */
public class ConnectionPool {
    
    private Translate plugin;
    private BoneCPConfig config = null;
    private BoneCP connectionPool = null;
    private Connection connection = null;    
    
    public ConnectionPool (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void createConnectionPool() {
        PluginManager pm = plugin.getServer().getPluginManager();        
        try {
        Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not load Database Driver!");
            pm.disablePlugin(plugin);
        }
        
        this.config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + File.separator + "translate.db"); 
        config.setUsername(plugin.getConfig().getString("username")); 
        config.setPassword(plugin.getConfig().getString("password"));
        config.setMinConnectionsPerPartition(5);
        config.setMaxConnectionsPerPartition(10);
        config.setPartitionCount(1);
        try {
            this.connectionPool = new BoneCP(config); // setup the connection pool
            this.connection = this.connectionPool.getAsyncConnection().get();
        } catch (SQLException | InterruptedException | ExecutionException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not create connection pool");
        }       
    }
    
    public boolean getConnection() {
        return this.connection != null;
    }
    
    public void openConnection() {
        try {
            this.connection = this.connectionPool.getAsyncConnection().get();
        } catch (InterruptedException | ExecutionException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not open connection to database translate.db");
        }
    }
    
    public void closeConnections() {
        try {
            this.connection.close();
            this.connectionPool.shutdown();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not close database connections!");
        }
    }
    
    public Connection connection() {
        return this.connection;
    }

}
