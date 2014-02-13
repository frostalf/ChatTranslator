
package net.frostalf.translate.database;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import net.frostalf.translate.Translate;
/**
 *
 * @author Frostalf
 */
public class DBStorage {

    private Translate plugin;
    private ConnectionPool connectionPool = new ConnectionPool(plugin);
    private Statement stmt = null;
    
    public void DBStorage (Translate plugin) {
        this.plugin = plugin;
    }
    
    public void createTable() {
        String query = "SELECT * FROM translate";       
        if (connectionPool.getConnection()) {
            
            try {
                stmt = connectionPool.connection().createStatement();
                stmt.setQueryTimeout(30);
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.last()) {
                    rs.close();
                    stmt.close();
                    String makeTable = "CREATE TABLE translate(id int primary key NOT NULL, playername text NOT NULL, languagecode text NOT NULL, enabled boolean NOT NULL)";
                    stmt = connectionPool.connection().createStatement();
                    stmt.setQueryTimeout(30);
                    stmt.executeUpdate(makeTable);                  
                }
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not create database!");
            }
            

        }
    }
}
