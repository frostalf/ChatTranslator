
package net.frostalf.translate.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jolbox.bonecp.BoneCP;
import net.frostalf.translate.Translate;
/**
 *
 * @author Frostalf
 */
public class DBStorage {

    private Translate plugin;
    
    public DBStorage (Translate plugin) {
        this.plugin = plugin;
    }
    
    BoneCP connectionPool = null;
    Connection connection = null;
}
