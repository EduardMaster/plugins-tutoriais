
package net.eduard.misterybox.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.misterybox.manager.MysteryBox;

public class TemplateEvent implements Listener {

	@EventHandler
	public void event(PlayerMoveEvent e) {
	}
	@EventHandler
	public void event(PlayerChatEvent e) {
		if (e.getMessage().contains("abrircoco")){
			MysteryBox.open2(e.getPlayer());
		}
	}

}
