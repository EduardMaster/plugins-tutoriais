package net.eduard.simplewarp;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.simplewarp.manager.Configs;
import net.eduard.simplewarp.manager.Warp;

public class Main extends JavaPlugin {

	private static Main plugin;
	private static Map<String, Warp> warps = new HashMap<>();
	private static FileConfiguration messages;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("warp").setExecutor(new WarpCommand());
		Bukkit.getPluginManager().registerEvents(new WarpListener(), this);
		saveResource("messages.yml", true);
		reload();
	}
	@Override
	public void onDisable() {
		
	}
	
	public static Map<String, Warp> getWarp() {
		return warps;
	}
	
	public static Collection<Warp> getWarps() {
		return warps.values();
	}

	public static String message(String path) {
		return ChatColor.translateAlternateColorCodes('&',
				messages.getString(path));
	}

	public static void reload() {
		messages = YamlConfiguration.loadConfiguration(
				new File(plugin.getDataFolder(), "messages.yml"));

	}

	public static void setWarp(String name, Location location) {
		Configs config = new Configs("Warp/" + name.toLowerCase() + ".yml",plugin);
		config.set("name", name);
		config.set("world", location.getWorld().getName());
		config.set("x", location.getX());
		config.set("y", location.getY());
		config.set("z", location.getZ());
		config.set("yaw", location.getYaw());
		config.set("pitch", location.getPitch());
		config.saveConfig();
	}

}
