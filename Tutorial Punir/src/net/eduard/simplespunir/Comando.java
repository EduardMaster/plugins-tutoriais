package net.eduard.simplespunir;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;

import net.eduard.simplespunir.fancyapi.FancyMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Comando implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {

				p.sendMessage("§c/punir <jogador>");
				return true;
			}
			Player target = Bukkit.getPlayerExact(args[0]);
			new FancyMessage("oi").command("/www.google.com.br")
					.then("ADASDASD").command("/GAMEMODE 1").send(target);
			efeito(p, "§bPunir jogar para sempre", "/ban " + target.getName(),
					"§cAo clicar ira banir o jogador " + target.getName()
							+ " para sempre");
			TextComponent text = new TextComponent("texto");
			text.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/fly"));
			text.setHoverEvent(new HoverEvent(
					net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder("Oi tudo bem").create()));
			p.spigot().sendMessage(text);

		}

		return true;
	}
	public static void efeito(Player p, String message, String cmd,
			String hover) {
		Spigot player = p.spigot();
		TextComponent text = new TextComponent(message);
		text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(hover).create()));
		text.setClickEvent(
				new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, cmd));
		p.spigot().sendMessage(text);


	}
}
