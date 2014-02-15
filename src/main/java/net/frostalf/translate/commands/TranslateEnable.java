
package net.frostalf.translate.commands;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.frostalf.translate.Translate;
import net.frostalf.translate.database.ConnectionPool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Frostalf
 */
public class TranslateEnable implements CommandExecutor {
    
    private Translate plugin;
    
    public TranslateEnable (Translate plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ConnectionPool connectionPool = new ConnectionPool(plugin);
            if(cmd.getName().equalsIgnoreCase("TranslateEnable")) {
                if(!connectionPool.getConnection()) {
                    connectionPool.connection();
                }
                try {
                    Statement stmt = connectionPool.connection().createStatement();
                    String query = "UPDATE translate set enabled = 1 where playername=" + player.getName();
                    stmt.executeUpdate(query);
                    stmt.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(TranslateEnable.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return true;
    }

}
