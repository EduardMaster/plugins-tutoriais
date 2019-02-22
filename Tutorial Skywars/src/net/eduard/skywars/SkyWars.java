
package net.eduard.skywars;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class SkyWars extends BukkitRunnable {

	public enum State {
			STARTING, PLAYING, RESTARTING
	}

	private String name;

	private int time;

	private State state;

	private boolean enabled;

	private int timeIntoStart;

	private int timeIntoRestart;

	private int timeIntoGameOver;

	private int minPlayersAmount;

	private int maxPlayersAmount;

	private ArrayList<Location> spawns = new ArrayList<>();

	private ArrayList<Player> players = new ArrayList<>();

	private ArrayList<Player> specs = new ArrayList<>();

	private Location lobby;

	public SkyWars(String name) {
		setName(name);
		setState(State.STARTING);
		setTimeIntoGameOver(60 * 10);
		setTimeIntoRestart(20);
		setTimeIntoStart(60 * 2);
		setMinPlayersAmount(2);
		setMaxPlayersAmount(8);
	}

	public  SkyWars() {
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getTime() {

		return time;
	}

	public void setTime(int time) {

		this.time = time;
	}

	public State getState() {

		return state;
	}

	public void setState(State state) {

		this.state = state;
	}

	public boolean isEnabled() {

		return enabled;
	}

	public void setEnabled(boolean enabled) {

		this.enabled = enabled;
	}

	public int getTimeIntoStart() {

		return timeIntoStart;
	}

	public void setTimeIntoStart(int timeIntoStart) {

		this.timeIntoStart = timeIntoStart;
	}

	public int getTimeIntoRestart() {

		return timeIntoRestart;
	}

	public void setTimeIntoRestart(int timeIntoRestart) {

		this.timeIntoRestart = timeIntoRestart;
	}

	public int getTimeIntoGameOver() {

		return timeIntoGameOver;
	}

	public void setTimeIntoGameOver(int timeIntoGameOver) {

		this.timeIntoGameOver = timeIntoGameOver;
	}

	public Location getLobby() {

		return lobby;
	}

	public void setLobby(Location lobby) {

		this.lobby = lobby;
	}

	public ArrayList<Location> getSpawns() {

		return spawns;
	}

	public void setSpawns(ArrayList<Location> spawns) {

		this.spawns = spawns;
	}

	public String getStats() {

		return ChatColor
			.translateAlternateColorCodes('&',
				Main.main.getConfig().getString("stats"))
			.replace("$amount", "" + getPlayers().size())
			.replace("$max", "" + getMaxPlayersAmount());
	}

	@Override
	public void run() {

		for (SkyWars skywar : Main.skywars) {
			if (skywar.isEnabled()) {
				if (skywar.getPlayers().size() >= skywar.getMinPlayersAmount()) {
					skywar.setTime(getTime() - 1);
					if (skywar.getState() == State.STARTING) {
						if (skywar.getTime() == 0) {

						} else {
							if (skywar.getTime() <=10|skywar.getTime() %30==0|skywar.getTime() %20==0) {
								Bukkit
								.broadcastMessage(ChatColor
									.translateAlternateColorCodes('&',
										Main.main.getConfig()
											.getString("messages.join"))
									.replace("$stats", getStats()));
							}
						
						}
					}

				}
			}
		}

	}

	public ArrayList<Player> getPlayers() {

		return players;
	}

	public void setPlayers(ArrayList<Player> players) {

		this.players = players;
	}

	public ArrayList<Player> getSpecs() {

		return specs;
	}

	public void setSpecs(ArrayList<Player> specs) {

		this.specs = specs;
	}

	public int getMinPlayersAmount() {

		return minPlayersAmount;
	}

	public void setMinPlayersAmount(int minPlayersAmount) {

		this.minPlayersAmount = minPlayersAmount;
	}

	public int getMaxPlayersAmount() {

		return maxPlayersAmount;
	}

	public void setMaxPlayersAmount(int maxPlayersAmount) {

		this.maxPlayersAmount = maxPlayersAmount;
	}

}
