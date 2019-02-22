package net.eduard.admin.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.eduard.admin.Main;

public class VerInv implements Listener {
	@EventHandler
	public void event(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		if (p.getItemInHand().getType() == Material.BOWL)
			if (Main.admins.contains(p))
				if (e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					e.setCancelled(true);
					p.openInventory(target.getInventory());
					p.sendMessage("§6Voce abriu o Inventario do jogador " + target.getDisplayName());
				}
	}
}
