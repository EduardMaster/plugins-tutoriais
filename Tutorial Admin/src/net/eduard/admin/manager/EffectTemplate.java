package net.eduard.admin.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EffectTemplate implements Listener{
@EventHandler
public void event(PlayerInteractEvent e) {
	Player p = e.getPlayer();
	if (p.getItemInHand()==null)return;
	if (e.getAction() == Action.RIGHT_CLICK_AIR) {
		if (e.getMaterial() == Material.CHEST) {
			e.setCancelled(true);
			// Faz algo
			
			
			
		}
	}
}
}
