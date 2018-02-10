package cn.itcast.invoice.invoice.storeoper.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.invoice.invoice.storeoper.dao.dao.StoreOperDao;
import cn.itcast.invoice.invoice.storeoper.vo.StoreOperModel;
import cn.itcast.invoice.invoice.storeoper.vo.StoreOperQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class implements StoreOperDao and extends BaseDaoImpl<StoreOperModel>
 *
 */
public class StoreOperDaoImpl extends BaseDaoImpl<StoreOperModel> implements StoreOperDao{
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreOperQueryModel sqm = (StoreOperQueryModel) qm;
		//TODO 添加自定义查询规则
	}
}
