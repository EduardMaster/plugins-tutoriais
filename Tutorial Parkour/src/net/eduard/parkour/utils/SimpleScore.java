package net.eduard.parkour.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class SimpleScore {

	public SimpleScore setScoreboard(Player player) {
		player.setScoreboard(score);
		return this;
	}
	public Scoreboard getScore() {
		return score;
	}
	public String getTitle() {
		return board.getDisplayName();
	}
	
	private Scoreboard score;
	private Objective board;
	private HashMap<Integer, OfflinePlayer> lines = new HashMap<>();
	private Objective health;
	public SimpleScore(String title) {
		score = Bukkit.getScoreboardManager().getNewScoreboard();
		board = score.registerNewObjective("Scoreboard", "Mine");
		board.setDisplaySlot(DisplaySlot.SIDEBAR);
		health = score.registerNewObjective("HealthBar", "health");
		health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		setTitle(title);
	}
	public SimpleScore set(int slot,String display) {
		slot = getSlot(slot);
		remove(slot);
		OfflinePlayer text = getText(display);
		board.getScore(text).setScore(slot);
		lines.put(slot, text);
		return this;
	}
	public SimpleScore empty(int slot) {
		slot = getSlot(slot);
		remove(slot);
		board.getScore(getText(getEmpty(slot))).setScore(slot);
		return this;
	}
	private int getSlot(int slot) {
		if (slot<1) {
			slot = 1;
		}
		if (slot >15) {
			slot = 15;
		}
		return slot;
	}
	private String getEmpty(int slot) {
		
		return " "+ChatColor.values()[getSlot(slot)-1];
	}
	public SimpleScore remove(int slot) {
		if (lines.containsKey(slot)) {
			score.resetScores(lines.get(slot));
		}
		return this;
	}
	public SimpleScore setHealthDisplay(String display) {
		health.setDisplayName(text(display,32));
		return this;
	}
	public SimpleScore setTitle(String title) {
		board.setDisplayName(text(title,32));
		return this;
	}
	private OfflinePlayer getText(String text) {
		@SuppressWarnings("deprecation")
		OfflinePlayer value = Bukkit.getOfflinePlayer(text(text,16));
		return value;
	}
	private String text(String text,int size) {
		return text.length()>size?text.substring(0, size):text;
	}
}
