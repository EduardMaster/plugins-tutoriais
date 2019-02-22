package net.eduard.cash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.cash.command.CashCommand;
import net.eduard.cash.event.CashEvents;

public class Cash extends JavaPlugin{

	@Override
	public void onEnable() {

		Bukkit.getPluginManager().registerEvents(new CashEvents(), this);
		getCommand("cash").setExecutor(new CashCommand());
		
	}
}
