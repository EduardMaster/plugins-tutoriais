
package net.eduard.misterybox;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.misterybox.command.TemplateCommand;
import net.eduard.misterybox.event.TemplateEvent;

public class Main extends JavaPlugin implements Listener {
	public static Main plugin;
	public static FileConfiguration config;

	@Override
	public void onEnable() {
		plugin = this;
		config = plugin.getConfig();
		saveDefaultConfig();
		{
			config.addDefault("valor3", "valor4");
			config.options().copyDefaults(true);
			saveConfig();
		}
		
		getCommand("comando").setExecutor(new TemplateCommand());
		Bukkit.getPluginManager().registerEvents(new TemplateEvent(), this);
	}
	public static ItemStack newItem(String name,Material material,String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public void onDisable() {

	}

}
