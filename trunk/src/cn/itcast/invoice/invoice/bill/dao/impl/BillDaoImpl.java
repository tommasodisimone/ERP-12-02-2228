package cn.itcast.invoice.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.invoice.invoice.bill.dao.dao.BillDao;
import cn.itcast.invoice.invoice.bill.vo.BillQueryModel;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
/**
 * this class implements BollDao and extends HibernateDaoSupport
 *
 */
public class BillDaoImpl extends HibernateDaoSupport implements BillDao{

	private static List<Object[]> billByGoods;

	public List<Object[]> getBillByGoods(BillQueryModel bqm) {
		/*
		select 
			g.uuid,		
			g.name,			
			sum(od.num)
		from 
			tbl_orderdetail od,
			tbl_goods g
		where
			od.goodsUuid = g.uuid
		group by
			od.goodsUuid 
		*/
		//QBC
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		//è®¾ç½®æŸ¥è¯¢çš„å†…å®¹ gm,sum()
		//select gm , sum(num)
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.groupProperty("gm"));
		plist.add(Projections.sum("num"));
		/*
		select gm,sum(num)
		from...
		group by gm
		*/
		dc.setProjection(plist);
		//è®¾ç½®æŸ¥è¯¢çš„æ�¡ä»¶
		doQbc(dc, bqm);
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	private void doQbc(DetachedCriteria dc,BillQueryModel bqm){
		dc.createAlias("om", "o");
		if(bqm.getType()!=null && bqm.getType()!=-1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid()!=-1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
		if(bqm.getBegin()!=null){
			dc.add(Restrictions.ge("o.createTime", bqm.getBegin()));
		}
		if(bqm.getEnd()!=null){
			dc.add(Restrictions.le("o.createTime", bqm.getEnd()));
		}
		if(bqm.getGoodsUuid()!=null){
			dc.createAlias("gm", "g");
			dc.add(Restrictions.eq("g.uuid", bqm.getGoodsUuid()));
		}
	}
	public List<OrderDetailModel> getBillDetailByGoods(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		//è®¾ç½®æŸ¥è¯¢çš„æ�¡ä»¶
		doQbc(dc, bqm);
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	
}
