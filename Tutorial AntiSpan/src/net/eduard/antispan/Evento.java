package net.eduard.antispan;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Evento implements Listener {
	public static HashMap<Player, Long> delay = new HashMap<>();
	public static HashMap<Player, Long> delay2 = new HashMap<>();
	@EventHandler
	public void event(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("comando.rapido")) {
			if (delay.containsKey(p)) {
				Long teste = delay.get(p);
				long agora = System.currentTimeMillis();
				boolean test = agora > (teste+1000*3);
				if (!test) {
					p.sendMessage("§cEspere um momento para digitar o comando novamente!");
					e.setCancelled(true);
				
				}else {
					delay.put(p,System.currentTimeMillis());
				}
			}else {
				delay.put(p,System.currentTimeMillis());
			}
		}
	}
	@EventHandler
	public void event(AsyncPlayerChatEvent e ) {
		Player p = e.getPlayer();
		if (!p.hasPermission("chat.rapido")) {
			if (delay2.containsKey(p)) {
				Long teste = delay2.get(p);
				long agora = System.currentTimeMillis();
				boolean test = agora > (teste+1000*1);
				if (!test) {
					p.sendMessage("§cEspere um momento para digitar no chat novamente!");
					e.setCancelled(true);
				
				}else {
					delay2.put(p,System.currentTimeMillis());
				}
			}else {
				delay2.put(p,System.currentTimeMillis());
			}
		}
	}
}
