
package net.eduard.skywars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Location Lobby;

	public static ArrayList<SkyWars> skywars = new ArrayList<>();

	public static Main main;

	@Override
	public void onEnable() {
		main = this;

		reloadMaps();
		new SkyWars().runTaskTimer(this, 20, 20);
		if (getConfig().contains("lobby")) {
			FileConfiguration config = getConfig();
			World world = Bukkit.getWorld(config.getString("lobby.world"));
			double x = config.getDouble("lobby.x");
			double y = config.getDouble("lobby.y");
			double z = config.getDouble("lobby.z");
			double yaw = config.getDouble("lobby.yaw");
			double pitch = config.getDouble("lobby.pitch");
			Location location = new Location(world, x, y, z, (float) yaw, (float) pitch);
			Lobby = location;
		}
		getCommand("skywars").setExecutor(new SkyWarsCommand());
	}

	public static void reloadMaps() {

		File file = new File(main.getDataFolder(), "/Arenas/");
		if (!file.isDirectory()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		if (file.listFiles().length >= 1) {
			for (File sky : file.listFiles()) {
				YamlConfiguration config = YamlConfiguration.loadConfiguration(sky);
				SkyWars skywar = new SkyWars(config.getString("name"));
				skywar.setEnabled(config.getBoolean("enabled"));
				skywar.setTimeIntoGameOver(config.getInt("time_into_gameover"));
				skywar.setTimeIntoRestart(config.getInt("time_into_restart"));
				skywar.setTimeIntoStart(config.getInt("time_into_start"));

				{
					World world = Bukkit.getWorld(config.getString("lobby.world"));
					double x = config.getDouble("lobby.x");
					double y = config.getDouble("lobby.y");
					double z = config.getDouble("lobby.z");
					double yaw = config.getDouble("lobby.yaw");
					double pitch = config.getDouble("lobby.pitch");
					Location location = new Location(world, x, y, z, (float) yaw, (float) pitch);
					skywar.setLobby(location);
				}
				for (String spawn : config.getConfigurationSection("spawns").getKeys(false)) {
					World world = Bukkit.getWorld(config.getString("spawns." + spawn + ".world"));
					double x = config.getDouble("spawns." + spawn + ".x");
					double y = config.getDouble("spawns." + spawn + ".y");
					double z = config.getDouble("spawns." + spawn + ".z");
					double yaw = config.getDouble("spawns." + spawn + ".yaw");
					double pitch = config.getDouble("spawns." + spawn + ".pitch");
					Location location = new Location(world, x, y, z, (float) yaw, (float) pitch);
					skywar.getSpawns().add(location);
				}
				skywars.add(skywar);
			}
		}
	}

	public static boolean existsLobby() {
		return Lobby != null;
	}

	@Override
	public void onDisable() {

		saveMaps();
		if (existsLobby()) {
			FileConfiguration config = getConfig();
			config.set("lobby.x", Lobby.getX());
			config.set("lobby.y", Lobby.getY());
			config.set("lobby.z", Lobby.getZ());
			config.set("lobby.yaw", Lobby.getYaw());
			config.set("lobby.pitch", Lobby.getPitch());
		}
	}

	public static boolean existsSkywars(String name) {
		for (SkyWars skywar : skywars) {
			if (skywar.getName().equalsIgnoreCase(name)) {
				return true;
			}

		}
		return false;
	}

	public static SkyWars getSkyWars(String name) {
		for (SkyWars skywar : skywars) {
			if (skywar.getName().equalsIgnoreCase(name)) {
				return skywar;
			}

		}
		SkyWars sky = new SkyWars(name);
		skywars.add(sky);
		return sky;

	}

	public static void saveMaps() {

		for (SkyWars sky : skywars) {
			File file = new File(main.getDataFolder(), "/Arenas/" + sky.getName().toLowerCase() + ".yml");
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			config.set("name", sky.getName());
			config.set("enabled", sky.isEnabled());
			config.set("time_into_start", sky.getTimeIntoStart());
			config.set("time_into_restart", sky.getTimeIntoRestart());
			config.set("time_into_gameover", sky.getTimeIntoGameOver());
			config.set("lobby.world", sky.getLobby().getWorld().getName());
			config.set("lobby.x", sky.getLobby().getX());
			config.set("lobby.y", sky.getLobby().getY());
			config.set("lobby.z", sky.getLobby().getZ());
			config.set("lobby.yaw", sky.getLobby().getYaw());
			config.set("lobby.pitch", sky.getLobby().getPitch());
			config.set("spawns", null);
			config.createSection("spawns");
			int id = 1;
			for (Location spawn : sky.getSpawns()) {
				config.set("spawns.spawn-" + id + ".world", spawn.getWorld().getName());
				config.set("spawns.spawn-" + id + ".x".replace("$", "-"), spawn.getX());
				config.set("spawns.spawn-" + id + ".y", spawn.getY());
				config.set("spawns.spawn-" + id + ".z", spawn.getZ());
				config.set("spawns.spawn-" + id + ".yaw", spawn.getYaw());
				config.set("spawns.spawn-" + id + ".pitch", spawn.getPitch());
				id++;
			}
			try {
				config.save(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
