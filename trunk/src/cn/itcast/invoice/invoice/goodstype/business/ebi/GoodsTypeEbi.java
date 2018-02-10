package cn.itcast.invoice.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this class extends BaseEbi<GoodsTypeModel>
 *
 */
@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel> {
	/**
	 * èŽ·å�–æŒ‡å®šä¾›åº”å•†çš„äº§å“�ç±»åˆ«ä¿¡æ�¯
	 * @param supplierUuid ä¾›åº”å•†uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllBySupplier(Long supplierUuid);
	/**
	 * èŽ·å�–æŒ‡å®šä¾›åº”å•†å…·æœ‰å•†å“�ä¿¡æ�¯çš„å•†å“�ç±»åˆ«ä¿¡æ�¯é›†å�ˆ
	 * @param uuid	ä¾›åº”å•†uuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllUnionBySupplier(Long uuid);
}
