package net.eduard.tutorial.login;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Eventos implements Listener{
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!LoginAPI.estaRegistrar(p)) {
			p.sendMessage("§aRegistre-se por favor: /register <senha> <confirma>");
		}else {
			p.sendMessage("§aLogue-se por favor: /login <senha>");
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!LoginAPI.estaLogado(p)) {
					p.kickPlayer("§cVocê demorou para entrar");
				}
			}
		}.runTaskLater(Login.getInstance(), 20*15);
	}
	

	@EventHandler
	public void naoMover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!LoginAPI.estaLogado(p)) {
			p.teleport(e.getFrom());
		}
	}
	@EventHandler
	public void naoLevarDano(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (!LoginAPI.estaLogado(p)) {
				e.setCancelled(true);
			}
		}
	}
	
}
