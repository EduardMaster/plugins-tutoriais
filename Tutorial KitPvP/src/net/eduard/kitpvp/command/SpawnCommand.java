package net.eduard.kitpvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.kitpvp.manager.KitPvP;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cSomente para Jogadores!");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("spawn")){
			Player p = (Player)sender;
			if(args.length == 0){
				try {
					p.sendMessage("§c§lKITPVP §7- §eTeleportando para o spawn em 3 segundos...");
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				p.teleport(KitPvP.spawn);;
				p.sendMessage("§c§lKITPVP §7- §eTeleportado até o Spawn!");
			}
		}
		return false;
	}

}
