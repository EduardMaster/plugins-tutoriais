package net.eduard.kitpvp.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.eduard.kitpvp.manager.KitPvP;

public class PlayerListener implements Listener {

	@EventHandler
	public void event(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setGameMode(GameMode.SURVIVAL);
		p.teleport(KitPvP.spawn);
		p.setHealth(p.getMaxHealth());
		KitPvP.giveItems(p);
		KitPvP.players.put(p, KitPvP.kitNone);

	}
	@EventHandler
	public void event(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player killer = p.getKiller();
		if (killer != null) {
			e.setDeathMessage("");
		}
		KitPvP.removeKit(p);

	}

	@EventHandler
	public void event(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (!KitPvP.hasKit(p)) {
				e.setCancelled(true);
			}

		}
	}
	@EventHandler
	public void event(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (!KitPvP.hasKit(p)) {
				e.setCancelled(true);
			}

		}
	}
	@EventHandler
	public void event(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		KitPvP.removeKit(p);

	}
	@EventHandler
	public void event(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		e.setRespawnLocation(KitPvP.spawn);
		KitPvP.giveItems(p);
	}
}
