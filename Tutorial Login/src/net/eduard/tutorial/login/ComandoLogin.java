package net.eduard.tutorial.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoLogin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				return true;
			} else {
				if (!LoginAPI.estaLogado(p)) {
					String senha = args[0];
					if (senha.equals(LoginAPI.getSenha(p))) {
						LoginAPI.logar(p);
						p.sendMessage("§aLogado com sucesso!");

					} else {
						p.kickPlayer("§cVoce errou a senha tente denovo!");
					}
				} else {
					p.sendMessage("§cVoce já esta logado!");
				}
			}
		}

		return true;
	}

}
