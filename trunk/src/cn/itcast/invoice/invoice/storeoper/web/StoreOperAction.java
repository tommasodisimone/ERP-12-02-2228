package cn.itcast.invoice.invoice.storeoper.web;

import java.util.List;

import cn.itcast.invoice.invoice.storeoper.business.ebi.StoreOperEbi;
import cn.itcast.invoice.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.invoice.invoice.storeoper.vo.StoreOperQueryModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class StoreOperAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public StoreOperModel sm = new StoreOperModel();
	/**
	 * this public element is a public element
	 *
	 */
	public StoreOperQueryModel sqm = new StoreOperQueryModel();

	private StoreOperEbi storeOperEbi;
	/**
	 * this public element is a public element
	 *
	 */
	public void setStoreOperEbi(StoreOperEbi storeOperEbi) {
		this.storeOperEbi = storeOperEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(storeOperEbi.getCount(sqm));
		List<StoreOperModel> storeOperList = storeOperEbi.getAll(sqm,pageNum,pageCount);
		put("storeOperList",storeOperList);
		return LIST;
	}

	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(sm.getUuid()== null){
			storeOperEbi.save(sm);
		}else{
			storeOperEbi.update(sm);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		if(sm.getUuid()!=null){
			sm = storeOperEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		storeOperEbi.delete(sm);
		return TO_LIST;
	}

}
