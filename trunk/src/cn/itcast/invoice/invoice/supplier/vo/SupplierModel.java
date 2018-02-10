package cn.itcast.invoice.invoice.supplier.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
/**
 * this class implements Serializable
 *
 */
public class SupplierModel implements Serializable{
	/**
	 * this public element is a public element
	 *
	 */
	public static final Integer SUPPLIER_NEEDS_OF_YES = 1;
	/**
	 * this public element is a public element
	 *
	 */
	public static final Integer SUPPLIER_NEEDS_OF_NO = 0;
	
	/**
	 * this public element is a public element
	 *
	 */
	public static final String SUPPLIER_NEEDS_OF_YES_VIEW = "é€�è´§";
	/**
	 * this public element is a public element
	 *
	 */
	public static final String SUPPLIER_NEEDS_OF_NO_VIEW = "è‡ªæ��";
	
	/**
	 * this public element is a public element
	 *
	 */
	public static final Map<Integer, String> needsMap = new HashMap<Integer, String>();
	static{
		needsMap.put(SUPPLIER_NEEDS_OF_YES, SUPPLIER_NEEDS_OF_YES_VIEW);
		needsMap.put(SUPPLIER_NEEDS_OF_NO, SUPPLIER_NEEDS_OF_NO_VIEW);
	}
	
	private Long segreto;
	
	private String name;
	private String address;
	private String contact;
	private String tele;
	private Integer needs;
	
	private String needsView;
	
	//å…³ç³»
	private Set<GoodsTypeModel> gtms;
	
	/**
	 * this public element is a public element
	 *
	 */
	public Set<GoodsTypeModel> getGtms() {
		return gtms;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setGtms(Set<GoodsTypeModel> gtms) {
		this.gtms = gtms;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String getNeedsView() {
		return needsView;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public Integer getNeeds() {
		return needs;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView = needsMap.get(needs);
	}
	/**
	 * this public element is a public element
	 *
	 */
	public Long getUuid() {
		return segreto;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setSegreto(Long segreto) {
		this.segreto = segreto;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String getName() {
		return name;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String getTele() {
		return tele;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	
}