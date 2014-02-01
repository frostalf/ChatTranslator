
package net.frostalf.translate.listeners;

import net.frostalf.translate.Translate;
import org.bukkit.event.Listener;

/**
 *
 * @author Frostalf
 */
public class ListenerManager implements Listener {

    Translate plugin;
    
    public ListenerManager (Translate plugin) {
        this.plugin = plugin;
    }
}
