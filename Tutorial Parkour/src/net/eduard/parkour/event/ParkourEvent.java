
package net.eduard.parkour.event;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.parkour.manager.Parkour;

public class ParkourEvent implements Listener {

	@EventHandler
	public void event(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!e.getFrom().getBlock().getLocation().equals(e.getTo().getBlock().getLocation())) {
			if (Parkour.isInGame(p)) {
				if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Parkour.getParkour(p)
						.getCheckpointTester()) {
					if (Parkour.getCheckpoints().containsKey(p)) {
						if (!e.getTo().getBlock().getLocation()
								.equals(Parkour.getCheckpoints().get(p).getBlock().getLocation())) {
							p.sendMessage(ChatColor.GOLD + "Checkpoint encontrado!");
						}

					} else {
						p.sendMessage(ChatColor.GOLD + "Checkpoint encontrado!");
					}
					Parkour.getCheckpoints().put(p, e.getTo());
				}
				if (e.getTo().getBlock().getLocation()
						.equals(Parkour.getParkour(p).getEnd().getLocation().getBlock().getLocation())) {
					
				}
			}

		}
	}

	@EventHandler
	public void event(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Parkour.isInGame((Player) e.getEntity())) {
				e.setCancelled(true);
				if (e.getCause() == DamageCause.FALL) {
					Integer value = Parkour.getFalls().get(p);
					value++;
					Parkour.getFalls().put(p, value);
					if (Parkour.getCheckpoints().containsKey(p)) {
						p.teleport(Parkour.getCheckpoints().get(p));
					} else {
						p.teleport(Parkour.getParkour(p).getSpawn().getLocation());
					}
				}
			}
		}

	}

	@EventHandler
	public void event(PlayerInteractEvent e) {
		if (Parkour.isInGame(e.getPlayer()))
			if (e.getAction() != Action.PHYSICAL)
				e.setCancelled(true);
	}

	@EventHandler
	public void event(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (Parkour.isInGame(p)) {
			e.setCancelled(true);
			if (e.getMessage().startsWith("/leave")) {
				Parkour.leave(p);
			} else {
				p.sendMessage(ChatColor.GOLD + "Voce só pode usar /leave quando estiver praticando parkour");
			}
		}

	}

	@EventHandler
	public void event(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player)
			if (Parkour.isInGame((Player) e.getEntity()))
				e.setCancelled(true);
	}
}
