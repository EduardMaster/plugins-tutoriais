package net.eduard.parkour.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.eduard.parkour.Main;
import net.eduard.parkour.utils.Loc;
import net.eduard.parkour.utils.SimpleScore;

public class Parkour implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private static Map<String, Parkour> parkours = new HashMap<>();
	private static Map<Player, Location> checkpoints = new HashMap<>();
	private static Map<Player, SimpleScore> scores = new HashMap<>();
	private static Map<Player, Integer> falls = new HashMap<>();
	private static Map<Player, Integer> time = new HashMap<>();
	private static Map<Player, Parkour> playing = new HashMap<>();
	private static Map<Player, ItemStack[]> lastItens = new HashMap<>();
	private static Location lobby;
	private static Map<Player, Location> lastLocation = new HashMap<>();

	public static Parkour createParkour(String name) {
		Parkour par = new Parkour(name);
		parkours.put(name, par);
		return par;
	}

	public static Parkour getParkour(Player p) {
		return playing.get(p);
	}

	public static void deleteParkour(String name) {
		parkours.remove(name);
	}

	public static boolean existsParkour(String name) {
		return parkours.containsKey(name);
	}

	public static Parkour getParkour(String name) {
		return parkours.get(name);
	}

	public static boolean isInGame(Player p) {
		return playing.containsKey(p);
	}

	private String name;
	private transient List<Player> players = new ArrayList<>();
	private Material checkpointTester;

	public boolean enabled() {
		return checkpointTester != null && end != null && spawn != null;
	}

	private Loc spawn, end;

	public Parkour(String name) {
		setName(name);
	}

	public Material getCheckpointTester() {
		return checkpointTester;
	}

	public Loc getEnd() {
		return end;
	}

	public String getName() {
		return name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Loc getSpawn() {
		return spawn;
	}

	public void setCheckpointTester(Material checkpointTester) {
		this.checkpointTester = checkpointTester;
	}

	public void setEnd(Loc end) {
		this.end = end;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setSpawn(Loc spawn) {
		this.spawn = spawn;
	}

	public static Map<Player, Parkour> getPlaying() {
		return playing;
	}

	public static void setPlaying(Map<Player, Parkour> playing) {
		Parkour.playing = playing;
	}

	public static Map<Player, ItemStack[]> getLastItens() {
		return lastItens;
	}

	public static void setLastItens(Map<Player, ItemStack[]> lastItens) {
		Parkour.lastItens = lastItens;
	}

	public static Map<String, Parkour> getParkours() {
		return parkours;
	}

	public static void setParkours(Map<String, Parkour> parkours) {
		Parkour.parkours = parkours;
	}

	public void join(Player p) {
		players.add(p);
		playing.put(p, this);
		lastItens.put(p, p.getInventory().getContents());
		lastLocation.put(p, p.getLocation());
		falls.put(p, 0);
		time.put(p, 0);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		p.setExhaustion(0);
		p.setSaturation(20);
		p.setAllowFlight(false);
		p.setGameMode(GameMode.SURVIVAL);
		p.teleport(spawn.getLocation());
		Main.updateScoreboard();
	}

	public static Map<Player, Location> getCheckpoints() {
		return checkpoints;
	}

	public static void setCheckpoints(Map<Player, Location> checkpoints) {
		Parkour.checkpoints = checkpoints;
	}

	public static Map<Player, Integer> getFalls() {
		return falls;
	}

	public static void setFalls(Map<Player, Integer> falls) {
		Parkour.falls = falls;
	}

	public static Map<Player, Integer> getTime() {
		return time;
	}

	public static void setTime(Map<Player, Integer> time) {
		Parkour.time = time;
	}

	@SuppressWarnings("deprecation")
	public static void leave(Player p) {
		Parkour par = getParkour(p);
		par.getPlayers().remove(p);
		p.teleport(lastLocation.get(p));
		p.getInventory().setContents(lastItens.get(p));
		p.updateInventory();
		p.sendMessage(ChatColor.RED + "Voce desistiu do parkor!");
		Bukkit.broadcastMessage(ChatColor.GOLD + "O jogador " + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GOLD
				+ " desistiu de jogar o parkour " + ChatColor.AQUA + par.getName());
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		playing.remove(p);
		scores.remove(p);
		lastItens.remove(p);
		lastLocation.remove(p);
		falls.remove(p);
		time.remove(p);

	}

	public static Location getLobby() {
		return lobby;
	}

	public static void setLobby(Location lobby) {
		Parkour.lobby = lobby;
	}

	public static Map<Player, Location> getLastLocation() {
		return lastLocation;
	}

	public static void setLastLocation(Map<Player, Location> lastLocation) {
		Parkour.lastLocation = lastLocation;
	}

	public static Map<Player, SimpleScore> getScores() {
		return scores;
	}

	public static void setScores(Map<Player, SimpleScore> scores) {
		Parkour.scores = scores;
	}

}
