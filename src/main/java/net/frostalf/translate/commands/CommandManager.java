
package net.frostalf.translate.commands;

import net.frostalf.translate.Translate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Frostalf
 */
public class CommandManager implements CommandExecutor {

    Translate plugin;
    
    public CommandManager (Translate plugin) {
        this.plugin = plugin;
    }
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return true;
    }
    
}
