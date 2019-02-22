package net.eduard.permission.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MPlayer {

	private UUID id;
	private List<String> permissions = new ArrayList<>();
	private List<String> groups = new ArrayList<>();
	private String prefix;
	private String suffix;
	private String name;
	public MPlayer(UUID id, List<String> permissions, List<String> groups,
			String prefix, String suffix, String name) {
		super();
		this.id = id;
		this.permissions = permissions;
		this.groups = groups;
		this.prefix = prefix;
		this.suffix = suffix;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
