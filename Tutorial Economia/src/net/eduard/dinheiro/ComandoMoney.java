package net.eduard.dinheiro;

import java.text.DecimalFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoMoney implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			UUID id = p.getUniqueId();
			if (args.length == 0) {
				p.sendMessage("§aSeu dinheiro §2" + new DecimalFormat("#,###.##")
						.format(Main.getInstance().getMoney().get(id)));
			} else {
				if (args[0].equalsIgnoreCase("set")) {
					if (args.length <= 2) {
						p.sendMessage("§c/money set <player> <value>");
					} else {
						Player target = Bukkit.getPlayerExact(args[1]);
						if (target == null) {
							p.sendMessage("§cPlayer invalido!");
						} else {
							try {
								double valor = Double.parseDouble(args[2]);
								Main.getInstance().getMoney().put(id, valor);

							} catch (Exception e) {
								p.sendMessage(
										"§cUse numero em vez de palavras!");
							}
						}
					}
					// money set nome 20
				}
				else if (args[0].equalsIgnoreCase("remove")) {
					if (args.length <= 2) {
						p.sendMessage("§c/money remove <player> <value>");
					} else {
						Player target = Bukkit.getPlayerExact(args[1]);
						if (target == null) {
							p.sendMessage("§cPlayer invalido!");
						} else {
							try {
								double valor = Double.parseDouble(args[2]);
								
								double calc = Main.getInstance().getMoney().get(id)-valor;
								
								Main.getInstance().getMoney().put(id, calc);

							} catch (Exception e) {
								p.sendMessage(
										"§cUse numero em vez de palavras!");
							}
						}
					}
					// money set nome 20
				}
				else if (args[0].equalsIgnoreCase("add")) {
					if (args.length <= 2) {
						p.sendMessage("§c/money add <player> <value>");
					} else {
						Player target = Bukkit.getPlayerExact(args[1]);
						if (target == null) {
							p.sendMessage("§cPlayer invalido!");
						} else {
							try {
								double valor = Double.parseDouble(args[2]);
								
								double calc = Main.getInstance().getMoney().get(id)+valor;
								
								Main.getInstance().getMoney().put(id, calc);

							} catch (Exception e) {
								p.sendMessage(
										"§cUse numero em vez de palavras!");
							}
						}
					}
					// money set nome 20
				}
			}

		}
		return false;
	}

}
