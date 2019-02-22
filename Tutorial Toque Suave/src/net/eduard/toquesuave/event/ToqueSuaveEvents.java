
package net.eduard.toquesuave.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.api.lib.manager.EventsManager;
import net.eduard.toquesuave.Main;

public class ToqueSuaveEvents extends EventsManager {

	@EventHandler
	public void event(PlayerMoveEvent e) {

	}
	@EventHandler
	public void event(PlayerJoinEvent e) {
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void event(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		if (Main.getInstance().getConfigs().contains("ToqueSuave")) {
			if (!p.getItemInHand().isSimilar(
					Main.getInstance().getConfigs().getItem("ToqueSuave"))) {
				e.setCancelled(true);
				
			}
		}

	}

}
