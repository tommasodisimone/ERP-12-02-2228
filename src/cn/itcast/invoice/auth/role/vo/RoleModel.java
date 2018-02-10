package cn.itcast.invoice.auth.role.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.auth.res.vo.ResModel;

public class RoleModel implements Serializable{
	private Long segreto;
	private String name;
	
	//关系
	private Set<ResModel> reses;
	private Set<EmpModel> emps;
	private Set<MenuModel> menus;
	
	public Set<MenuModel> getMenus() {
		return menus;
	}
	public void setMenus(Set<MenuModel> menus) {
		this.menus = menus;
	}
	public Set<EmpModel> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpModel> emps) {
		this.emps = emps;
	}
	public Set<ResModel> getReses() {
		return reses;
	}
	public void setReses(Set<ResModel> reses) {
		this.reses = reses;
	}
	public Long getUuid() {
		return segreto;
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
	
}