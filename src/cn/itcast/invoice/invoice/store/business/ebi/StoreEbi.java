package cn.itcast.invoice.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this interface extends BaseEbi<StoreModel>
 *
 */
@Transactional
public interface StoreEbi extends BaseEbi<StoreModel> {
	/**
	 * å…¥åº“
	 * @param odmUuid	è®¢å�•æ˜Žç»†ç¼–å�· 
	 * @param goodsUuid å•†å“�ç¼–å�·
	 * @param storeUuid ä»“åº“ç¼–å�·
	 * @param num æ“�ä½œæ•°é‡�
	 * @param login
	 * 
	 */
	public OrderDetailModel inGoods(Long odmUuid ,Long goodsUuid, Long storeUuid, Integer num, EmpModel login);
}
