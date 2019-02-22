
package net.eduard.rpg;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.eduard.rpg.event.PvPManager;
import net.eduard.rpg.manager.ItemStats;
import net.eduard.rpg.manager.Stats;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
	public static FileConfiguration config;
	@Override
	public void onEnable() {
		plugin = this;
		config = plugin.getConfig();
		readStats();
		addDefaultsValues();
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new PvPManager(), this);
	}
	private void readStats() {
		
	}

	@EventHandler
	public void event(PlayerItemDamageEvent e) {
		e.setCancelled(true);
		
	}

	public void addDefaultsValues() {
		Stats stats = new Stats();
		for (EntityType entityType : EntityType.values()) {
			
			if (entityType.isSpawnable()) {
				if (entityType.isAlive()) {
					if (Animals.class.isAssignableFrom(entityType.getEntityClass())) {
						Stats animal = stats.clone();
					}
					if (Monster.class.isAssignableFrom(entityType.getEntityClass())) {
						Stats monster = stats.clone();
					}
				}
			} else {
				if (entityType.isAlive()) {
					Stats player = stats.clone();
					
				}
			}
			
		}
	}

	@EventHandler
	public void event(PlayerJoinEvent e) {

		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		e.getPlayer().setHealthScaled(false);
		e.getPlayer().setMaxHealth(20);
		e.getPlayer().setLevel(0);
		e.getPlayer().setTotalExperience(0);
		e.getPlayer().setExp(0);
		e.getPlayer().setDisplayName("§6" + e.getPlayer().getName() + "§r");
		e.getPlayer().setExhaustion(0);
		e.getPlayer().setFireTicks(0);
		e.getPlayer().setAllowFlight(false);
		e.getPlayer().setGameMode(GameMode.SURVIVAL);
		e.getPlayer().setSaturation(20);
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setHealth(e.getPlayer().getMaxHealth());
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60 * 60, 0));
		{
			ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
			ItemStats s = new ItemStats(item);
			s.setLevel(1);
			s.setDamage(5);
			s.setCritChance(25);
			s.setCrit(30);
			e.getPlayer().getInventory().addItem(s.getItemModified());
		}
		{
			ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
			ItemStats s = new ItemStats(item);
			s.setLevel(1);
			s.setEvasionChance(7);
			e.getPlayer().getInventory().addItem(s.getItemModified());
		}

	}

}
