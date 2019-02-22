package net.eduard.tapetevoador.manager;

import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.tapetevoador.Main;

public class TapeteTimer extends BukkitRunnable
{

	@Override
	public void run() {
		Main.effect();
		
	}

}
