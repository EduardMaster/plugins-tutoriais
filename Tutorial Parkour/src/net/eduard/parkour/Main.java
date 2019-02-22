
package net.eduard.parkour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.parkour.command.ParkourCommand;
import net.eduard.parkour.event.ParkourEvent;
import net.eduard.parkour.manager.Parkour;
import net.eduard.parkour.utils.SimpleScore;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
	public static FileConfiguration config;
	private File data;

	@Override
	@SuppressWarnings("unchecked")
	public void onEnable() {
		plugin = this;
		config = plugin.getConfig();
//		saveDefaultConfig();
		{
			config.addDefault("valor3", "valor4");
			config.options().copyDefaults(true);
			saveConfig();
		}
		getCommand("parkour").setExecutor(new ParkourCommand());
		Bukkit.getPluginManager().registerEvents(new ParkourEvent(), this);
		data = new File(getDataFolder(), "parkours.data");
		if (data.exists()) {
			Parkour.setParkours((Map<String, Parkour>) getSerializable(data));
			for (Parkour par : Parkour.getParkours().values()) {
				par.setPlayers(new ArrayList<>());
			}
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				updateScoreboard();
			}

		}.runTaskTimer(this, 20, 20);
	}

	public static void updateScoreboard() {
		for (Entry<Player, Parkour> map : Parkour.getPlaying().entrySet()) {
			Player p = map.getKey();
			Parkour par = map.getValue();
			SimpleScore score = null;
			if (Parkour.getScores().containsKey(p)) {
				score = Parkour.getScores().get(p);
				score.setTitle(ChatColor.GREEN + "Parkour: " + ChatColor.DARK_GREEN + par.getName());
			} else {
				score = new SimpleScore(ChatColor.GREEN + "Parkour: " + ChatColor.DARK_GREEN + par.getName());
				Parkour.getScores().put(p, score);
			}
			score.empty(15);
			score.set(14, ChatColor.GOLD + "Caidas: " + ChatColor.YELLOW + Parkour.getFalls().get(p));
			score.empty(13);
			score.set(12, ChatColor.GOLD + "Tempo: " + ChatColor.YELLOW + Parkour.getTime().get(p));
			score.empty(11);
			score.set(10, ChatColor.GOLD + "Jogando: " + ChatColor.YELLOW + par.getPlayers().size());
			score.empty(9);
			Integer value = Parkour.getTime().get(p);
			value++;
			Parkour.getTime().put(p, value);
			score.setScoreboard(p);

		}
	}

	@Override
	public void onDisable() {
		setSeralizable(Parkour.getParkours(), data);
	}

	public static void setSeralizable(Object object, File file) {
		try {
			FileOutputStream saveStream = new FileOutputStream(file);
			ObjectOutputStream save = new ObjectOutputStream(saveStream);
			if (object instanceof Serializable) {
				save.writeObject(object);
			}
			save.close();
		} catch (Exception ex) {
		}

	}

	public static Object getSerializable(File file) {
		if (!file.exists()) {
			return null;
		}
		try {

			FileInputStream getStream = new FileInputStream(file);
			ObjectInputStream get = new ObjectInputStream(getStream);
			Object object = get.readObject();
			get.close();
			return object;
		} catch (Exception ex) {
		}

		return null;
	}
}
