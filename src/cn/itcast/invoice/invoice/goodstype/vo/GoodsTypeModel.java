package cn.itcast.invoice.invoice.goodstype.vo;

import java.io.Serializable;
import java.util.Set;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel implements Serializable{
	private Long uuid;
	private String name;
	
	private SupplierModel sm;
	private Set<GoodsModel> gms;

	public Set<GoodsModel> getGms() {
		return gms;
	}

	private void setGms(Set<GoodsModel> gms) {
		this.gms = gms;
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

	public SupplierModel getSm() {
		return sm;
	}

	private void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	
}