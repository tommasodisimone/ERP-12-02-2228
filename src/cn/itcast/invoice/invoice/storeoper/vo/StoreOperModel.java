package cn.itcast.invoice.invoice.storeoper.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.store.vo.StoreModel;

public class StoreOperModel implements Serializable{
	
	public static final Integer STOREOPER_TYPE_OF_IN = 1;
	public static final Integer STOREOPER_TYPE_OF_OUT = 2;
	
	public static final String STOREOPER_TYPE_OF_IN_VIEW = "入库";
	public static final String STOREOPER_TYPE_OF_OUT_VIEW = "出库";
	
	public static final Map<Integer, String> typeMap = new HashMap<Integer, String>();
	
	static{
		typeMap.put(STOREOPER_TYPE_OF_IN, STOREOPER_TYPE_OF_IN_VIEW);
		typeMap.put(STOREOPER_TYPE_OF_OUT, STOREOPER_TYPE_OF_OUT_VIEW);
	}
	
	private Long uuid;
	private Long operTime;
	private Integer num;
	private Integer type;
	
	private String typeView;
	
	private GoodsModel gm;
	private EmpModel em;
	private OrderModel om;
	private StoreModel sm;
	
	public String getTypeView() {
		return typeView;
	}
	public Long getUuid() {
		return uuid;
	}
	private void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getOperTime() {
		return operTime;
	}
	private void setOperTime(Long operTime) {
		this.operTime = operTime;
	}
	public Integer getNum() {
		return num;
	}
	private void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	private void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public GoodsModel getGm() {
		return gm;
	}
	private void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public EmpModel getEm() {
		return em;
	}
	private void setEm(EmpModel em) {
		this.em = em;
	}
	public OrderModel getOm() {
		return om;
	}
	private void setOm(OrderModel om) {
		this.om = om;
	}
	public StoreModel getSm() {
		return sm;
	}
	private void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}