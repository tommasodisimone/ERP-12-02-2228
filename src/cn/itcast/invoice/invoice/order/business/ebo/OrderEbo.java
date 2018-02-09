package cn.itcast.invoice.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.order.business.ebi.OrderEbi;
import cn.itcast.invoice.invoice.order.dao.dao.OrderDao;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.order.vo.OrderQueryModel;
import cn.itcast.invoice.util.base.BaseQueryModel;
import cn.itcast.invoice.util.exception.AppException;
import cn.itcast.invoice.util.format.MD5Utils;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}
	
	public void save(EmpModel em,OrderModel om, Long[] goodsUuids, Integer[] nums,Double[] prices) {
		//å°†è®¢å�•ä¿¡æ�¯ç»„ç»‡å¥½ï¼Œä¿�å­˜
		//omä¸­ä¿�å­˜æœ‰å¯¹åº”ä¾›åº”å•†çš„uuid
		//è®¾ç½®è®¢å�•å�·:ç³»ç»Ÿæ—¶é—´+ç™»é™†äººuuid
		String orderNum = System.currentTimeMillis()+""+em.getUuid();
		om.setOrderNum(MD5Utils.md5(orderNum));
		//è®¾ç½®è®¢å�•ç±»åˆ«
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//è®¾ç½®è®¢å�•çŠ¶æ€�
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//è®¾ç½®è®¢å�•åˆ›å»ºæ—¶é—´ä¸ºå½“å‰�ç³»ç»Ÿæ—¶é—´
		om.setCreateTime(System.currentTimeMillis());
		//è®¾ç½®å½“å‰�ç™»é™†äººä¸ºåˆ¶å�•äºº
		om.setCreater(em);
		
		Integer totalNum = 0;
		Double totalPrice = 0.0d;
		OrderDetailModel odm = new OrderDetailModel();
		GoodsModel gm = new GoodsModel();
		//å°†è®¢å�•æ˜Žç»†ä¿¡æ�¯ç»„ç»‡åŒ…ï¼Œä¿�å­˜
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for(int i = 0;i<goodsUuids.length;i++){
			Long goodsUuid = goodsUuids[i];
			Integer num = nums[i];
			Double price = prices[i];
			
			totalNum+=num;
			totalPrice+=num*price;
			
			
			odm.setNum(num);
			//è®¾ç½®è®¢å�•æ˜Žç»†ä¸­å½“å‰�è´§ç‰©å®Œæˆ�é‡�ä¸ºè®¢å�•å•†å“�è´§ç‰©æ€»é‡�
			odm.setSurplus(num);
			odm.setPrice(price);
			
			
			gm.setUuid(goodsUuid);
			odm.setGm(gm);
			//ç»‘å®šæ˜Žç»†åˆ°è®¢å�•çš„å…³ç³»
			odm.setOm(om);
			odms.add(odm);
		}
		//è®¾ç½®æ‰€æœ‰çš„è®¢å�•æ˜Žç»†é›†å�ˆ
		om.setOdms(odms);
		//è®¾ç½®å•†å“�æ€»æ•°é‡�
		om.setTotalNum(totalNum);
		//è®¾ç½®è®¢å�•æ€»ä»·æ ¼
		om.setTotalPrice(totalPrice);
		//çŽ°åœ¨çš„çŠ¶æ€�ï¼šomä¸­åŒ…å�«æœ‰odms ,odmsä¸­çš„odmåŒ…å�«om
		//å½“ä½¿ç”¨çº§è�”æ·»åŠ æ—¶ï¼Œä¿�å­˜çš„æ˜¯omï¼ŒåŸºäºŽå…³è�”å…³ç³»ï¼Œä¼šçº§è�”åˆ°odmsä¸­çš„æ‰€æœ‰å¯¹è±¡
		//è°�ç»™orderDetailè¡¨ä¸­çš„orderUuidèµ‹å€¼çš„  update?insert?
		//æ­¤å¤„è®¾ç½®äº†cascade=save-updateé‚£ä¹ˆï¼Œä¿�å­˜omå°†ä¿�å­˜å…¶ä¸­odmsä¸­çš„odm
		//inverse=trueåˆ™æ–­å¼€äº†omç»´æŠ¤odmä¸­çš„å…³è�”å…³ç³»çš„å�¯èƒ½æ€§updateå°†ä¸�æ‰§è¡Œ
		//ç”±äºŽodmä¸­ç»‘å®šäº†ä¸Žomçš„å…³ç³»ï¼Œå› æ­¤åœ¨æ·»åŠ æ—¶ï¼Œinsertè¯­å�¥ä¸­å°†å‡ºçŽ°orderUuidè¿™ä¸ªå­—æ®µ
		orderDao.save(om);
	}

	
	private Integer[] buyCheckTypes = {
			OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK,
			//OrderModel.ORDER_TYPE_OF_BUY_RETURN_NO_CHECK
			};
	public List<OrderModel> getAllNoCheckOrder(OrderQueryModel oqm,Integer pageNum, Integer pageCount) {
			//é‡‡è´­æœªå®¡æ ¸
			//é‡‡è´­é€€è´§æœªå®¡æ ¸
			//å°†ä¸¤ç§�çŠ¶æ€�æ��äº¤åˆ°æ•°æ�®å±‚	é‡‡è´­æœªå®¡æ ¸çŠ¶æ€�ï¼Œé‡‡è´­é€€è´§æœªå®¡æ ¸çŠ¶æ€�
			//ä¼ é€’çš„æ�¡ä»¶æ˜¯å¤šä¸ªå€¼ï¼Œå› æ­¤éœ€è¦�å°†æ•°æ�®è¿›è¡ŒåŒ…è£…ï¼Œç§°ä¸ºæ•°ç»„/é›†å�ˆ
		return orderDao.getAllByTypes(oqm,pageNum,pageCount,buyCheckTypes);
	}

	public Integer getCountByTypes(OrderQueryModel oqm) {
		return orderDao.getCountByTypes(oqm,buyCheckTypes);
	}

	public void buyCheckPass(Long uuid,EmpModel em) {
		//å¦‚æžœè¯¥è®¢å�•æ²¡æœ‰å®¡æ ¸
		//ä¿®æ”¹çŠ¶æ€�å°†æœªå®¡æ ¸çŠ¶æ€�ä¿®æ”¹ä¸ºå®¡æ ¸é€šè¿‡çŠ¶æ€�	ORDER_TYPE_OF_BUY_CHECK_PASS
		//å¿«ç…§æ›´æ–°
		OrderModel om = orderDao.get(uuid);
		//é€»è¾‘åˆ¤å®š
		if(!Arrays.asList(buyCheckTypes).contains(om.getType())){
			throw new AppException("å¯¹ä¸�èµ·,è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�");
		}
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		//è°�ä»€ä¹ˆæ—¶é—´å®¡æ ¸çš„ï¼Ÿ
		om.setCheckTime(System.currentTimeMillis());
		om.setChecker(em);
	}

	public void buyCheckNoPass(Long uuid,EmpModel em) {
		OrderModel om = orderDao.get(uuid);
		//é€»è¾‘åˆ¤å®š
		if(!Arrays.asList(buyCheckTypes).contains(om.getType())){
			throw new AppException("å¯¹ä¸�èµ·,è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�");
		}
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
		om.setCheckTime(System.currentTimeMillis());
		om.setChecker(em);
	}
	private Integer[] taskTypes = {
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
			OrderModel.ORDER_TYPE_OF_BUY_END,
			//ç¼ºå°‘12ç§�
			//å…±è®¡16ç§�çŠ¶æ€�
			};
	public List<OrderModel> getAllTasks(OrderQueryModel oqm, Integer pageNum,Integer pageCount) {
		//èŽ·å�–çš„æ•°æ�®æœ‰å“ªäº›ï¼Ÿæ— è®ºä½•ç§�ç±»åˆ«çš„è®¢å�•ï¼Œå�ªè¦�æ˜¯å®¡æ ¸é€šè¿‡å�Žï¼Œæ‰€æœ‰çŠ¶æ€�å�‡æ˜¾ç¤º
		return orderDao.getAllByTypes(oqm, pageNum, pageCount, taskTypes);
	}
	
	public static Integer[] taskTypes2 = {
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			//ç¼ºå°‘3ç§�
			//å…±è®¡4ç§�çŠ¶æ€�
			};
	
	public static final Set<Integer> taskTypesSet = new HashSet<Integer>();
	static{
		taskTypesSet.add(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		//taskTypesSet.add(OrderModel.);
		//taskTypesSet.add(OrderModel.);
		//taskTypesSet.add(OrderModel.);
	}
	
	public void assignTask(OrderModel om) {
		OrderModel temp = orderDao.get(om.getUuid());
		if(!Arrays.asList(taskTypes2).contains(temp.getType())){
			throw new AppException("å¯¹ä¸�èµ·,è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�");
		}
		//å½“å‰�ä»»åŠ¡åˆ†é…�å®Œæ¯•å�Žï¼Œåˆ‡æ�¢çŠ¶æ€�ä¸ºæ­£åœ¨é‡‡è´­
		//é‡‡è´­å’Œé‡‡è´­é€€è´§éƒ½å½’å�Œä¸€ä¸ªäººå®¡æ‰¹
		//if(åŽŸå§‹æ˜¯é‡‡è´­ä¸šåŠ¡ï¼Œä¿®æ”¹ä¸ºé‡‡è´­....)
		//else if(åŽŸå§‹æ˜¯é‡‡è´­é€€è´§ä»»åŠ¡,ä¿®æ”¹ä¸ºé‡‡è´­é€€è´§....)
		//else if(åŽŸå§‹æ˜¯é‡‡è´­é€€è´§ä»»åŠ¡,ä¿®æ”¹ä¸ºé‡‡è´­é€€è´§....)
		//else if(åŽŸå§‹æ˜¯é‡‡è´­é€€è´§ä»»åŠ¡,ä¿®æ”¹ä¸ºé‡‡è´­é€€è´§....)
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		//ä¿®æ”¹ä»»åŠ¡äºº
		temp.setCompleter(om.getCompleter());
	}

	public List<OrderModel> getAllByCompleter(OrderQueryModel oqm,Integer pageNum, Integer pageCount, EmpModel login) {
		oqm.setCompleter(login);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public void endTask(Long uuid) {
		OrderModel om = orderDao.get(uuid);
		/*
		if(....){
			throw new AppException("å¯¹ä¸�èµ·,è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�");
		}
		*/
		//ä»…éœ€è¦�ä¿®æ”¹ä¸€ä¸ªçŠ¶æ€�
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	private Integer[] inTypes = {
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
			//ç¼ºå°‘1ç§�
			};
	public List<OrderModel> getAllNotIn(OrderQueryModel oqm, Integer pageNum,Integer pageCount) {
		//
		return orderDao.getAllByTypes(oqm, pageNum, pageCount, inTypes);
	}

}
