package net.eduard.permission.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.eduard.permission.Main;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e	){
		Main.getInstance().setupPermission(e.getPlayer());
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Main.getInstance().unsetPermission(e.getPlayer());
	}
	@EventHandler
	public void onKick(PlayerKickEvent e){
		Main.getInstance().unsetPermission(e.getPlayer());
	}
}
