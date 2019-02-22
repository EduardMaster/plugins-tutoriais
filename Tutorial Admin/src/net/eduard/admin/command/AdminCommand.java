
package net.eduard.admin.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.eduard.admin.Main;

public class AdminCommand implements CommandExecutor {
	public static HashMap<Player, ItemStack[]> items = new HashMap<>();
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (Main.admins.contains(p)) {
				p.getInventory().setContents(items.get(p));
				for (Player target:Bukkit.getOnlinePlayers()) {
					if (!p.equals(target))
					target.showPlayer(p);
				}
				items.remove(p);
				Main.admins.remove(p);
				p.sendMessage("§6Voce saiu do modo Admin!");
			}else {
				Main.admins.add(p);
				items.put(p, p.getInventory().getContents());
				for (Player target:Bukkit.getOnlinePlayers()) {
					if (!p.equals(target))
					target.hidePlayer(p);
				}
				Main.giveItemsAdmin(p);
				p.sendMessage("§6Voce entrou no modo Admin!");
			}
		}
		return true;
	}

}
