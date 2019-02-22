package net.eduard.admin.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiTemplate implements Listener{
	@EventHandler
	public void open(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK|e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (e.getMaterial() == Material.COMPASS) {
				open(e.getPlayer());
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void click(InventoryClickEvent e) {
		if (e.getCurrentItem()==null)return;
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equals("Titulo")) {
				if (e.getCurrentItem().getType() == Material.STONE) {
					p.chat("/comando algo");
				}
				p.closeInventory();
				e.setCancelled(true);
			}
			
		}
	}
	private static Inventory gui;

	@SuppressWarnings("deprecation")
	public static void create() {
		gui = Bukkit.createInventory(null, 6, "Titulo");
		gui.setItem(5, new ItemStack(15));
	}
	public static Inventory getGUI() {
		return gui;
	}

	public static void open(Player p) {
		p.openInventory(gui);
	}

	static {
		create();
		Bukkit.getPluginManager().registerEvents(new GuiTemplate(), Bukkit.getPluginManager().getPlugins()[0]);	
	}
}
