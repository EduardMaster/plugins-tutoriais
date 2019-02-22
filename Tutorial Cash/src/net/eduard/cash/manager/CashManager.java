package net.eduard.cash.manager;

import java.sql.ResultSet;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CashManager extends SQLManager {

	private static CashManager cash = new CashManager();
	public static CashManager getCash() {
		return cash;
	}

	public CashManager() {
		super("root", "", "localhost", "teste2");
		update("create table if not exists cash (id varchar(50) not null,value double not null);");

	}
	public double getCash(Player player) {
		double value = 0;
		UUID id = player.getUniqueId();
		try {
			ResultSet rs = select("select * from cash where id = ?", id);
			if (rs.next()) {
				value = rs.getDouble("cash");
			} else {
				update("insert into cash values (?,0)", id);
			}
			endQuery();
		} catch (Exception e) {
		}
		return value;
	}
	public void setCash(Player player,double cash){
		UUID id = player.getUniqueId();
		try {
			getCash(player);
			update("update cash set cash = ? where id = ?", cash, id );
		} catch (Exception e) {
		}
	}
	public void addCash(Player player,double cash){
		setCash(player, getCash(player)+cash);
	}
	public void removeCash(Player player,double cash){
		setCash(player, getCash(player)-cash);
	}

}
