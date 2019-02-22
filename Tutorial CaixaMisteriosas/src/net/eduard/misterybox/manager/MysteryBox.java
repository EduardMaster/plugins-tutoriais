package net.eduard.misterybox.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.misterybox.Main;

public final class MysteryBox {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(Main.class);
	
	
	public static void cima(Player p, Inventory inv, int slot) {

		new BukkitRunnable() {

			@Override
			public void run() {
				if (slot == 2 * 9 + 4) {
					Bukkit.broadcastMessage("Encheu a parte de cima");
				} else {
					int random = new Random().nextInt(6);
					ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,
							1, (short) random);
					inv.setItem(slot, item);
					p.playSound(p.getLocation(), Sound.CLICK, 2, 1);
					cima(p, inv, slot + 1);

				}
			}
		}.runTaskLater(plugin, 2);
	}
	public static void baixo(Player p, Inventory inv, int slot) {

		new BukkitRunnable() {

			@Override
			public void run() {
				if (slot == 3 * 9 - 1 - 4) {
					Bukkit.broadcastMessage("Encheu a parte de baixo");
					inv.setItem(slot, gerarItemBasico());
				} else {
					int random = new Random().nextInt(6);
					ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,
							1, (short) random);
					inv.setItem(slot, item);
					// p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 2,
					// 1);
					baixo(p, inv, slot - 1);

				}
			}
		}.runTaskLater(plugin, 2);
	}
	public static ItemStack gerarItemBasico() {
		int rd = 1 + new Random().nextInt(list.size());
		ItemStack item = list.get(rd - 1);
		if (Math.random() <= 0.2F) {
			return item;
		} else {
			return gerarItemBasico();
		}
	}
	static List<ItemStack> list = new ArrayList<>();
	static {
		list.add(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
		list.add(new ItemStack(Material.GOLDEN_APPLE));

	}
	public static void open(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "§8Abrindo...");
		cima(p, inv, 0);
		baixo(p, inv, 5 * 9 - 1);

		p.openInventory(inv);
		// >>>>>>>>>>>>
		// <<<<<<<<<<<<
		// >>>>>>>>>>>>
		// >>> <<<
		// <<<<<<<<<<<<
		// >>>>>>>>>>>>
	}
	public static void tudo(Player p, Inventory inv, int teste) {

		new BukkitRunnable() {
			int v = 0;
			@Override
			public void run() {
				v++;
				for (int x = 3 * 9 - 1; x >= 0; x--) {
					if (x > 2 * 9 - 2 || x < 1 * 9 + 1) {
						int random = new Random().nextInt(6);
						ItemStack item = new ItemStack(
								Material.STAINED_GLASS_PANE, 1, (short) random);
						inv.setItem(x, item);
					}

				}
				for (int x = 10 + 1; x <= 10 + 6; x++) {
					ItemStack item = inv.getItem(x);
					inv.setItem(x, null);
					inv.setItem(x - 1, item);

				}
				inv.setItem(10 + 6, gerarItemBasico());
				p.playSound(p.getLocation(), Sound.CLICK, 2, 1);
				if (v == teste) {
					cancel();
				}

			}
		}.runTaskTimer(plugin, 3, 3);
	}
	public static void open2(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§8Abrindo v2...");
		tudo(p, inv, 10 + new Random().nextInt(20));
		p.openInventory(inv);
		// >>>>>>>>>>>>
		// <<<<<<<<<<<<
		// >>>>>>>>>>>>
		// >>> <<<
		// <<<<<<<<<<<<
		// >>>>>>>>>>>>
	}

}
