package cn.itcast.invoice.invoice.order.vo;

import java.io.Serializable;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.util.format.FormatUtil;

public class OrderDetailModel implements Serializable{
	
	private Long uuid;
	private Integer num;
	private Integer surplus;
	private Double price;
	
	private String priceView;
	
	private GoodsModel gm;
	private OrderModel om;
	
	public String getPriceView() {
		return priceView;
	}
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
	public Double getPrice() {
		return price;
	}
	private void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatUtil.formatMoney(price);
	}
	public GoodsModel getGm() {
		return gm;
	}
	private void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public OrderModel getOm() {
		return om;
	}
	private void setOm(OrderModel om) {
		this.om = om;
	}
	public Integer getSurplus() {
		return surplus;
	}
	private void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	
	
}