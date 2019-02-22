package net.eduard.simplewarp;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.eduard.simplewarp.manager.Warp;

public class WarpListener implements Listener {

	@EventHandler
	public void event(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String text = e.getMessage();
		Collection<Warp> lista = Main.getWarps();
		String cmd = text;
		if (cmd.contains(" ")) {
			cmd = text.split(" ")[0];
		}
		cmd = cmd.replaceFirst("/", "");
		for (Warp warp:lista){
			if (warp.getName().equalsIgnoreCase(cmd)){
				e.setCancelled(true);
				p.chat("/warp " + cmd);
			}
		}
		

	}
}
