
package net.eduard.spawn.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.eduard.spawn.Main;

public class SpawnEvent implements Listener {

	@EventHandler
	public void event(PlayerJoinEvent e) {
		if (Main.config.contains("spawn")){
			e.getPlayer().teleport(Main.config.getLocation("spawn"));
		}
	}
}
