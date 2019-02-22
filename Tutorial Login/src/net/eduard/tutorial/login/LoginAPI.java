package net.eduard.tutorial.login;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public final class LoginAPI {
	
	private static Login login;
	
	static {
		login = Login.getInstance();
	}

	private static List<Player> jogadoresLogados = new ArrayList<>();
	
	public static boolean estaLogado(Player player) {
		return jogadoresLogados.contains(player);
	}
	public static void logar(Player player) {
		jogadoresLogados.add(player);
	}
	public static void deslogar(Player player) {
		jogadoresLogados.remove(player);
	}
	public static boolean estaRegistrar(Player player) {
		return login.getConfig().contains(player.getName().toLowerCase());
	}
	public static String getSenha(Player player) {
		return login.getConfig().getString(player.getName().toLowerCase());
	}
	
	public static void removerConta(String conta) {
		login.getConfig().set(conta.toLowerCase(), null);
		login.saveConfig();
	}
	
	public static void registrar(Player player,String senha) {
		login.getConfig().set(player.getName().toLowerCase(), senha);
		login.saveConfig();
	}
	public static void trocarSenha(Player player,String novaSenha) {
		login.getConfig().set(player.getName().toLowerCase(), novaSenha);
		login.saveConfig();
	}
	

	public static List<Player> getJogadoresLogados() {
		return jogadoresLogados;
	}

	public static void setJogadoresLogados(List<Player> jogadoresLogados) {
		LoginAPI.jogadoresLogados = jogadoresLogados;
	}

}
