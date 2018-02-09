package cn.itcast.invoice.auth.dep.vo;

import java.io.Serializable;

public class DepModel implements Serializable{
	private Long uuid;
	
	private String name;
	
	private String tele;
	
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
	public String getTele() {
		return tele;
	}
	private void setTele(String tele) {
		this.tele = tele;
	}
	
}