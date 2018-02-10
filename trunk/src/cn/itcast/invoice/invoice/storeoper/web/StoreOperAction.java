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
	public StoreOperModel sm = new StoreOperModel();
	public StoreOperQueryModel sqm = new StoreOperQueryModel();

	private StoreOperEbi storeOperEbi;
	public void setStoreOperEbi(StoreOperEbi storeOperEbi) {
		this.storeOperEbi = storeOperEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		setDataTotal(storeOperEbi.getCount(sqm));
		List<StoreOperModel> storeOperList = storeOperEbi.getAll(sqm,pageNum,pageCount);
		put("storeOperList",storeOperList);
		return LIST;
	}

	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(sm.getUuid()== null){
			storeOperEbi.save(sm);
		}else{
			storeOperEbi.update(sm);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		if(sm.getUuid()!=null){
			sm = storeOperEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		storeOperEbi.delete(sm);
		return TO_LIST;
	}

}
