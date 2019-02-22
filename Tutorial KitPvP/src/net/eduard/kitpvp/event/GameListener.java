package net.eduard.kitpvp.event;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GameListener implements Listener {

	@EventHandler
	public void event(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			e.setCancelled(true);
			e.setFoodLevel(20);
			p.setSaturation(20);
		}

	}
	@EventHandler
	public void event(BlockPlaceEvent e) {
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
			e.setCancelled(true);
	}
	@EventHandler
	public void event(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
			e.setCancelled(true);
	}
	@EventHandler
	public void event(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void event(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getMaterial() == Material.MUSHROOM_SOUP
				&& p.getHealth() + 3 < p.getMaxHealth()) {
			double result = p.getHealth() + 7;
			p.setHealth(result > p.getMaxHealth() ? p.getMaxHealth() : result);
			e.setCancelled(true);
			p.getItemInHand().setType(Material.BOW);
		}

	}

}
