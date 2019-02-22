package net.eduard.permission.setup;

import java.util.ArrayList;
import java.util.List;

public class MGroup {
	
	private String name;
	private List<String> permissions = new ArrayList<>();
	private List<String> groups = new ArrayList<>();
	private String prefix;
	private String suffix;
	
	
	public MGroup(String name, List<String> permissions, List<String> groups,
			String prefix, String suffix) {
		super();
		this.name = name;
		this.permissions = permissions;
		this.groups = groups;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	

}
