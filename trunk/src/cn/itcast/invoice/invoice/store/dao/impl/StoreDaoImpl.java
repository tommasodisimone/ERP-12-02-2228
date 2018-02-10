package cn.itcast.invoice.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.invoice.invoice.store.dao.dao.StoreDao;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
import cn.itcast.invoice.invoice.store.vo.StoreQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class implements StoreDao and extends BaseDaoImpl<StoreModel>
 *
 */
public class StoreDaoImpl extends BaseDaoImpl<StoreModel> implements StoreDao{
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreQueryModel sqm = (StoreQueryModel) qm;
		//TODO 添加自定义查询规则
	}
}
