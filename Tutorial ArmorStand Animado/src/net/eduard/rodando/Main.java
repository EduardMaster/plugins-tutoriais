package net.eduard.rodando;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

	private static Plugin plugin;

	public void onEnable() {
		plugin = this;
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void cmd(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().startsWith("/cu")) {

			e.setCancelled(true);
			List<ItemStack> lista = Arrays.asList(new ItemStack(Material.DIAMOND_BLOCK),
					new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK),
					new ItemStack(Material.EMERALD_BLOCK), new ItemStack(Material.REDSTONE_BLOCK),
					new ItemStack(Material.STONE), new ItemStack(Material.WORKBENCH), new ItemStack(Material.ANVIL),
					new ItemStack(Material.BOOKSHELF), new ItemStack(Material.PISTON_BASE));
			gerarEfeito(p.getLocation(), lista, p);
		}
	}

	public static ItemStack getRandomItem(ItemStack... items) {

		return items[new Random().nextInt(items.length - 1)];
	}

	@SafeVarargs
	public static <E> E getRandom(E... items) {
		return items[new Random().nextInt(items.length - 1)];
	}

	public static <E> E getRandom(List<E> list) {
		return list.get(new Random().nextInt(list.size()));
	}

	public static <E> List<E> mover(int casasMovida, List<E> lista) {
		List<E> listaCopia = new ArrayList<>();
		if (casasMovida > lista.size()) {
			casasMovida = 1;
		}
		for (int i = 0; i < lista.size(); i++) {
			int m = i + casasMovida;
			if (m >= lista.size()) {
				m -= lista.size();
			}
			E dado = lista.get(m);
			listaCopia.add(dado);
		}
		return listaCopia;
	}

	public static void gerarEfeito(Location location, List<ItemStack> lista, Player p) {
//		Collections.shuffle(lista);
		ArrayList<ArmorStand> stands = new ArrayList<>();

		ArrayList<Location> locs = getCircle(location, 1, lista.size());

		for (int i = 0; i < lista.size(); i++) {
			Location loc = locs.get(i);
			ArmorStand stand = location.getWorld().spawn(loc, ArmorStand.class);
			ItemStack item = lista.get(i);
			stand.setSmall(true);
			stand.setHelmet(item);
			stand.setArms(false);
			stand.setVisible(false);
			stand.setCustomName("");
			stand.setGravity(false);
			stands.add(stand);
		}
		new BukkitRunnable() {
			int contagem = 500;
			int movendo = 0;
			int id = 0;

			@Override
			public void run() {
				// 1 , 2 , 3
				// 2 , 3 , 1

				contagem--;
				movendo++;
				if (movendo == lista.size()) {
					movendo = 0;
				}
				ItemStack inicio = stands.get(0).getHelmet();
				for (int i = lista.size() - 1; i > 0; i--) {
					ArmorStand atual = stands.get(i);
					ArmorStand proximo = null;
					if (i == lista.size()-1) {
						proximo = stands.get(0);
					} else {
						proximo = stands.get(i + 1);
					}
					proximo.setHelmet(atual.getHelmet());
//					if (i == 1) {
//						atual.setHelmet(stands.get(0).getHelmet());
//					}
				}
				stands.get(1).setHelmet(inicio);

				if (contagem == 0) {
					cancel();
					new BukkitRunnable() {
						int durante = lista.size();

						@Override
						public void run() {

							durante--;
							ArmorStand stand = getRandom(stands);
							stand.remove();
							stands.remove(stand);
							if (durante == 1) {

								ItemStack resante = stands.get(0).getHelmet();
								p.getInventory().addItem(resante);

								stands.get(0).remove();
								cancel();
							}

						}
					}.runTaskTimer(Main.plugin, 10, 10);

				}
			}
		}.runTaskTimer(Main.plugin, 5, 5);

	}

	public static ArrayList<Location> getCircle(Location center, double radius, int amount) {
		World world = center.getWorld();
		double increment = (2 * Math.PI) / amount;
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int i = 0; i < amount; i++) {
			double angle = i * increment;
			double x = center.getY() + (radius * Math.cos(angle));
			double z = center.getZ() + (radius * Math.sin(angle));
			locations.add(new Location(world, center.getX(), x, z));
		}
		return locations;
	}

}
