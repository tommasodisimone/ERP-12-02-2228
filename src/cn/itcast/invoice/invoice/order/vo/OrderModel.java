package cn.itcast.invoice.invoice.order.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.format.FormatUtil;
/**
 * this class implements Serializable
 *
 */
public class OrderModel implements Serializable{
	public static final Integer ORDER_TYPE_OF_BUY_NO_CHECK = 111;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_NO_PASS = 120;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_PASS = 121;
	public static final Integer ORDER_TYPE_OF_BUY_BUYING = 131;
	public static final Integer ORDER_TYPE_OF_BUY_IN_STORE = 141;
	public static final Integer ORDER_TYPE_OF_BUY_END = 199;
	
	public static final String ORDER_TYPE_OF_BUY_NO_CHECK_VIEW = "Ã¦Å“ÂªÃ¥Â®Â¡Ã¦Â Â¸";
	public static final String ORDER_TYPE_OF_BUY_CHECK_NO_VIEW = "Ã©Â©Â³Ã¥â€ºÅ¾";
	public static final String ORDER_TYPE_OF_BUY_CHECK_OK_VIEW = "Ã¤Â»Â»Ã¥Å Â¡Ã¦Å’â€¡Ã¦Â´Â¾Ã¤Â¸Â­";
	public static final String ORDER_TYPE_OF_BUY_BUYING_VIEW = "Ã©â€¡â€¡Ã¨Â´Â­Ã¤Â¸Â­";
	public static final String ORDER_TYPE_OF_BUY_IN_STORE_VIEW = "Ã¥â€¦Â¥Ã¥Âºâ€œÃ¤Â¸Â­";
	public static final String ORDER_TYPE_OF_BUY_END_VIEW = "Ã¥Â·Â²Ã§Â»â€œÃ¥ï¿½â€¢";
	
	public static final Map<Integer, String> orderTypeMap = new HashMap<Integer, String>();
	public static final Map<Integer, String> buyTypeMap = new TreeMap<Integer, String>();
	public static final Map<Integer, String> saleTypeMap = new TreeMap<Integer, String>();
	private static final Map<Integer, String> typeMap = new TreeMap<Integer, String>();
	
	public static final Integer ORDER_ORDERTYPE_OF_BUY = 1;
	public static final Integer ORDER_ORDERTYPE_OF_SALE = 2;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_BUY = 3;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_SALE = 4;
	
	public static final String ORDER_ORDERTYPE_OF_BUY_VIEW = "Ã©â€¡â€¡Ã¨Â´Â­";
	public static final String ORDER_ORDERTYPE_OF_SALE_VIEW = "Ã©â€�â‚¬Ã¥â€�Â®";
	public static final String ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW = "Ã©â€¡â€¡Ã¨Â´Â­Ã©â‚¬â‚¬Ã¨Â´Â§";
	public static final String ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW = "Ã©â€�â‚¬Ã¥â€�Â®Ã©â‚¬â‚¬Ã¨Â´Â§";
	
	static{
		orderTypeMap.put(ORDER_ORDERTYPE_OF_BUY,ORDER_ORDERTYPE_OF_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_SALE,ORDER_ORDERTYPE_OF_SALE_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_BUY,ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_SALE,ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW);
		
		buyTypeMap.put(ORDER_TYPE_OF_BUY_NO_CHECK, ORDER_TYPE_OF_BUY_NO_CHECK_VIEW);
		buyTypeMap.put(ORDER_TYPE_OF_BUY_CHECK_NO_PASS, ORDER_TYPE_OF_BUY_CHECK_NO_VIEW);
		buyTypeMap.put(ORDER_TYPE_OF_BUY_CHECK_PASS, ORDER_TYPE_OF_BUY_CHECK_OK_VIEW);
		buyTypeMap.put(ORDER_TYPE_OF_BUY_BUYING, ORDER_TYPE_OF_BUY_BUYING_VIEW);
		buyTypeMap.put(ORDER_TYPE_OF_BUY_IN_STORE, ORDER_TYPE_OF_BUY_IN_STORE_VIEW);
		buyTypeMap.put(ORDER_TYPE_OF_BUY_END, ORDER_TYPE_OF_BUY_END_VIEW);
		
		typeMap.putAll(buyTypeMap);
	}
	
	//typeÃ§Å Â¶Ã¦â‚¬ï¿½Ã§Â±Â»Ã¥Å¾â€¹
	/*
	Ã§â€�Â³Ã¨Â¯Â·Ã¦Å“ÂªÃ¥Â®Â¡Ã¦â€°Â¹					Ã¦Å“ÂªÃ¥Â®Â¡Ã¦Â Â¸			111
	Ã§â€�Â³Ã¨Â¯Â·Ã¥Â·Â²Ã¥Â®Â¡Ã¦â€°Â¹Ã©Â©Â³Ã¥â€ºÅ¾				Ã©Â©Â³Ã¥â€ºÅ¾				120
	Ã§â€�Â³Ã¨Â¯Â·Ã¥Â·Â²Ã¥Â®Â¡Ã¦â€°Â¹Ã©â‚¬Å¡Ã¨Â¿â€¡/Ã¦Å“ÂªÃ¦Å’â€¡Ã¦Â´Â¾Ã¤Â»Â»Ã¥Å Â¡Ã¤ÂºÂº	Ã¤Â»Â»Ã¥Å Â¡Ã¦Å’â€¡Ã¦Â´Â¾Ã¤Â¸Â­			121
	Ã¥Â·Â²Ã¦Å’â€¡Ã¦Â´Â¾Ã¤Â»Â»Ã¥Å Â¡Ã¤ÂºÂº/Ã¤Â»Â»Ã¥Å Â¡Ã¦Å“ÂªÃ¥Â®Å’Ã¦Ë†ï¿½		Ã©â€¡â€¡Ã¨Â´Â­Ã¤Â¸Â­			131
	Ã¤Â»Â»Ã¥Å Â¡Ã¥Â·Â²Ã§Â»ï¿½Ã¥Â®Å’Ã¦Ë†ï¿½/Ã¨Â®Â¢Ã¥ï¿½â€¢Ã¦Å“ÂªÃ¥â€¦Â¥Ã¥Âºâ€œ		Ã¥â€¦Â¥Ã¥Âºâ€œÃ¤Â¸Â­			141
	Ã¨Â®Â¢Ã¥ï¿½â€¢Ã¥â€¦Â¥Ã¥Âºâ€œÃ¥Â®Å’Ã¦Â¯â€¢				Ã¥Â·Â²Ã§Â»â€œÃ¥ï¿½â€¢			199
	*/
	/*
	211	Ã¦Å“ÂªÃ¥Â®Â¡Ã¦Â Â¸									
	220	Ã©Â©Â³Ã¥â€ºÅ¾
	221	Ã¤Â»Â»Ã¥Å Â¡Ã¦Å’â€¡Ã¦Â´Â¾Ã¤Â¸Â­
	231	Ã¥â€¡ÂºÃ¥Âºâ€œÃ¤Â¸Â­
	241	Ã©â‚¬ï¿½Ã¨Â´Â§Ã¤Â¸Â­
	299	Ã¥Â·Â²Ã§Â»â€œÃ¥ï¿½â€¢
	*/
	
	private Long segreto;
	private String orderNum;

	private Integer totalNum;
	
	private Double totalPrice;
	
	private Integer orderType;
	private Integer type;
	
	private Long createTime;
	private Long checkTime;
	private Long completeTime;
	
	//Ã¨Â§â€ Ã¥â€ºÂ¾Ã¥â‚¬Â¼
	private String totalPriceView;
	private String createTimeView;
	private String checkTimeView;
	private String completeTimeView;
	private String orderTypeView;
	private String typeView;
	
	//Ã¥â€¦Â³Ã§Â³Â»
	private EmpModel creater;
	private EmpModel checker;
	private EmpModel completer;
	private SupplierModel sm;
	private Set<OrderDetailModel> odms;

	public Set<OrderDetailModel> getOdms() {
		return odms;
	}
	public void setOdms(Set<OrderDetailModel> odms) {
		this.odms = odms;
	}
	
	
	/**
	 * 
	 * @param check insert 0 to get totalPriceView, 1 to get orderTypeView, 2 to get typeView
	 * @return a string with the desidered value
	 */
	public String getCartView(int check) {
		String toReturn= null;
		if (check==0) {
			toReturn = totalPriceView;

		} else
		if (check==1) {
			toReturn = orderTypeView;

		} else
		if (check==2) {
			toReturn = typeView;
		}
		else {
			toReturn = "The Value is not correct, please read documentation!";
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @param check insert 0 to get createTimeView, 1 to get checkTimeView, 2 to get completeTimeView
	 * @return a string with the desidered value
	 */
	public String getTimeView(int check) {
		String toReturn= null;
		if (check==0) {
			toReturn = createTimeView;

		} else
		if (check==1) {
			toReturn = checkTimeView;

		} else
		if (check==2) {
			toReturn = completeTimeView;
		}
		else {
			toReturn = "The Value is not correct, please read documentation!";
		}
		
		return toReturn;
	}
	
	public Long getUuid() {
		return segreto;
	}
	public void setSegreto(Long segreto) {
		this.segreto = segreto;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		this.totalPriceView = FormatUtil.formatMoney(totalPrice);
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
		this.orderTypeView =orderTypeMap.get(orderType);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		this.createTimeView = FormatUtil.formatDateTime(createTime);
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
		this.checkTimeView = FormatUtil.formatDateTime(checkTime);
	}
	public Long getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
		this.completeTimeView = FormatUtil.formatDateTime(completeTime);
	}
	public EmpModel getCreater() {
		return creater;
	}
	public void setCreater(EmpModel creater) {
		this.creater = creater;
	}
	public EmpModel getChecker() {
		return checker;
	}
	public void setChecker(EmpModel checker) {
		this.checker = checker;
	}
	public EmpModel getCompleter() {
		return completer;
	}
	public void setCompleter(EmpModel completer) {
		this.completer = completer;
	}
	public SupplierModel getSm() {
		return sm;
	}
	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	
}