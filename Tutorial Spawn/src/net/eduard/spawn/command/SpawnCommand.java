
package net.eduard.spawn.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.spawn.Main;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if (Main.config.contains("spawn")){
				player.teleport(Main.config.getLocation("spawn"));
				player.sendMessage(Main.messages.message("Spawn"));
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2, 1);
				
			}else{
				player.sendMessage(Main.messages.message("Sem spawn"));
			}
		}else{
			sender.sendMessage("§cApenas para jogadores!");
		}
		return true;
	}

}
