package net.eduard.kitpvp.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.eduard.kitpvp.manager.Kit;

public class Poseidon extends Kit {

	public Poseidon() {
		super("Poseidon");
		register();

	}
	@EventHandler
	public void event(PlayerMoveEvent e) {
		Player p = e.getPlayer();	
		Material block = e.getTo().getBlock().getRelative(BlockFace.DOWN)
				.getType();
		if (block == Material.WATER || block == Material.STATIONARY_WATER) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,
					20 * 5, 0), true);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
					20 * 5, 1), true);
			p.playSound(p.getLocation(), Sound.WATER, 2.0F, 2.0F);
		}
	}

}
