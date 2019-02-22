package net.eduard.tapetevoador.command;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.tapetevoador.Main;

public class TapeteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (Main.players.contains(p)) {
				Main.players.remove(p);
				p.sendMessage(Main.config.message("Disable"));
				for (Block block : Main.getTapeteBlocks(p.getLocation())) {
					if (block.getType() == Main.getMaterial()) {
						block.setType(Material.AIR);
					}
				}
			} else {
				Main.players.add(p);
				p.sendMessage(Main.config.message("Enable"));
			}
		}
		return true;
	}

}
