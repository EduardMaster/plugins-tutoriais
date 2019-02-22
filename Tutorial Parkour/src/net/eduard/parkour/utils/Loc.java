package net.eduard.parkour.utils;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Loc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x, y, z, yaw, pitch;
	private String world;

	public Loc(org.bukkit.Location location) {
		setX(location.getX());
		setY(location.getY());
		setZ(location.getZ());
		setYaw(location.getYaw());
		setPitch(location.getPitch());
		;
		setWorld(location.getWorld());
	}
	public Location getLocation() {
		return new Location(getWorld(), x, y, z,(float)yaw,(float)pitch);
	}

	public void setWorld(World world) {
		this.world = world.getName();
	}

	public World getWorld() {
		return Bukkit.getWorld(world);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

}
