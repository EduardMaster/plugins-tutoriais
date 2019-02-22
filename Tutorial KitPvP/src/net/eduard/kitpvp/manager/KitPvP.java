package net.eduard.kitpvp.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import net.eduard.kitpvp.kits.None;

public final class KitPvP {

	public static Kit kitNone = new None();

	public static ItemStack soup = newItem(Material.MUSHROOM_SOUP,

			"§eSopa");
	public static ItemStack glass = newItem(Material.STAINED_GLASS_PANE,

			" §6§lKitPvP");
	public static Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
	public static ItemStack keyKitSelector = KitPvP.newItem(Material.CHEST,
			"§3Kits §7(Clique Direito)");
	public static String menuKitSelector = "§7Kits Disponiveis";
	public static HashMap<String, Kit> kits = new HashMap<>();
	public static HashMap<Player, Kit> players = new HashMap<>();

	private static String menuKitShop = "§8Kits a venda";

	public static boolean hasKit(Player p) {
		return players.get(p) != kitNone;
	}

	public static void selectKit(Player p, Kit kit) {
		players.put(p, kit);
		PlayerInventory inv = p.getInventory();
		inv.clear();
		inv.setArmorContents(null);
		for (ItemStack item : kit.getItems()) {
			inv.addItem(item);
		}
		for (int i = 0; i < 4 * 9; i++) {
			inv.addItem(soup);
		}
		kit.getPlayers().add(p);
	}
	public static void removeKit(Player p) {
		KitPvP.players.get(p).getPlayers().remove(p);
		KitPvP.players.remove(p);
	}
	public static void giveItems(Player p) {
		PlayerInventory inv = p.getInventory();
		inv.clear();
		inv.setArmorContents(null);
		inv.setItem(2, newItem(Material.COMPASS, "§6Warps §7(Clique Direito)"));
		inv.setItem(7, newItem(Material.EMERALD, "§aLoja §7(Clique Direito)"));
		inv.setItem(5, newItem(Material.CHEST, "§3Kits §7(Clique Direito)"));
		while (inv.firstEmpty() != -1) {
			if (inv.firstEmpty() < 9) {
				inv.setItem(inv.firstEmpty(), glass);
			}

		}
	}

	public static ItemStack newItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	public static void openKitsShop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, menuKitShop);
		for (Kit kit : kits.values()) {
			if (!p.hasPermission("kits." + kit.getName())) {
				inv.addItem(kit.getIcon());
			}
		}
		if (inv.firstEmpty() == 0) {
			p.sendMessage("§aVoce já possui todos os kits!");
		} else {
			while (inv.firstEmpty() != -1) {
				inv.setItem(inv.firstEmpty(),
						newItem(Material.STAINED_GLASS_PANE, " "));
			}
			p.openInventory(inv);
		}
	}
	public static void openKitsSelector(Player p) {
		Inventory inv = Bukkit.createInventory(null, 6 * 9, menuKitSelector);
		for (Kit kit : kits.values()) {
			inv.addItem(kit.getIcon());
		}
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(),
					newItem(Material.STAINED_GLASS_PANE, " "));
		}
		p.openInventory(inv);
	}

}
