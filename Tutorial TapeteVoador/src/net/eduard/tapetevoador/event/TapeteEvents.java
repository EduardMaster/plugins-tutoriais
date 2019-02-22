package net.eduard.tapetevoador.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import net.eduard.tapetevoador.Main;

public class TapeteEvents implements Listener{
	
	@EventHandler
	public void event(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FALL) {
			Player p = (Player) e.getEntity();
			if (Main.players.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void event(PlayerDeathEvent e) {

		Player p = e.getEntity();
		if (Main.players.contains(p)) {
			if (p.getGameMode() == Main.getGamemode()) {
				Main.reset(p);
			}
		}

	}

	@EventHandler
	public void event(PlayerGameModeChangeEvent e) {

		Player p = e.getPlayer();
		if (Main.players.contains(p)) {
			if (p.getGameMode() == Main.getGamemode()) {
				Main.reset(p);
			}
		}
	}
	@EventHandler
	public void event(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if (Main.players.contains(p)) {
			Main.reset(p);
			Main.players.remove(p);
		}
	}

	@EventHandler
	public void event(PlayerTeleportEvent e) {

		Player p = e.getPlayer();
		if (Main.players.contains(p)) {
			if (p.getGameMode() == Main.getGamemode()) {
				Main.reset(p);
			}
		}
	}
	@EventHandler
	public void event(PlayerMoveEvent e) {

		Player p = e.getPlayer();

		if (!e.getTo().getBlock().equals(e.getFrom().getBlock())) {
			if (Main.players.contains(p)) {
				if (p.isDead()) {
					return;
				}
				if (p.getGameMode() == Main.getGamemode()) {
					if (p.isSneaking()) {
						if (e.getFrom().getY() < e.getTo().getY()) {
							e.setCancelled(true);
							return;
						}
					}
					List<Block> listDeBlocosDeletados = new ArrayList<>();
					List<Block> listaDeBlocoFrom = Main.getTapeteBlocks(e.getFrom());
					List<Block> listaDeBlocoTo = Main.getTapeteBlocks(e.getTo());

					for (Block block : listaDeBlocoFrom) {
						if (!listaDeBlocoTo.contains(block)) {
							listDeBlocosDeletados.add(block);
						}
					}
					for (Block block : listDeBlocosDeletados) {
						if (block.getType() == Main.getMaterial()) {
							block.setType(Material.AIR);
						}
					}
					for (Block block : listaDeBlocoTo) {
						if (block.getType() == Material.AIR) {
							block.setType(Main.getMaterial());
						}
					}
				}
			}
		}
	}
}
