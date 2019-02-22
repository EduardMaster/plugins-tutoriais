
package net.eduard.simplefake;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.eduard.simplefake.comandos.FakeCommand;
import net.eduard.simplefake.event.FakeEvent;
import net.eduard.simplefake.manager.Configs;
import net.eduard.simplefake.manager.FakeAPI;


public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			FakeAPI.reset(p);
		}
	} 

	public static Configs config;

	@Override
	public void onEnable() {

		config = new Configs("config.yml",this);
		config.add("name_reset_to_original",
				"&aSeu nome voltou para o estado original!");
		config.add("name_exist_exeption",
				"&cJa existe um player com esse nome! &e$name");
		config.add("name_change_sussefully",
				"&aVoce agora esta com esse nome! &e$name");
		config.add("kick_by_player_exist",
				"&cExiste um jogador com esse nome! &e$name");
		config.add("name_reset_by_other",
				"&aSeu nome foi resetado porque entrou um jogador com esse nome! &e$name");
		config.saveConfig();
		Bukkit.getPluginManager().registerEvents(new FakeEvent(), this);
		getCommand("fake").setExecutor(new FakeCommand());
		for (Player p : Bukkit.getOnlinePlayers()) {
			Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(p, null));
		}
	}
}
