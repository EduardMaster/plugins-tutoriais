package net.eduard.misterybox.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ClickTemplate implements Listener {
	@EventHandler
	public void event(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		if (p.getItemInHand().getType() == Material.BLAZE_ROD)
			if (e.getRightClicked() instanceof Player) {
				Player target = (Player) e.getRightClicked();
				e.setCancelled(true);
				// Faz algo
				target.sendMessage("O jogador " + p.getDisplayName() + " clicou em você!");
			}
	}
}
