
package net.eduard.parkour.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.parkour.manager.Parkour;
import net.eduard.parkour.utils.Loc;

public class ParkourCommand implements CommandExecutor {

	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.GREEN + "Digite /parkour help");
		} else {
			String arg = args[0];
			if (arg.equalsIgnoreCase("help")) {
				if (sender.hasPermission("parkour.admin")) {
					sender.sendMessage(ChatColor.GREEN+"/parkour create <name>"+ChatColor.AQUA+" Cria o parkour");
					sender.sendMessage(ChatColor.GREEN+"/parkour delete <name>"+ChatColor.AQUA+" Deleta o parkour");
					sender.sendMessage(ChatColor.GREEN+"/parkour setspawn <name>"+ChatColor.AQUA+" Seta o Spawn do parkour");
					sender.sendMessage(ChatColor.GREEN+"/parkour setend <name>"+ChatColor.AQUA+" Seta o Fim do parkour");
					sender.sendMessage(ChatColor.GREEN+"/parkour setcheckpoint <name>"+ChatColor.AQUA+" Seta o Bloco de Checkpoint do parkour");
				}
				sender.sendMessage(ChatColor.GREEN+"/parkour join <name>"+ChatColor.AQUA+" Entra no parkour");
				sender.sendMessage(ChatColor.GREEN+"/parkour list"+ChatColor.AQUA+" Verifica a lista de Parkours");
			} else if (arg.equalsIgnoreCase("create")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour create <name>");
				} else {
					String name = args[1];
					if (Parkour.existsParkour(name)) {
						sender.sendMessage(ChatColor.RED + "Já existe este parkour criado!");
					} else {
						Parkour.createParkour(name);
						sender.sendMessage(ChatColor.GREEN + "Este parkour foi criado!");
					}
				}
			} else if (arg.equalsIgnoreCase("delete")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour delete <name>");
				} else {
					String name = args[1];
					if (Parkour.existsParkour(name)) {
						Parkour.deleteParkour(name);
						sender.sendMessage(ChatColor.GREEN + "Este parkour foi deletedo!");
					} else {
						sender.sendMessage(ChatColor.RED + "Este parkour nao foi criado!");
					}
				}
			} else if (arg.equalsIgnoreCase("setspawn")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour setspawn <name>");
				} else {

					String name = args[1];
					if (Parkour.existsParkour(name)) {
						if (sender instanceof Player) {
							Player p = (Player) sender;
							Parkour par = Parkour.getParkour(name);
							par.setSpawn(new Loc(p.getLocation()));
							sender.sendMessage(ChatColor.GREEN + "Este spawn foi setado!");
						} else {
							sender.sendMessage(ChatColor.RED + "Este comando so pode ser usado em jogo!");
						}

					} else {
						sender.sendMessage(ChatColor.RED + "Este parkour nao foi criado!");
					}
				}
			} else if (arg.equalsIgnoreCase("setend")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour setend <name>");
				} else {

					String name = args[1];
					if (Parkour.existsParkour(name)) {
						if (sender instanceof Player) {
							Player p = (Player) sender;
							Parkour par = Parkour.getParkour(name);
							par.setEnd(new Loc(p.getLocation()));
							sender.sendMessage(ChatColor.GREEN + "Este fim de Parkour(end) foi setado!");
						} else {
							sender.sendMessage(ChatColor.RED + "Este comando so pode ser usado em jogo!");
						}

					} else {
						sender.sendMessage(ChatColor.RED + "Este parkour nao foi criado!");
					}
				}
			} else if (arg.equalsIgnoreCase("setcheckpoint")) {
				if (args.length <= 2) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour setcheckpoint <name> <material> ou..");
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour setcheckpoint <name> <id>");
				} else {
					String name = args[1];
					if (Parkour.existsParkour(name)) {
						Parkour par = Parkour.getParkour(name);
						String type = args[2];
						Material mat = null;
						try {
							mat = Material.valueOf(name);
						} catch (Exception ex) {
							try {
								mat = Material.getMaterial(Integer.valueOf(type));
							} catch (Exception ex2) {

							}
						}
						if (mat == null) {
							sender.sendMessage(
									ChatColor.GREEN + "Digite /parkour setcheckpoint <name> <material> ou..");
							sender.sendMessage(ChatColor.GREEN + "Digite /parkour setcheckpoint <name> <id>");
						} else {

							par.setCheckpointTester(mat);
							sender.sendMessage(ChatColor.GREEN + "Este tipo de bloco de checkpoint foi setado!");
							sender.sendMessage(ChatColor.GREEN + "Bloco: " + mat.name());
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Este parkour nao foi criado!");
					}

				}
			}else if (arg.equalsIgnoreCase("join")) {
				if (args.length == 1) {
					sender.sendMessage(ChatColor.GREEN + "Digite /parkour join <name>");
				} else {

					String name = args[1];
					if (Parkour.existsParkour(name)) {
						if (sender instanceof Player) {
							Player p = (Player) sender;
							Parkour par = Parkour.getParkour(name);
							if (par.enabled()) {
								par.join(p);
							}else {
								sender.sendMessage(ChatColor.RED + "Este Parkour nao foi totalmente configurado!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Este comando so pode ser usado em jogo!");
						}

					} else {
						sender.sendMessage(ChatColor.RED + "Este parkour nao foi criado!");
					}
				}
			} 
		}
		return true;
	}

}
