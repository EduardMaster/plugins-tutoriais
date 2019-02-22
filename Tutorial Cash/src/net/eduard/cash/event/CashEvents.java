package net.eduard.cash.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.eduard.cash.manager.CashManager;

public class CashEvents implements Listener {
	@EventHandler
	public void event(PlayerJoinEvent e){
		CashManager.getCash().getCash(e.getPlayer());
	}
}
