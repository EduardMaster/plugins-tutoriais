
package net.eduard.spawn.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.spawn.Main;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			Main.config.setLocation("spawn", player.getLocation());
			Main.config.saveConfig();
			player.sendMessage(Main.messages.message("Spawn setado"));
		}else{
			sender.sendMessage("§cApenas para jogadores!");
		}
		return true;
	}

}
