package net.eduard.admin.events;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.eduard.admin.Main;

public class VerInfo implements Listener {
	@EventHandler
	public void event(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		if (p.getItemInHand().getType() == Material.BLAZE_ROD)
			if (Main.admins.contains(p))
				if (e.getRightClicked() instanceof Player) {
					Player target = (Player) e.getRightClicked();
					e.setCancelled(true);
					p.sendMessage("§6Informações do " + target.getDisplayName());
					p.sendMessage("§aKills §2" + target.getStatistic(Statistic.PLAYER_KILLS));
					p.sendMessage("§aMortes §2" + target.getStatistic(Statistic.DEATHS));
				}
	}
}
