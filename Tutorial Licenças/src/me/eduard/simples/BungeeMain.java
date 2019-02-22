package me.eduard.simples;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin implements Listener{
	private static BungeeMain plugin;
	public void onEnable() {
		plugin = this;
		Licence.BungeeTester.test(this, new Runnable() {

			public void run() {
				//
				BungeeCord.getInstance().getPluginManager().registerListener(plugin, plugin);
				BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("§aPlugin ativado com sucesso"));
			}
		});
	}
}
