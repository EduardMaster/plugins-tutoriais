package me.eduard.simples;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	private static Main plugin;
	public void onEnable()	{
		plugin = this;
		Licence.BukkitTester.test(this, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getPluginManager().registerEvents(plugin, plugin);
				
			}
		});
		
	}
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§cNao deu certo");
	}
}
