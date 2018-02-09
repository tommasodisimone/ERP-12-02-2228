package cn.itcast.invoice.invoice.storedetail.vo;

import java.io.Serializable;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.store.vo.StoreModel;

public class StoreDetailModel implements Serializable{
	private Long uuid;
	
	private Integer num;
	
	private GoodsModel gm;
	private StoreModel sm;
	
	public Long getUuid() {
		return uuid;
	}
	private void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	private void setNum(Integer num) {
		this.num = num;
	}
	public GoodsModel getGm() {
		return gm;
	}
	private void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public StoreModel getSm() {
		return sm;
	}
	private void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}