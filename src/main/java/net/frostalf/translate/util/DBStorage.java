
package net.frostalf.translate.util;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import net.frostalf.translate.Translate;
import org.bukkit.plugin.PluginManager;
/**
 *
 * @author Frostalf
 */
public class DBStorage {

    private Translate plugin;
    private BoneCP connectionPool = null;
    private Connection connection = null;
    public void DBStorage (Translate plugin) {
        this.plugin = plugin;
        PluginManager pm = plugin.getServer().getPluginManager();
        try {
        Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not load Database Driver!");
            pm.disablePlugin(plugin);
        }
// setup the connection pool

        BoneCPConfig config = new BoneCPConfig();
// jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
        config.setJdbcUrl("jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + File.separator + "translate.db"); 
        config.setUsername("sa"); 
        config.setPassword("password");
        config.setMinConnectionsPerPartition(5);
        config.setMaxConnectionsPerPartition(10);
        config.setPartitionCount(1);

        try {
            connectionPool = new BoneCP(config); // setup the connection pool
            connection = connectionPool.getAsyncConnection().get(); // fetch a connection
        } catch (SQLException | InterruptedException | ExecutionException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not load Database Driver!");
        }

        if (connection != null){
            
            String makeTable = "CREATE TABLE translate(id int primary key NOT NULL, playername text NOT NULL, languagecode text NOT NULL, enabled boolean NOT NULL)";
            String insertTable = "INSERT INTO translate VALUES(1,'frostalf','en',1)";
    // do something with the connection.
            try {
                    Statement stmt = connection.createStatement();
                    stmt.setQueryTimeout(30);
                    stmt.executeUpdate(makeTable);
                    stmt.executeUpdate(insertTable);
                    stmt.close();
                    connection.close();
                } catch (SQLException ignored) {}
        }
                        
        //connection.close(); // close the connection
        //connectionPool.shutdown(); // shutdown connection pool.        
    }
}
