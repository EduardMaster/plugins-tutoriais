package net.eduard.antibot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigAPI {

	private JavaPlugin plugin;
	private String name;
	private File file;
	private FileConfiguration config;

	public String getName() {
		return name;
	}

	public ConfigAPI setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
		return this;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public ConfigAPI(String name, JavaPlugin plugin) {
		this.plugin = plugin;
		this.name = name;
		reloadConfig();
	}

	public void add(String path,Object value) {
		getConfig().addDefault(path, value);
	}
	public ConfigAPI reloadConfig() {
		file = new File(plugin.getDataFolder(), name);
		config = YamlConfiguration.loadConfiguration(file);
		InputStream defaults = plugin.getResource(file.getName());
		if (defaults != null) {
			YamlConfiguration loadConfig = YamlConfiguration.loadConfiguration(defaults);
			config.setDefaults(loadConfig);
		}
		return this;
	}

	public ConfigAPI saveConfig() {
		try {
			config.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return this;
	}

	public String message(String path) {
		return ChatColor.translateAlternateColorCodes('&', getConfig().getString(path));
	}

	public ConfigAPI saveDefaultConfig() {
		plugin.saveResource(name, false);
		return this;
	}

	public ConfigAPI saveDefault() {
		config.options().copyDefaults(true);
		saveConfig();
		return this;
	}
	@SuppressWarnings("deprecation")
	public static void setItem(ConfigurationSection section, ItemStack item) {
		section.set("id", item.getTypeId());
		section.set("data", item.getDurability());
		if (item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasDisplayName()) {
				section.set("name", meta.getDisplayName());
			}
			if (meta.hasLore()) {
				List<String> lines = new ArrayList<>();
				for (String line : meta.getLore()) {
					lines.add(line);
				}
				section.set("lore", lines);
			}
		}
		StringBuilder text = new StringBuilder();
		for (Entry<Enchantment, Integer> enchant : item.getEnchantments().entrySet()) {
			text.append(enchant.getKey().getId() + "-" + enchant.getValue() + ",");
		}
		section.set("enchant", text.toString());
	}
	public static void setLocation(ConfigurationSection section, Location location) {
		section.set("world", location.getWorld().getName());
		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
		section.set("yaw", location.getYaw());
		section.set("pitch", location.getPitch());
	}

	public static Location getLocation(ConfigurationSection section) {
		World world = Bukkit.getWorld(section.getString("world"));
		double x = section.getDouble("x");
		double y = section.getDouble("y");
		double z = section.getDouble("z");
		float yaw = (float) section.getDouble("yaw");
		float pitch = (float) section.getDouble("pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static Location getLocation(String line) {
		String[] split = line.split(",");
		World world = Bukkit.getWorld(split[0]);
		double x = Double.parseDouble(split[1]);
		double y = Double.parseDouble(split[2]);
		double z = Double.parseDouble(split[3]);
		float yaw = Float.parseFloat(split[4]);
		float pitch = Float.parseFloat(split[5]);
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String toChatMessage(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	public static String saveLocation(Location location) {
		StringBuilder text = new StringBuilder();
		text.append(location.getWorld().getName() + ",");
		text.append(location.getX() + ",");
		text.append(location.getY() + ",");
		text.append(location.getZ() + ",");
		text.append(location.getYaw() + ",");
		text.append(location.getPitch());
		return text.toString();
	}

	public static String toConfigMessage(String text) {
		return text.replace("§", "&");
	}

	@SuppressWarnings("deprecation")
	public static ItemStack getItem(ConfigurationSection section) {
		ItemStack item = new ItemStack(section.getInt("id"), section.getInt("data"));
		ItemMeta meta = item.getItemMeta();
		if (section.contains("name")) {
			meta.setDisplayName(toChatMessage(section.getString("name")));
		}
		if (section.contains("lore")) {
			List<String> lines = new ArrayList<>();
			for (String line : meta.getLore()) {
				lines.add(toChatMessage(line));
			}
		}
		if (section.contains("enchant")) {
			for (String value : section.getString("enchant").split(",")) {
				if (value == null)
					continue;
				if (value.isEmpty())
					continue;
				if (value.contains("-")) {
					String[] split = value.split("-");
					item.addUnsafeEnchantment(Enchantment.getById(Integer.valueOf(split[0])),
							Integer.valueOf(split[1]));
				}
			}
		}
		return item;
	}
}