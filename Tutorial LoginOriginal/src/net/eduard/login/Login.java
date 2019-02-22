package net.eduard.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Login implements Listener {

	@EventHandler
	public void event(PreLoginEvent e) {
		PendingConnection p = e.getConnection();

		String name = p.getName();
		if (isOriginal(name)) {
			p.setOnlineMode(true);
		} else {
			if (hasAccount(name)) {
				p.setOnlineMode(false);
			} else {
				e.setCancelled(true);
				e.setCancelReason("Voce precisa registrar esta conta no Site");
			}
		}
		p.setOnlineMode(true);

	}
	@EventHandler
	public void event(LoginEvent e) {
		PendingConnection p = e.getConnection();
		String name = p.getName();
		UUID id = p.getUniqueId();
		System.out.println("UUID " + p.getUniqueId());
		if (isOriginal(name)) {
			if (hasAccount(id)) {
				updateOriginal(id, name);
			} else {
				newOriginal(name, id);
			}
		} else {
			if (hasPlayer(name)) {
				e.setCancelled(true);
				e.setCancelReason("Voce ja esta no Servidor");
			}
		}
	}
	@EventHandler
	public void event(PostLoginEvent e) {

		

	}
	private static HttpURLConnection getConnection(String url)
			throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent", "Premium-Checker");

		return connection;
	}
	public boolean hasPlayer(String name) {
		return false;
	}
	public void updateOriginal(UUID id, String name) {

	}
	public void newOriginal(String name, UUID id) {

	}
	public boolean hasAccount(UUID id) {
		return false;
	}
	public boolean hasAccount(String name) {
		return false;
	}

	public boolean isOriginal(String name) {
		try {
			HttpURLConnection conn = getConnection(
					"https://api.mojang.com/users/profiles/minecraft/" + name);
			if (conn.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line = reader.readLine();
				if ((line != null) && (!line.equals("null")))
					return true;
			}
		} catch (Exception localException) {
		}
		return false;
	}

}
