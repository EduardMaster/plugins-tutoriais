package net.eduard.cash.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLManager {

	private String user= "root";
	private String pass="";
	private String host = "localhost";
	private String port = "3306";
	private String database="teste";
	private String type = "jdbc:mysql://";
	private Connection connection;
	private int modications = 0;

	static {
		// test mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getNewConnection() throws Exception {
		return DriverManager.getConnection(
				type + host + ":" + port + "/" + database, user, pass);
	}

	public static void main(String[] args) {
		System.out.println("".replace('?', '&').replaceFirst("&", "?"));
	}
	public synchronized void openConnection() {
		try {
			if (!hasConnection())
				connection = getNewConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized boolean hasModifications() {
		return modications > 0;
	}
	public synchronized Connection getConnection() {
		return connection;
	}

	public synchronized int update(String query,Object... replacers) {
		startQuery();
		try {
			return query(query, replacers).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			endQuery();
		}
	}
	public synchronized PreparedStatement query(String query,Object... replacers){
		try {
			query=query.replace('?', '&');
			for (Object replacer:replacers){
				query=query.replaceFirst("&", "'"+replacer.toString()+"'");
			}
			if (!query.endsWith(";")){
				query+=";";
			}
			return connection.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized ResultSet select(String query,Object... replacers) {
		startQuery();
		try {
			return query(query, replacers).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public synchronized void startQuery() {
		openConnection();
		modications++;
	}
	public synchronized void endQuery() {
		modications--;
		closeConnection();
	}

	public synchronized boolean hasConnection() {
		return connection != null;
	}
	public synchronized void closeConnection() {
		try {
			if (hasConnection() && !hasModifications()) {
				connection.close();
				connection = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SQLManager(String user, String pass, String host, String database) {
		super();
		this.user = user;
		this.pass = pass;
		this.host = host;
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
