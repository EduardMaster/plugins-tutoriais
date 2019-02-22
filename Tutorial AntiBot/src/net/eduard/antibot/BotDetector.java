
package net.eduard.antibot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;
/*
 * Não foi testado
 * 
 */
public class BotDetector implements Listener {

	public BotDetector() {
	new BukkitRunnable() {
			
			@Override
			public void run() {
				test();
			}
		}.runTaskTimerAsynchronously(Main.plugin,20,20);
	}

	private int contagem = 0;

	private boolean whitelist = false;

	@EventHandler
	private void onBotTryToJoin(AsyncPlayerPreLoginEvent e) {

		if (Main.ips_proxy.contains(e.getAddress().getHostAddress())) {
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Main.config.message("Bot").replace("$ender", "/n"));
		}
		if (whitelist) {
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Main.config.message("Bot").replace("$enter", "/n"));
			return;
		}

		++contagem;
	}

	public void test() {
		if (contagem > 6) {
			whitelist = true;
			new BukkitRunnable() {
				
				@Override
				public void run() {
					whitelist = false;
					
				}
			}.runTaskLaterAsynchronously(Main.plugin, 10);
		}
		contagem = 0;
	}

}
