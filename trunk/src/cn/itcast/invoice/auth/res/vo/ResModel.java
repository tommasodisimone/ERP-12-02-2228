package cn.itcast.invoice.auth.res.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.auth.role.vo.RoleModel;

public class ResModel implements Serializable{
	private Long uuid;
	private String name;
	//action访问路径
	private String url;
	
	//角色
	private Set<RoleModel> roles;
	
	public Set<RoleModel> getRoles() {
		return roles;
	}
	private void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public Long getUuid() {
		return uuid;
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