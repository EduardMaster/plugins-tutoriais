package net.eduard.dinheiro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main getPlugin() {
		return JavaPlugin.getPlugin(Main.class);
	}
	private static Main plugin;
	
	private Map<UUID, Double> money = new HashMap<>();
	
	
	public Map<UUID, Double> getMoney() {
		return money;
	}
	public static Main getInstance() {
		return plugin;
	}
	public void onEnable() {
		plugin = this;
		getCommand("money").setExecutor(new ComandoMoney());
	}

}
