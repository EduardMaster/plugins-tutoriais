package net.eduard.simplewarp.manager;

import org.bukkit.Location;

public class Warp {
	
	public Warp(String name, Location location) {
		super();
		this.name = name;
		this.location = location;
	}
	private String name;
	private Location location;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

}
