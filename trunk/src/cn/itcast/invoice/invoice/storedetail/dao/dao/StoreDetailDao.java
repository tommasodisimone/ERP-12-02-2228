package cn.itcast.invoice.invoice.storedetail.dao.dao;

import cn.itcast.invoice.invoice.storedetail.vo.StoreDetailModel;
import cn.itcast.invoice.util.base.BaseDao;
/**
 * this interface extends BaseDao<StoreDetailModel>
 *
 */
public interface StoreDetailDao extends BaseDao<StoreDetailModel> {
	/**
	 * æ ¹æ�®å•†å“�uuidä¸Žä»“åº“uuidèŽ·å�–å•†å“�åœ¨ä»“åº“ä¸­çš„å­˜å‚¨æ•°æ�®
	 * @param storeUuid
	 * @param goodsUuid
	 * @return
	 */
	public StoreDetailModel getBySmAndGm(Long storeUuid, Long goodsUuid);
}
