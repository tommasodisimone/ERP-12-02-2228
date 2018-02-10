package cn.itcast.invoice.invoice.goodstype.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel implements Serializable{
	private Long segreto;
	private String name;
	
	private SupplierModel sm;
	private Set<GoodsModel> gms;

	public Set<GoodsModel> getGms() {
		return gms;
	}

	public void setGms(Set<GoodsModel> gms) {
		this.gms = gms;
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

	public SupplierModel getSm() {
		return sm;
	}

	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	
}