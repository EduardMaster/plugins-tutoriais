package net.eduard.permission.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.eduard.permission.Main;

public class GroupMasterCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender,
			Command cmd, String label,
			String[] args) {
		if (args.length == 0){
			sender.sendMessage("§c/mg help");
		}else{
			String arg = args[0];
			if (arg.equalsIgnoreCase("reload")){
				Main.getInstance().reloadPermissions();
				sender.sendMessage("§aGroupMaster recarregado!");
			}else if (arg.equalsIgnoreCase("addperm")){
				if (args.length == 0){
					sender.sendMessage("§c/mg addperm <permission>");
				}else{
					String permission = args[1];
				}
			}	
		}
		return false;
	}

}
