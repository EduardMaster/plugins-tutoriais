
package net.eduard.antibot;


import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static List<String> ips_proxy;
	public static ConfigAPI config;
	public static Main plugin;
	@Override
	public void onEnable() {
		plugin = this;
		config = new ConfigAPI("config.yml", this);
		config.add("Bot",
			"&cSuspeita de Bot! $enter §aPor favor relogue em alguns segundos!");
		config.saveConfig();
		new BotDetector();
		try {
			File file = new File(getDataFolder(),"proxy.yml");
			Bukkit.getConsoleSender().sendMessage("§3[§bAntBot§3] §7Salvando a config §eproxy.yml");
			saveResource("proxy.yml", false);
			Bukkit.getConsoleSender().sendMessage("§3[§bAntBot§3] §7Pegando os §fIps-Proxys§7 da config §eproxy.yml");
			ips_proxy = Files.readAllLines(file.toPath());
			Bukkit.getConsoleSender().sendMessage("§3[§bAntBot§3] §aAntivando Bot Detector!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
