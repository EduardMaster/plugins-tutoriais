package net.eduard.cashsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CashSQL {
	
	
	
	public static void criarTabela(){
		
		try {
			
			Connection con = abrirConecao();
			
			PreparedStatement st = con.prepareStatement("CREATE TABLE IF NOT EXISTS DINHEIRO(ID VARCHAR(45),CASH DOUBLE);");
			st.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
		}
		
	}
	public static void addJogador(Player player){
		UUID id = player.getUniqueId();
		try {
			Connection con = abrirConecao();
			PreparedStatement st = con.prepareStatement("INSERT INTO DINHEIRO VALUES (?,1000);");
			st.setString(1, id.toString());
			st.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static double getJogador(Player player){
		double value = 0;
		try {
			UUID id = player.getUniqueId();
			try {
				Connection con = abrirConecao();
				PreparedStatement st = con.prepareStatement("SELECT CASH FROM DINHEIRO WHERE ID = ?;");
				st.setString(1, id.toString());
				ResultSet rs = st.executeQuery();
				
				if (rs.next()){
					value = rs.getDouble("CASH");
				}
				
				con.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
		}
		return value;
	}
	public static boolean hasJogador(Player player){
		try {
			UUID id = player.getUniqueId();
			try {
				Connection con = abrirConecao();
				PreparedStatement st = con.prepareStatement("SELECT CASH FROM DINHEIRO WHERE ID = ?;");
				st.setString(1, id.toString());
				ResultSet rs = st.executeQuery();
				boolean result = rs.next();
				con.close();
				return result;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
		}
		return false;
	}
	public static void setJogador(Player player,double cash){
		UUID id = player.getUniqueId();
		try {
			Connection con = abrirConecao();
			PreparedStatement st = con.prepareStatement("UPDATE DINHEIRO SET CASH = ? WHERE ID = ?;");
			st.setString(2, id.toString());
			st.setDouble(1, cash);
			st.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	
	public static Connection abrirConecao()
	{
		try {
			String password = "";
			String user = "root";
			String host = "localhost";
			String port = "3306";
			String database = "teste3";
			String type = "jdbc:mysql://";
			String url = type+host+":"+port+"/"+database;
			return DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("erro");
		}
		
		
		
		
		return null;
		
	}
	
	
	

}
