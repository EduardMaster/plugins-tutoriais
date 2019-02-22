package net.eduard.tutorial.login;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Login extends JavaPlugin{
	
	private static Login instance;
	
	public void onEnable() {
		setInstance(this);
		Bukkit.getConsoleSender().sendMessage("§aSistema de registro ligado!");
		getCommand("login").setExecutor(new ComandoLogin());
		getCommand("registrar").setExecutor(new ComandoRegistrar());
		getCommand("trocarsenha").setExecutor(new ComandoTrocarSenha());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Eventos(), this);
	}

	public static Login getInstance() {
		return instance;
	}

	public static void setInstance(Login instance) {
		Login.instance = instance;
	}

}
