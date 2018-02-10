package cn.itcast.invoice.invoice.storedetail.vo;

import java.io.Serializable;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.store.vo.StoreModel;

public class StoreDetailModel implements Serializable{
	private Long segreto;
	
	private Integer num;
	
	private GoodsModel gm;
	private StoreModel sm;
	
	public Long getUuid() {
		return segreto;
	}
	public void setSegreto(Long segreto) {
		this.segreto = segreto;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public StoreModel getSm() {
		return sm;
	}
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}