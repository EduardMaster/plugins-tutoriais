package net.eduard.kitpvp.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Kit implements Listener {

	private String title;
	private String name;
	private List<String> lore = new ArrayList<>();
	private List<ItemStack> items = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private Material icon=Material.AIR;
	private int data;
	private boolean glow;
	public Kit(String name) {
		this.name = name;
		this.title = name;
	}
	public void register() {
		KitPvP.kits.put(name, this);
	}
	
	public boolean has(Player player){
		return getPlayers().contains(player);
	}
	
	public void setLore(String... lore){
		this.lore = Arrays.asList(lore);
	}
	public void setName(String name){
		this.name = name;
	}
	public void setData(int data){
		this.data = data;
	}
	

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}

	public List<ItemStack> getItems() {
		return items;
	}

	public void setItems(List<ItemStack> items) {
		this.items = items;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isGlow() {
		return glow;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}

	public String getName() {
		return name;
	}

	public int getData() {
		return data;
	}

	public void setIcon(Material icon) {
		this.icon = icon;
	}

	public ItemStack getIcon() {
		ItemStack item = new ItemStack(icon,1,(short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(title);
		meta.setLore(lore);
		return item;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
