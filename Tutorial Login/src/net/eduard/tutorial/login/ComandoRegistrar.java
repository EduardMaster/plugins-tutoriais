package net.eduard.tutorial.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoRegistrar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			// login arg0 arg1
			if (args.length == 0) {
				return false;
			} else {
				if (!LoginAPI.estaRegistrar(p)) {
					String senhaInicial = args[0];
					if (args.length < 2) {
						p.sendMessage("§cDigite a confirmação da senha!");
					} else {
						String confirmaSenha = args[1];
						if (senhaInicial.equals(confirmaSenha)) {
							LoginAPI.registrar(p, confirmaSenha);
							p.sendMessage("§aVoce se registrou com sucesso!");
							LoginAPI.logar(p);
						} else {
							p.sendMessage("§cAs senhas não batem!");
						}

					}
				}else {
					p.sendMessage("§aVoce ja esta registrado!");
				}
			}

		}

		return true;
	}

}
