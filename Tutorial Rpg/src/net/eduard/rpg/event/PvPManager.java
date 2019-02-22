
package net.eduard.rpg.event;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import net.eduard.rpg.manager.Stat;
import net.eduard.rpg.manager.Stats;

public class PvPManager implements Listener {
	public static boolean acceptNormalDamages = true;
	public static boolean acceptNormalCriticalHit = false;

	private static double getResult(double damage, LivingEntity damager, LivingEntity entity) {
		// 2
		//

		Stats atacker = Stats.getStats(damager);
		Stats defenser = Stats.getStats(entity);
		Random random = new Random();
		if (!acceptNormalCriticalHit) {
			damage = Stats.getItemDamage(damager.getEquipment().getItemInHand());
		}
		if (!acceptNormalDamages) {
			damage = 0;
		}

		double critChance = Stat.critChance.getResult(atacker);
		double crit = Stat.crit.getResult(atacker);
		double evasionChance = Stat.evasionChance.getResult(defenser);
		double shield = Stat.shield.getResult(defenser);
		double shieldChance = Stat.shieldChance.getResult(defenser);
		double armour = Stat.armour.getResult(defenser);
		double health = entity.getHealth();
		damage += atacker.getDamage();

		if (random.nextDouble() <= critChance) {
			damage = damage * (2 + (crit));
		}
		if (random.nextDouble() <= (evasionChance)) {
			return -1;
		}
		if (random.nextDouble() <= (shieldChance)) {
			damage -= damage * (shield);
		}
		damage -= damage * (armour);
		double calc = health - damage;
		if (calc <= 0) {
			return 0;
		} else {
			return calc;
		}
	}

	public static boolean isOnCooldown(LivingEntity damager) {
		long time = System.currentTimeMillis();
		if (attackCooldown.containsKey(damager)) {
			long value = attackCooldown.get(damager);
			double speed = Stats.getStats(damager).getAttacksPerSecond();
			if (((time - value) / 1000D) < (speed / (speed * speed))) {
				return true;
			} else {
				attackCooldown.put(damager, time);
				return false;
			}
		} else {
			attackCooldown.put(damager, time);
			return false;
		}
	}

	public static HashMap<LivingEntity, Long> attackCooldown = new HashMap<>();

	@EventHandler
	public void event(EntityDeathEvent e) {
		if (attackCooldown.containsKey(e.getEntity())) {
			attackCooldown.remove(e.getEntity());
		}
	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {

			LivingEntity entity = (LivingEntity) e.getEntity();
			LivingEntity damager = null;
			if (e.getDamager() instanceof LivingEntity) {
				damager = (LivingEntity) e.getDamager();
			} else if (e.getDamager() instanceof Projectile) {
				Projectile projectile = (Projectile) e.getDamager();
				if (projectile.getShooter() instanceof Player) {
					Player p = (Player) projectile.getShooter();
					damager = p;
				} else if (projectile.getShooter() instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) projectile.getShooter();
					damager = livingEntity;
				}
			}
			if (isOnCooldown(damager)) {
				e.setCancelled(true);
				return;
			}
			double result = getResult(e.getDamage(), damager, entity);
			e.setDamage(0);
			if (entity.getHealth() == 0) {
				return;
			}
			if (result == 0) {
				e.setDamage(10000000);
			} else if (result > 0D) {
				entity.setHealth(result);
			}
		}

	}

}
