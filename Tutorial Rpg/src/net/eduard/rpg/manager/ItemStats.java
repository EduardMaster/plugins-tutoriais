package net.eduard.rpg.manager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStats extends Stats {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient ItemStack item;

	public ItemStats(ItemStack item) {
		setItem(item);
		if (item != null) {
			if (item.hasItemMeta()) {
				ItemMeta meta = item.getItemMeta();
				if (meta.hasLore()) {
					for (String line : meta.getLore()) {
						for (Stat type : Stat.values()) {
							if (line.contains(type.getDescription())) {
								String rest = line.replace(type.getDescription(), "");
								Double value = 0D;
								if (type.isPercent()) {
									rest = rest.replace(Stat.messagePercent, "");
									value = Double.valueOf(rest) / 100D;
								} else {
									value = Double.valueOf(rest);
								}
								try {
									Field field = getClass().getSuperclass().getDeclaredField(type.name());
									field.setAccessible(true);
									field.setDouble(this, Double.valueOf(value));
								} catch (Exception ex) {
								}

							}
						}
					}
				}
			}
		}
	}

	public ItemStack getItemModified() {
		if (item==null) {
			return new ItemStack(Material.STONE);
		}
		ItemStack clone = item.clone();
		ItemMeta meta = clone.getItemMeta();
		List<String> lore = new ArrayList<>();
		for (Stat type : Stat.values()) {
			try {
				String line = type.getDescription();
				Double value = 0D;
				Field field = getClass().getSuperclass().getDeclaredField(type.name());
				field.setAccessible(true);
				value = field.getDouble(this);

				if (value == 0) {
					continue;
				}

				if (type.isPercent()) {
					value *= 100;
					line += "" + value + Stat.messagePercent;
				} else {
					line += "" + value;
				}
				lore.add(line);
			} catch (Exception ex) {
				ex.printStackTrace();
				continue;
			}
		}
		meta.setLore(lore);
		clone.setItemMeta(meta);
		return clone;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
