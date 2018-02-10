package cn.itcast.invoice.invoice.order.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this interface extends BaseEbi<OrderDetailModel>
 *
 */
@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel> {
}
