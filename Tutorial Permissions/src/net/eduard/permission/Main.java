package net.eduard.permission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.permission.command.GroupMasterCommand;
import net.eduard.permission.event.PlayerListener;
import net.eduard.permission.setup.MGroup;
import net.eduard.permission.setup.MPlayer;

public class Main extends JavaPlugin {

	private Map<String, MGroup> groups = new HashMap<>();
	private Map<UUID, MPlayer> players = new HashMap<>();
	private Map<UUID, PermissionAttachment> permissions = new HashMap<>();
	private static Main plugin;
	@Override
	public void onLoad() {
		saveResource("groups.yml", false);
		// loadGroups();
		// loadPlayers();
	}
	public PermissionAttachment att;
	
	public void teste	(){
//		getServer().getServicesManager().register(PermissionManager.class, this.permissionsManager, this, ServicePriority.Normal);
//		 
//		getServer().getServicesManager().unregister(PermissionManager.class, this.permissionsManager);
	}
	// COM FALHAS (PARADO POR TEMPO INDETERMINADO)
	@Override
	public void onEnable() {
		plugin = this;
		for (Player p : Bukkit.getOnlinePlayers()) {

			//
			// for (Permission perm :
			// Bukkit.getPluginManager().getPermissions()) {
			//
			// Bukkit.broadcastMessage(perm.getName() + " : ");
			// }
			// Bukkit.broadcastMessage(
			// "" + Bukkit.getPluginManager().getPermissions().size());
			// Permission perm = new Permission("teste.*",
			// PermissionDefault.TRUE);
			att = p.addAttachment(this);
			
			att.setPermission("teste.*", true);
for (Entry<String, Boolean> a :att.getPermissions().entrySet()){
			Bukkit.broadcastMessage("§e"+a.getKey()+" - "+a.getValue());	
			}
			if (p.hasPermission("teste.*")) {
				Bukkit.broadcastMessage("§cafsdfasdfsadf");
			}
			// // att.setPermission("eduardapi.command.gamemode", true);
			// att.setPermission("teste", true);
			// Bukkit.broadcastMessage("§c" + p.hasPermission("teste.affs") + "
			// §b"
			// + att.getPermissible().hasPermission("teste.mds"));
			//
		}
		// setupPermission();
		// loadListeners();
		// loadCommands();

	}
	private void loadCommands() {
		getCommand("groupmaster").setExecutor(new GroupMasterCommand());
	}
	@Override
	public void onDisable() {
		// savePlayers();
		att.remove();
	}

	private void savePlayers() {
		File file = new File(getDataFolder(), "players.yml");
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
		for (MPlayer player : players.values()) {
			UUID id = player.getId();
			ConfigurationSection sec = conf
					.getConfigurationSection(id.toString());
			if (sec == null) {
				sec = conf.createSection(id.toString());
			}
			sec.set("prefix", player.getPrefix());
			sec.set("suffix", player.getSuffix());
			sec.set("permissions", player.getPermissions());
			sec.set("groups", player.getGroups());
			sec.set("name", player.getName());
		}
		try {
			conf.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

	}

	private void setupPermission() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			setupPermission(player);
		}
	}
	public void addPermission(Player player, String permission) {
		UUID id = player.getUniqueId();
		MPlayer mp = players.get(id);
		if (!mp.getPermissions().contains(permission)) {
			mp.getPermissions().add(permission);
		}
		PermissionAttachment perm = permissions.get(id);
		perm.setPermission(permission, !permission.startsWith("-"));
	}
	public void removePermission(Player player, String permission) {
		UUID id = player.getUniqueId();
		players.get(id).getPermissions().remove(permission);
		permissions.get(id).unsetPermission(permission);
	}
	public void setupPermission(Player player) {
		UUID id = player.getUniqueId();
		unsetPermission(player);
		if (!players.containsKey(id)) {
			players.put(id, new MPlayer(id, new ArrayList<>(),
					Arrays.asList("normal"), "", "", player.getName()));
		}
		MPlayer perm = players.get(id);
		PermissionAttachment att = player.addAttachment(this);
		for (String permission : perm.getPermissions()) {
			if (permission.isEmpty())
				continue;
			boolean value = !permission.startsWith("-");
			att.setPermission(permission, value);
		}
		setupPermission(perm.getGroups(), att);

	}
	private void setupPermission(List<String> groups,
			PermissionAttachment att) {
		for (String group : groups) {
			if (group.isEmpty())
				continue;
			if (this.groups.containsKey(group)) {
				MGroup gr = this.groups.get(group);
				for (String permission : gr.getPermissions()) {
					if (permission.isEmpty())
						continue;
					boolean value = !permission.startsWith("-");
					att.setPermission(permission, value);
				}
				setupPermission(gr.getGroups(), att);
			}
		}
	}
	public void reloadPlayers() {
		players.clear();
		loadPlayers();
	}
	public void reloadGroups() {
		groups.clear();
		loadGroups();
	}

	private void loadPlayers() {
		File file = new File(getDataFolder(), "players.yml");
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection section = conf.getConfigurationSection("");
		if (section != null)
			for (String id : section.getKeys(false)) {
				ConfigurationSection sec = conf.getConfigurationSection(id);
				UUID uuid = UUID.fromString(id);
				players.put(uuid, new MPlayer(uuid,
						sec.getStringList("permissions"),
						sec.getStringList("groups"), sec.getString("prefix"),
						sec.getString("suffix"), sec.getString("name")));
			}
	}

	private void loadGroups() {
		File file = new File(getDataFolder(), "groups.yml");
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
		for (String groupName : conf.getConfigurationSection("")
				.getKeys(false)) {
			ConfigurationSection sec = conf.getConfigurationSection(groupName);
			this.groups.put(groupName,
					new MGroup(groupName, sec.getStringList("permissions"),
							sec.getStringList("groups"),
							sec.getString("prefix"), sec.getString("suffix")));
		}

	}
	public static Main getInstance() {
		return plugin;
	}
	public void unsetPermission(Player player) {
		UUID id = player.getUniqueId();
		if (permissions.containsKey(id)) {
			player.removeAttachment(permissions.get(id));
			permissions.remove(id);
		}
	}
	public void reloadPermissions() {
		reloadGroups();
		reloadPlayers();
		setupPermission();
	}

}
