package cn.itcast.invoice.invoice.store.web;

import java.util.List;

import cn.itcast.invoice.auth.emp.business.ebi.EmpEbi;
import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.store.business.ebi.StoreEbi;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
import cn.itcast.invoice.invoice.store.vo.StoreQueryModel;
import cn.itcast.invoice.util.base.BaseAction;
import cn.itcast.invoice.util.exception.AppException;
/**
 * this class extends BaseAction
 *
 */
public class StoreAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public StoreModel sm = new StoreModel();
	/**
	 * this public element is a public element
	 *
	 */
	public StoreQueryModel sqm = new StoreQueryModel();
	
	/**
	 * this public element is a public element
	 *
	 */
	private StoreEbi storeEbi;
	/**
	 * this public element is a public element
	 *
	 */
	private EmpEbi empEbi;
	
	/**
	 * this public element is a public element
	 *
	 */
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(storeEbi.getCount(sqm));
		List<StoreModel> storeList = storeEbi.getAll(sqm,pageNum,pageCount);
		put("storeList",storeList);
		return LIST;
	}

	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(sm.getUuid()== null){
			storeEbi.save(sm);
		}else{
			storeEbi.update(sm);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		List<EmpModel> empList = empEbi.getAll();
		put("empList",empList);
		if(sm.getUuid()!=null){
			sm = storeEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		storeEbi.delete(sm);
		return TO_LIST;
	}
	
	/**
	 * this public element is a public element
	 *
	 */
	public Long goodsUuid;
	/**
	 * this public element is a public element
	 *
	 */
	public Integer num;
	/**
	 * this public element is a public element
	 *
	 */
	public Long storeUuid;
	/**
	 * this public element is a public element
	 *
	 */
	public Long odmUuid;
	private OrderDetailModel odm;
	/**
	 * this public element is a public element
	 *
	 */
	public OrderDetailModel getOdm() {
		return odm;
	}
	private boolean has;
	/**
	 * this public element is a public element
	 *
	 */
	public boolean isHas() {
		return has;
	}
	private boolean msg;
	/**
	 * this public element is a public element
	 *
	 */
	public boolean isMsg() {
		return msg;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public String ajaxInGoods(){
		try {
			odm = storeEbi.inGoods(odmUuid,goodsUuid,storeUuid,num,getLogin());
		} catch (AppException e) {
			if(e.getMessage().equals("aa")){
				msg = true;
				return "ajaxInGoods";
			}
		}
		
		OrderModel om = odm.getOm();
		int sum = 0;
		for(OrderDetailModel temp:om.getOdms()){
			sum += temp.getSurplus();
		}
		has = sum == 0;
		return "ajaxInGoods";
	}
}
