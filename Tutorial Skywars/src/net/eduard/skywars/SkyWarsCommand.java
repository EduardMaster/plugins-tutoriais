package net.eduard.skywars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkyWarsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage("§c/skywars help");
				return true;
			}
			String c = args[0];
			if (c.equalsIgnoreCase("create")|c.equalsIgnoreCase("criar")|c.equalsIgnoreCase("set")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars create <name>");
				} else {
					String name = args[1];
					if (!Main.existsSkywars(name)) {
						Main.getSkyWars(args[1]);
						p.sendMessage("§aSkyWars criado com sucesso!");
					} else {
						p.sendMessage("§cJá existe esse skywars!");
					}

				}
			}
			if (c.equalsIgnoreCase("delete")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars delete <name>");

				} else {
					String name = args[1];
					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						Main.skywars.remove(sky);
						p.sendMessage("§aSkywars deletado!");
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}

				}
			}
			if (c.equalsIgnoreCase("setspawns")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars setspawns <name>");
				} else {
					String name = args[1];

					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						sky.getSpawns().add(p.getLocation());
						p.sendMessage("§aSkywars spawn " + sky.getSpawns().size() + " adicionado!");
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}
				}

			}
			if (c.equalsIgnoreCase("resetspawns")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars delete <name>");

				} else {
					String name = args[1];
					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						sky.getSpawns().clear();
						p.sendMessage("§aSpawns resetados!");
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}
					
					
					
				}

			}
			if (c.equalsIgnoreCase("setlobby")) {
				if (args.length == 1) {
					Main.Lobby =p.getLocation();
					p.sendMessage("§aLobby do Skywars setado!");
				}else {
					String name = args[1];
					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						sky.setLobby(p.getLocation());
						p.sendMessage("§aLobby da arena de Skywars setado!");
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}
					
				}
			}
			if (c.equalsIgnoreCase("enable")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars enable <name>");

				} else {
					String name = args[1];
					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						if (sky.getLobby()==null) {
							p.sendMessage("§cConfigure o Lobby da arena de Skywars antes");
							return true;
						}
						if (!Main.existsLobby()) {
							p.sendMessage("§cConfigure o Lobby do Skywars");
							return true;
						}
						if (sky.getSpawns().size()<sky.getMinPlayersAmount()) {
							p.sendMessage("§cConfigure mais spawns para o Skywars");
							return true;
						}
						p.sendMessage("§aSkywars habilitado!");
						sky.setEnabled(true);
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}
					
					
					
				}
			}
			if (c.equalsIgnoreCase("disable")) {
				if (args.length == 1) {
					p.sendMessage("§c/skywars disable <name>");

				} else {
					String name = args[1];
					if (Main.existsSkywars(name)) {
						SkyWars sky = Main.getSkyWars(name);
						sky.setEnabled(false);
						p.sendMessage("§cSpawns desabilitado!");
					} else {
						p.sendMessage("§cSkywars nao existente!");
					}
					
					
					
				}
			}
			
			if (c.equalsIgnoreCase("help")) {
				p.sendMessage("§c=+=+=+=+=+=+=+=+=+=+=+=+=");
				p.sendMessage("§b/skywars create <arena>");
				p.sendMessage("§b/skywars delete <arena>");
				p.sendMessage("§b/skywars enable <arena>");
				p.sendMessage("§b/skywars disable <arena>");
				p.sendMessage("§b/skywars resetspawns <arena>");
				p.sendMessage("§b/skywars setspawns <arena>");
				p.sendMessage("§b/skywars setlobby [arena]");
				p.sendMessage("§8-----------------------------");
			}

		} else {
			sender.sendMessage("§cComando apenas para jogadores!");
		}

		return true;
	}

}
