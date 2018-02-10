package cn.itcast.invoice.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this class extends BaseEbi<GoodsModel>
 *
 */
@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {
	/**
	 * èŽ·å�–æŒ‡å®šå•†å“�ç±»åˆ«çš„æ‰€æœ‰å•†å“�ä¿¡æ�¯
	 * @param gtmUuid å•†å“�ç±»åˆ«uuid
	 * @return
	 */
	public List<GoodsModel> getAllByGtmUuid(Long gtmUuid);
}
