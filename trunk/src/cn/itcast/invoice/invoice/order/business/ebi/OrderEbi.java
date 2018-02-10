package cn.itcast.invoice.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.order.vo.OrderQueryModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this interface extends BaseEbi<OrderModel>
 *
 */
@Transactional
public interface OrderEbi extends BaseEbi<OrderModel> {
	/**
	 * ä¿�å­˜é‡‡è´­è®¢å�•
	 * @param empModel å½“å‰�ç™»é™†äºº(åˆ¶å�•äºº)
	 * @param om è®¢å�•
	 * @param goodsUuids è®¢å�•æ˜Žç»†è´§ç‰©uuid
	 * @param nums è®¢å�•æ˜Žç»†æ•°é‡�
	 * @param prices è®¢å�•æ˜Žç»†ä»·æ ¼
	 */
	public void save(EmpModel empModel, OrderModel om, Long[] goodsUuids, Integer[] nums, Double[] prices);
	/**
	 * èŽ·å�–æ‰€æœ‰æœªå®¡æ ¸çš„é‡‡è´­ç›¸å…³çš„è®¢å�•
	 * @param pageCount 
	 * @param pageNum 
	 * @param oqm 
	 * @return
	 */
	public List<OrderModel> getAllNoCheckOrder(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	public Integer getCountByTypes(OrderQueryModel oqm);
	/**
	 * é‡‡è´­è®¢å�•å®¡æ ¸é€šè¿‡
	 * @param uuid å¾…å®¡æ ¸è®¢å�•ç¼–å�·
	 * @param em å®¡æ ¸äºº
	 */
	public void buyCheckPass(Long uuid, EmpModel em);
	/**
	 * é‡‡è´­è®¢å�•å®¡æ ¸é©³å›ž
	 * @param uuid å¾…å®¡æ ¸è®¢å�•ç¼–å�·
	 * @param em å®¡æ ¸äºº
	 */
	public void buyCheckNoPass(Long uuid, EmpModel em);
	/**
	 * èŽ·å�–æ‰€æœ‰æœªæŒ‡æ´¾ä»»åŠ¡äººçš„è®¢å�•ä»»åŠ¡
	 * @param pageCount 
	 * @param pageNum 
	 * @param oqm 
	 * @return
	 */
	public List<OrderModel> getAllTasks(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
	/**
	 * æŒ‡æ´¾ä»»åŠ¡
	 * @param om
	 */
	public void assignTask(OrderModel om);
	public List<OrderModel> getAllByCompleter(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, EmpModel login);
	public void endTask(Long uuid);
	/**
	 * èŽ·å�–æ‰€æœ‰æœªå®Œå…¨å…¥åº“çš„è®¢å�•ä¿¡æ�¯
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllNotIn(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);
}
