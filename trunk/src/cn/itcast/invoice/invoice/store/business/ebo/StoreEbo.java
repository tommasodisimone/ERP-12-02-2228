package cn.itcast.invoice.invoice.store.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.order.dao.dao.OrderDetailDao;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.store.business.ebi.StoreEbi;
import cn.itcast.invoice.invoice.store.dao.dao.StoreDao;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
import cn.itcast.invoice.invoice.storedetail.dao.dao.StoreDetailDao;
import cn.itcast.invoice.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.invoice.invoice.storeoper.dao.dao.StoreOperDao;
import cn.itcast.invoice.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.invoice.util.base.BaseQueryModel;
import cn.itcast.invoice.util.exception.AppException;
/**
 * this class implements StoreEbi
 *
 */
public class StoreEbo implements StoreEbi{
	private StoreDao storeDao;
	private StoreDetailDao storeDetailDao;
	private OrderDetailDao orderDetailDao;
	private StoreOperDao storeOperDao;
	
	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void save(StoreModel sm) {
		storeDao.save(sm);
	}

	public void delete(StoreModel sm) {
		storeDao.delete(sm);
	}

	public void update(StoreModel sm) {
		storeDao.update(sm);
	}

	public StoreModel get(Serializable uuid) {
		return storeDao.get(uuid);
	}

	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}

	public List<StoreModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storeDao.getCount(qm);
	}

	public OrderDetailModel inGoods(Long odmUuid ,Long goodsUuid, Long storeUuid, Integer num,EmpModel login) {
		GoodsModel gm = new GoodsModel();
		gm.setUuid(goodsUuid);
		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);
		
		
		//å…¥åº“ç©¶ç«Ÿè¦�å�šä»€ä¹ˆï¼Ÿ
		//1.åŽŸå§‹è®¢å�•æ˜Žç»†ä¸­çš„å·²å…¥åº“æ•°é‡�æ›´æ–°
		//update è®¢å�•æ˜Žç»†  
		//å¿«ç…§æ›´æ–°
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		
		//æ ¡éªŒ
		if(odm.getSurplus() < num){
			throw new AppException("aa");
		}
		
		
		odm.setSurplus(odm.getSurplus()-num);
		
		//2.è®°å½•å…¥åº“çš„è®°å½•
		StoreOperModel som = new StoreOperModel();
		//å…¥åº“æ“�ä½œæ—¶é—´
		som.setOperTime(System.currentTimeMillis());
		//æœ¬æ¬¡æ“�ä½œæ•°é‡�
		som.setNum(num);
		//è®¾ç½®æ“�ä½œç±»åž‹ä¸ºå…¥åº“
		som.setType(StoreOperModel.STOREOPER_TYPE_OF_IN);
		//è®¾ç½®æ“�ä½œçš„å•†å“�
		som.setGm(gm);
		//è®¾ç½®æ“�ä½œäºº
		som.setEm(login);
		//è®¾ç½®å¯¹åº”çš„ä»“åº“
		som.setSm(sm);
		//è®¾ç½®æ“�ä½œå¯¹åº”çš„è®¢å�•
		som.setOm(odm.getOm());
		storeOperDao.save(som);
		
		//3.ä»“åº“ä¸­çš„çŽ°æœ‰å•†å“�æ•°é‡�æ›´æ–°
		//A Bä¸¤ä¸ªä»“åº“
		//å…¥Xå•†å“�ï¼ŒAä»“åº“Xå•†å“�100ä¸ªï¼ŒBä»“åº“ä»Žæ²¡æœ‰æ”¾è¿‡Xå•†å“�
		//Xå•†å“�å…¥B
		//æ ¹æ�®å•†å“�uuidä¸Žä»“åº“çš„uuidèŽ·å�–å•†å“�åœ¨ä»“åº“ä¸­çš„æ•°æ�®è®°å½•
		StoreDetailModel sdm = storeDetailDao.getBySmAndGm(storeUuid,goodsUuid);
		if(sdm == null){
			//è¯¥ä»“åº“ä¸­æ²¡æœ‰å­˜å‚¨è¿‡è¯¥å•†å“�
			//åˆ�å§‹åŒ–æ•°æ�®ï¼Œsave
			sdm = new StoreDetailModel();
			sdm.setNum(num);
			sdm.setGm(gm);
			sdm.setSm(sm);
			storeDetailDao.save(sdm);
		}else{
			//è¯¥ä»“åº“ä¸­å­˜å‚¨è¿‡è¯¥å•†å“�
			//åˆ©ç”¨å¿«ç…§æ›´æ–°æ•°é‡�
			sdm.setNum(sdm.getNum()+num);
		}
		
		//4.å½“è®¢å�•ä¸­çš„æ‰€æœ‰å•†å“�å…¨éƒ¨å…¥åº“å®Œæ¯•å�Žï¼Œä¿®æ”¹è®¢å�•çš„çŠ¶æ€�ï¼Œå�Œæ—¶ä¿®æ”¹å®Œæˆ�æ—¶é—´
		OrderModel om = odm.getOm();
		int sum = 0;
		for(OrderDetailModel temp:om.getOdms()){
			sum += temp.getSurplus();
		}
		if(sum == 0){
			//å¿«ç…§æ›´æ–°
			//ä¿®æ”¹è®¢å�•çŠ¶æ€�
			om.setType(OrderModel.ORDER_TYPE_OF_BUY_END);
			//ä¿®æ”¹ç»“å�•æ—¶é—´
			om.setCompleteTime(System.currentTimeMillis());
		}
		return odm;
	}

}
