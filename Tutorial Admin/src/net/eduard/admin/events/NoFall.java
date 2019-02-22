package net.eduard.admin.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

import net.eduard.admin.Main;

public class NoFall implements Listener {
	@EventHandler
	public void event(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		if (Main.admins.contains(p))
			if (p.getItemInHand().getType() == Material.FEATHER)
				if (e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					e.setCancelled(true);
					target.setVelocity(new Vector(0, 2, 0));
					p.sendMessage("§6Testando No-Fall no jogador " + target.getDisplayName());
				}
	}
}
