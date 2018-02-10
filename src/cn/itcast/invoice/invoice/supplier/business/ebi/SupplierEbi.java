package cn.itcast.invoice.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this interface extends BaseEbi<SupplierModel>
 *
 */
@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {

	public List<SupplierModel> getAllUnion();
	/**
	 * èŽ·å�–å…·æœ‰å•†å“�çš„æ‰€æœ‰ä¾›åº”å•†ä¿¡æ�¯
	 * @return
	 */
	public List<SupplierModel> getAllUnionTwo();
}
