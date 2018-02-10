package cn.itcast.invoice.invoice.store.vo;

import java.io.Serializable;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
/**
 * this class implements Serializable
 *
 */
public class StoreModel implements Serializable{
	private Long segreto;
	private String name;
	private String address;
	
	private EmpModel em;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EmpModel getEm() {
		return em;
	}
	public void setEm(EmpModel em) {
		this.em = em;
	}
	
}