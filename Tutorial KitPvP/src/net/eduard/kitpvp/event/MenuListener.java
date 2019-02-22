package net.eduard.kitpvp.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.eduard.kitpvp.manager.Kit;
import net.eduard.kitpvp.manager.KitPvP;

public class MenuListener implements Listener {

	// "§6Warps §7(Clique Direito)")); COMPASS
	// "§aLoja §7(Clique Direito)")); EMERALD
	// "§3Kits §7(Clique Direito)")); CHEST

	@EventHandler
	public void event(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() == null)
			return;
		if (e.getItem().equals(KitPvP.keyKitSelector)) {
			KitPvP.openKitsSelector(p);
		}
	}

	@EventHandler
	public void event(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			
			if (e.getInventory().getTitle().equals(KitPvP.menuKitSelector)) {
				e.setCancelled(true);
				ItemStack item = e.getCurrentItem();
				if (item == null)
					return;
				for (Kit kit : KitPvP.kits.values()) {
					if (item.equals(kit.getIcon())) {
						p.closeInventory();
						KitPvP.selectKit(p, kit);
					}
				}

			}
		}
		
	}
}
