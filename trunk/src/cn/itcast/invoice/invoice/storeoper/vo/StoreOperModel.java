package cn.itcast.invoice.invoice.storeoper.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
/**
 * this class implements Serializable
 *
 */
public class StoreOperModel implements Serializable{
	
	/**
	 * this public element is a public element
	 *
	 */
	public static final Integer STOREOPER_TYPE_OF_IN = 1;
	/**
	 * this public element is a public element
	 *
	 */
	public static final Integer STOREOPER_TYPE_OF_OUT = 2;
	
	/**
	 * this public element is a public element
	 *
	 */
	public static final String STOREOPER_TYPE_OF_IN_VIEW = "入库";
	/**
	 * this public element is a public element
	 *
	 */
	public static final String STOREOPER_TYPE_OF_OUT_VIEW = "出库";
	
	/**
	 * this public element is a public element
	 *
	 */
	public static final Map<Integer, String> typeMap = new HashMap<Integer, String>();
	
	static{
		typeMap.put(STOREOPER_TYPE_OF_IN, STOREOPER_TYPE_OF_IN_VIEW);
		typeMap.put(STOREOPER_TYPE_OF_OUT, STOREOPER_TYPE_OF_OUT_VIEW);
	}
	
	private Long segreto;
	private Long operTime;
	private Integer num;
	private Integer type;
	
	private String typeView;
	
	private GoodsModel gm;
	private EmpModel em;
	private OrderModel om;
	private StoreModel sm;
	
	/**
	 * this public element is a public element
	 *
	 */
	public String getTypeView() {
		return typeView;
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
	public Long getOperTime() {
		return operTime;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	/**
	 * this public element is a public element
	 *
	 */
	public GoodsModel getGm() {
		return gm;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public EmpModel getEm() {
		return em;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setEm(EmpModel em) {
		this.em = em;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public OrderModel getOm() {
		return om;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setOm(OrderModel om) {
		this.om = om;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public StoreModel getSm() {
		return sm;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}