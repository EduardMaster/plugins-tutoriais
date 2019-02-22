package net.eduard.kitpvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.kitpvp.manager.KitPvP;

public class SetSpawnCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cSomente para Jogadores!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			Player p = (Player)sender;
			if(!p.hasPermission("kitpvp.setspawn")){
				p.sendMessage("§cVocê não tem permissão para fazer isto!");
				return true;
			} else {
				try {
					p.sendMessage("§eAguarde 3 segundos...");
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				KitPvP.spawn = p.getLocation();
				p.sendMessage("§eO 'Spawn' foi setado com sucesso!");
			}
		}
		return false;
	}

}
