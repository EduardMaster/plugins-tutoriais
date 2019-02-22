package net.eduard.misterybox.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.bukkit.inventory.PlayerInventory;


public class SimpleKitAPI {

	public static class Kits implements Listener {
		public Kits(String name) {
			this.name = name;
			Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("EduardAPI"));

		}

		@EventHandler
		private void open(PlayerInteractEvent e) {
			if (e.getItem() == null)
				return;
			if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				if (e.getItem().equals(shopKey)) {
					shop(e.getPlayer());
				} else if (e.getItem().equals(kitsKey)) {
					open(e.getPlayer());
				}
			}
		}

		@EventHandler
		private void click(InventoryClickEvent e) {
			if (e.getCurrentItem() == null)
				return;
			if (!(e.getWhoClicked() instanceof Player))
				return;
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equals(shopTitle)) {
				e.setCancelled(true);
				for (Kit kit : kits.values()) {
					if (kit.icon.equals(e.getCurrentItem())) {
						if (canBuy(p, kit)) {
							buyKit(p, kit);
						} else {
							p.sendMessage(noBuy);
						}
						p.closeInventory();
						break;
					}
				}

			} else if (e.getInventory().getTitle().equals(kitsTitle)) {
				e.setCancelled(true);
				for (Kit kit : kits.values()) {
					if (kit.icon.equals(e.getCurrentItem())) {
						selectKit(p, kit);
						p.closeInventory();
						break;
					}
				}
			}
		}

		public void removeKit(Player player) {
			for (Kit kit : kits.values()) {
				kit.players.remove(player);
			}
			players.remove(player);
		}

		public void open(Player player) {
			Inventory inv = Bukkit.createInventory(null, 6 * 9, kitsTitle);
			for (Kit kit : kits.values()) {
				if (player.hasPermission("kits." + kit.name))
					inv.addItem(kit.icon);
			}
			player.openInventory(inv);
			player.sendMessage(openKits);
		}

		public void shop(Player player) {
			Inventory inv = Bukkit.createInventory(null, 6 * 9, shopTitle);
			for (Kit kit : kits.values()) {
				if (!player.hasPermission("kits." + kit.name))
					inv.addItem(kit.icon);
			}
			player.openInventory(inv);
			player.sendMessage(openShop);
		}

		public void selectKit(Player player, Kit kit) {
			PlayerInventory inv = player.getInventory();
			inv.clear();
			inv.setArmorContents(null);
			for (Entry<Integer, ItemStack> map : kit.items.entrySet()) {
				inv.setItem(map.getKey(), map.getValue());
			}
			if (kit.fillSoups) {
				while (inv.firstEmpty() != -1) {
					inv.addItem(new ItemStack(Material.MUSHROOM_SOUP));
				}
			}
		}

		public void buyKit(Player player, Kit kit) {
			player.sendMessage(buy.replace("$kit", kit.name).replace("$price", "" + kit.price));
		}

		public boolean register(KitType type, Kit kit) {
			if (kits.containsKey(type))
				return false;
			kits.put(type, kit);
			return true;
		}

		@SuppressWarnings("unused")
		private String shopTitle, kitsTitle, name;
		private Map<KitType, Kit> kits = new HashMap<>();
		private Map<Player, KitType> players = new HashMap<>();
		private ItemStack shopKey, kitsKey;
		private String buy = "§6Voce comprou o Kit $kit por $price de dinhero";
		private String noBuy = "§6Voce nao tem dinheiro suficiente ($price) para comprar este kit $kit!";
		private String openShop = "§6Voce abriu a Loja de Kits";
		private String openKits = "§6Voce abriu o Menu de Kits";
	}

	public static class Kit implements Listener {
		private ItemStack icon;
		private double price;
		private String name;
		private Map<Integer, ItemStack> items = new HashMap<>();
		private List<Player> players = new ArrayList<>();
		private boolean fillSoups = false;

		public Kit() {
			name = getClass().getSimpleName();
		}
	}

	public enum KitType {

	}

	public static boolean canBuy(Player player, Kit kit) {

		return false;
	}

}
