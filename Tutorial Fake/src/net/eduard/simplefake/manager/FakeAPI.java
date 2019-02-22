package net.eduard.simplefake.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.eduard.simplefake.Main;

public final class FakeAPI {
	
	private static Map<Player,String> original = new HashMap<>();
	private static Map<Player,String> data = new HashMap<>();
	
	public static Map<Player, String> getOriginal() {
		return original;
	}
	public static Map<Player, String> getData() {
		return data;
	}
	public static boolean isFake(Player player){
		
		return !original.get(player).equals(data.get(player));
	}
	public static void fake(Player player,String name){
		data.put(player, name);
		player.setDisplayName(name);
		player.setPlayerListName(name); 
		

			try {
//				Object entityplayer = getHandle(player);
				// PacketPlayOutNamedEntitySpawn a;
				// EntityPlayer c;
				// PacketPlayOutEntity d;
				// PacketPlayOutSpawnEntityLiving e;

				// EntityHuman b;
//				Field profileField = Extra.getField(Mine.classMineEntityHuman, "bH");
//				Object gameprofile = profileField.get(entityplayer);
//				// Object before = Extra.getValue(gameprofile, "name");
//				Extra.setValue(gameprofile, "name", displayName);
				// EntityPlayer a;
				// Object packet = Extra.getNew(Mine.classPacketPlayOutNamedEntitySpawn,
				// Extra.getParameters(Mine.classMineEntityHuman),
				// entityplayer);
				// // Extra.setValue(Extra.getValue(packet, "b"), "name", displayName);
				// sendPackets(packet, player);
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.equals(player))
						continue;
					p.hidePlayer(player);
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.equals(player))
						continue;
					p.showPlayer(player);
				}
				// Extra.setValue(gameprofile, "name", before);
				// System.out.println(Bukkit.getPlayer(displayName));

			} catch (Exception ex) {
				ex.printStackTrace();
			}

	}
	public static void reset(Player player){
		fake(player,original.get(player));
		player.sendMessage(Main.config.message("name_reset_to_original"));
	}

}
