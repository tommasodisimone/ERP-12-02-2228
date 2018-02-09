package cn.itcast.invoice.auth.menu.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.auth.role.vo.RoleModel;

public class MenuModel implements Serializable{
	private Long uuid;
	private String name;
	private String url;
	
	//关系
	private MenuModel parent;
	private Set<MenuModel> children;
	private Set<RoleModel> roles;
			
	public Set<RoleModel> getRoles() {
		return roles;
	}
	private void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public Set<MenuModel> getChildren() {
		return children;
	}
	private void setChildren(Set<MenuModel> children) {
		this.children = children;
	}
	public Long getUuid() {
		return uuid;
	}
	public MenuModel getParent() {
		return parent;
	}
	private void setParent(MenuModel parent) {
		this.parent = parent;
	}
	private void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	private void setUrl(String url) {
		this.url = url;
	}
	
}