package net.eduard.template;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Comando implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return true;
	}
}
