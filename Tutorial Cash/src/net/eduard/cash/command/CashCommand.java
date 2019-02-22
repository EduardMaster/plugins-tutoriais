package net.eduard.cash.command;

import java.text.NumberFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.cash.manager.CashManager;

public class CashCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		
		
		if (sender instanceof Player){
			Player p = (Player) sender;
			if (args.length == 0){
				double value = CashManager.getCash().getCash(p);
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				p.sendMessage("§6Seu cash é: §e"+nf.format(value));
			}else{
				String arg = args[0];
				if (arg.equalsIgnoreCase("set")){
					if (args.length == 1){
						p.sendMessage("§c/cash set <amount>");
					}else{
						double value = 0;
						try {
							value = Double.valueOf(args[1]);
						} catch (Exception e) {
						}
						
						CashManager.getCash().setCash(p, value);
						p.chat("/cash");
					}
				}
			}
		}else{
			
		}
		
		return true;
	}
}
