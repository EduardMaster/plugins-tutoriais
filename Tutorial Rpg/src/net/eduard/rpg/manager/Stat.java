package net.eduard.rpg.manager;

import java.lang.reflect.Field;

public enum Stat {
	level("§bNivel: §3"), damage("§6Dano: §e+"), attacksPerSecond("§6Ataques por segundo: §e+"),

	critChance("§6Chance de Criticar: §e+", 0.75, 9, 0.2), crit("§6Força do Critico: §e+", 10, 18, 2.0), health(
			"§aVida: §2+"), healthRegeneration("§aVida: §2+"), evasionChance("§gChance de Desviar: §9+", 0.5, 7,
					0.25), shieldChance("§aChance de Bloquear: §2+", 0.6, 13, 0.15), shield("§aForça do Bloqueio: §2+",
							0.9, 25, 0.3), armour("§bArmadura: §3+", 0.95, 30, 0.5),

	;

	private String description;
	private boolean percent;
	private double maxBonus;
	private double amountRequiredPerLevel;
	private double bonusPerLevel;
	public static String messagePercent = "%";
	public static double forEachDistanceUpLevel = 50;

	private Stat(String description, double maxBonus, double amountRequiredPerLevel, double bonusPerLevel) {
		this(description, false);
		setMaxBonus(maxBonus);
		setBonusPerLevel(bonusPerLevel);
		setAmountRequiredPerLevel(amountRequiredPerLevel);
	}

	private Stat(String description) {
		this(description, false);
	}

	private Stat(String description, boolean percent) {
		setDescription(description);
		setPercent(percent);
	}

	public double getResult(double level, double value) {
		if (level<=0) {
			level = 1;
		}
		if (value <= 0) {
			value = 1;
		}
		double result = bonusPerLevel * (value / (level * amountRequiredPerLevel));
		return result >= maxBonus ? maxBonus : result;
	}

	public double getResult(Stats stats) {
		double level = stats.getLevel();
		double value = 1;
		try {
			Field field = stats.getClass().getField(name());
			field.setAccessible(true);
			value = field.getDouble(stats);
		} catch (Exception ex) {
		}

		return getResult(level, value);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPercent() {
		return percent;
	}

	public void setPercent(boolean percent) {
		this.percent = percent;
	}

	public double getBonusPerLevel() {
		return bonusPerLevel;
	}

	public void setBonusPerLevel(double bonusPerLevel) {
		this.bonusPerLevel = bonusPerLevel;
	}

	public double getAmountRequiredPerLevel() {
		return amountRequiredPerLevel;
	}

	public void setAmountRequiredPerLevel(double amountRequiredPerLevel) {
		this.amountRequiredPerLevel = amountRequiredPerLevel;
	}

	public double getMaxBonus() {
		return maxBonus;
	}

	public void setMaxBonus(double maxBonus) {
		this.maxBonus = maxBonus;
	}

}
