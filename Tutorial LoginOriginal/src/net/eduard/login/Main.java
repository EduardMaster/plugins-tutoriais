package net.eduard.login;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin implements Listener {
	@Override
	public void onEnable() {
		BungeeCord.getInstance().getPluginManager().registerListener(this,
				new Login());

	}
	
}
