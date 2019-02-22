package net.eduard.rpg.manager;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Stats implements Serializable, Cloneable {
	public static HashMap<LivingEntity, Double> lifes = new HashMap<>();
	public static HashMap<EntityType, Stats> stats = new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static double getItemDamage(ItemStack itemStack) {
		if (itemStack == null)
			return 0;
		double damage = 0;
		String name = itemStack.getType().name();
		for (int id = 0; id <= 4; id++) {
			String value = "";
			if (id == 0) {
				value = "DIAMOND_";
				damage += 3;
			}
			if (id == 1) {
				value = "IRON_";
				damage += 2;
			}
			if (id == 2) {
				value = "GOLD_";
			}
			if (id == 3) {
				value = "STONE_";
				damage++;
			}
			if (id == 4) {
				value = "WOOD_";
			}

			for (int x = 0; x <= 3; x++) {
				double newDamage = damage;
				if (x == 0) {
					value = "SWORD";
					newDamage += 4;
				}
				if (x == 1) {
					value = "AXE";
					newDamage += 3;
				}
				if (x == 2) {
					value = "PICKAXE";
					newDamage += 2;
				}
				if (x == 3) {
					value = "SPADE";
					newDamage++;
				}

				if (name.equals(value)) {
					return newDamage;
				}
			}
			damage = 0;
		}
		return damage;
	}

	@Override
	public Stats clone() {
		try {
			return (Stats) super.clone();
		} catch (Exception ex) {
		}

		return null;
	}

	public static int getLevel(LivingEntity entity) {
		if (entity instanceof Player) {
			Player player = (Player) entity;
			return player.getLevel();
		}
		return (int) (entity.getLocation().distance(entity.getWorld().getSpawnLocation())
				/ Stat.forEachDistanceUpLevel);

	}

	public void acumulate(Stats stat) {
		try {
			for (Field field : getClass().getDeclaredFields()) {
				if (field.getType() == Double.TYPE) {
					field.setAccessible(true);
					field.setDouble(this, field.getDouble(stat) + field.getDouble(this));
				}
			}
		} catch (Exception ex) {
		}

	}

	public static Stats getStats(LivingEntity entity) {
		Stats value = stats.get(entity.getType());
		Stats stat = new Stats();
		int level = getLevel(entity);
		for (int x = level; x <= level; x++) {
			stat.acumulate(value);
		}
		if (entity.getEquipment() != null) {
			for (ItemStack equip : entity.getEquipment().getArmorContents()) {
				if (equip != null) {
					stat.acumulate(new ItemStats(equip));
				}
			}
			ItemStack item = entity.getEquipment().getItemInHand();
			if (item != null) {
				stat.acumulate(new ItemStats(item));
			}

		}
		stat.setLevel(level);
		return stat;
	}

	private double health;
	private double level;
	private double attacksPerSecond;
	private double damage;
	private double armour;
	private double critChance;
	private double crit;
	private double shieldChance;
	private double shield;
	private double evasionChance;
	private double healthRegeneration;

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public double getAttacksPerSecond() {
		return attacksPerSecond;
	}

	public void setAttacksPerSecond(double attacksPerSecond) {
		this.attacksPerSecond = attacksPerSecond;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getArmour() {
		return armour;
	}

	public void setArmour(double armour) {
		this.armour = armour;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public double getCrit() {
		return crit;
	}

	public void setCrit(double crit) {
		this.crit = crit;
	}

	public double getShieldChance() {
		return shieldChance;
	}

	public void setShieldChance(double shieldChance) {
		this.shieldChance = shieldChance;
	}

	public double getShield() {
		return shield;
	}

	public void setShield(double shield) {
		this.shield = shield;
	}

	public double getEvasionChance() {
		return evasionChance;
	}

	public void setEvasionChance(double evasionChance) {
		this.evasionChance = evasionChance;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getHealthRegeneration() {
		return healthRegeneration;
	}

	public void setHealthRegeneration(double healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}

}
