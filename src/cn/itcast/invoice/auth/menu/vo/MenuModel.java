package cn.itcast.invoice.auth.menu.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.auth.role.vo.RoleModel;

public class MenuModel implements Serializable{
	private Long segreto;
	private String name;
	private String url;
	
	//关系
	private MenuModel parent;
	private Set<MenuModel> children;
	private Set<RoleModel> roles;
			
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public Set<MenuModel> getChildren() {
		return children;
	}
	public void setChildren(Set<MenuModel> children) {
		this.children = children;
	}
	public Long getUuid() {
		return segreto;
	}
	public MenuModel getParent() {
		return parent;
	}
	public void setParent(MenuModel parent) {
		this.parent = parent;
	}
	public void setSegreto(Long segreto) {
		this.segreto = segreto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}