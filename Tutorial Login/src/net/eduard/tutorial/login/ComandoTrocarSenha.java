package net.eduard.tutorial.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoTrocarSenha implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (LoginAPI.estaLogado(p)) {
				if (args.length == 0) {
					p.sendMessage("§c/trocarsenha <senha-nova>");
				} else {
					String senhaNova = args[0];
					if (senhaNova.equals(LoginAPI.getSenha(p))) {
						p.sendMessage("§cSua senha é exatamente a antiga!");
					} else {
						LoginAPI.trocarSenha(p, senhaNova);
						p.sendMessage("§aSua senha foi trocada!");
					}
				}

			} else {
				p.sendMessage("§cVoce so pode trocar senha logado!");
			}
		}

		return true;
	}

}
