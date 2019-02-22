
package net.eduard.toquesuave;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.api.lib.Mine;
import net.eduard.api.server.EduardPlugin;
import net.eduard.toquesuave.command.ToqueSuaveCommand;
import net.eduard.toquesuave.event.ToqueSuaveEvents;

public class Main extends EduardPlugin  {
	private static Main plugin;
	public static Main getInstance() {
		return plugin;
	}
	public static Main getPlugin() {
		return JavaPlugin.getPlugin(Main.class);
	}
	@Override
	public void onEnable() {
		plugin = this;
		new ToqueSuaveCommand().register();
		new ToqueSuaveEvents().register(this);
		ItemStack item = Mine.newItem(Material.DIAMOND_PICKAXE, "§aToque Suave");
		Mine.addEnchant(item, Enchantment.SILK_TOUCH, 2);
		config.add("ToqueSuave", item);
		config.saveConfig();
		
	}

	@Override
	public void onDisable() {
	}

}
