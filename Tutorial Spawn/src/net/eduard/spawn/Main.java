
package net.eduard.spawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.spawn.command.SetSpawnCommand;
import net.eduard.spawn.command.SpawnCommand;
import net.eduard.spawn.event.SpawnEvent;
import net.eduard.spawn.manager.ConfigAPI;

public class Main extends JavaPlugin{
	public static Main plugin;
	public static ConfigAPI config;
	public static ConfigAPI messages;

	@Override
	public void onEnable() {
		plugin = this;
		config = new ConfigAPI("config.yml");
		messages = new ConfigAPI("messages.yml");
		save();
		commands();
		events();
	}
	public void commands() {
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
	}
	public void events() {
		Bukkit.getPluginManager().registerEvents(new SpawnEvent(), this);
	}
	public void save() {
		messages.add("Spawn setado", "&bO spawn foi setado!");
		messages.add("Spawn", "&6Voce foi teleportado para o Spawn!");
		messages.add("Sem spawn", "&cO Spawn nao foi setado!");
		messages.saveDefault();
	}

}
