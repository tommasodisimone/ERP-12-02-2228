package cn.itcast.invoice.invoice.goods.vo;

import java.io.Serializable;

import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.invoice.util.format.FormatUtil;

public class GoodsModel implements Serializable{
	private Long uuid;
	
	private String name;
	private String origin;
	private String producer;
	private String unit;
	private Integer useNum;
	private Integer maxNum;
	private Integer minNum;
	
	private Double inPrice;
	private Double outPrice;
	
	private String inPriceView;
	private String outPriceView;
	
	//关系
	private GoodsTypeModel gtm;
	
	public String getInPriceView() {
		return inPriceView;
	}
	
	public Integer getMaxNum() {
		return maxNum;
	}

	private void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}


	public Integer getMinNum() {
		return minNum;
	}


	private void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}


	public String getOutPriceView() {
		return outPriceView;
	}


	public Integer getUseNum() {
		return useNum;
	}

	private void setUseNum(Integer useNum) {
		this.useNum = useNum;
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

	public String getOrigin() {
		return origin;
	}

	private void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProducer() {
		return producer;
	}

	private void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUnit() {
		return unit;
	}

	private void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getInPrice() {
		return inPrice;
	}

	private void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
		this.inPriceView = FormatUtil.formatMoney(inPrice);
	}

	public Double getOutPrice() {
		return outPrice;
	}

	private void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
		this.outPriceView = FormatUtil.formatMoney(outPrice);
	}

	public GoodsTypeModel getGtm() {
		return gtm;
	}

	private void setGtm(GoodsTypeModel gtm) {
		this.gtm = gtm;
	}
	
}