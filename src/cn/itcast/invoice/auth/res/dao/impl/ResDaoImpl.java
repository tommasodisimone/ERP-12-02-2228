package cn.itcast.invoice.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.invoice.auth.res.dao.dao.ResDao;
import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.auth.res.vo.ResQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;

public class ResDaoImpl extends BaseDaoImpl<ResModel> implements ResDao{
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		ResQueryModel rqm = (ResQueryModel) qm;
		//TODO æ·»åŠ è‡ªå®šä¹‰æŸ¥è¯¢è§„åˆ™
	}

	public List<String> getAllResByEmp(Long empUuid) {
		//from tbl_res where .....
		//from ResModel
		//æŸ¥è¯¢ä¸¤ä¸ªè¡¨ï¼Œè¦�æ±‚å…³è�”çš„æ•°æ�®æ‰�å‡ºçŽ°ï¼Œä¸�å…³è�”çš„æ•°æ�®ä¸�å‡ºçŽ°(6é€‰1)
		//å†…è¿žï¼š
		//å¤–è¿žï¼š
		
		//from ResModel res join res.roles  
		//from RoleModel role join role.emps
		//from EmpModel em join em.roles role join role.reses res
		//from ResModel res join res.roles role join role.emps emp
		String hql = " select distinct res.url from ResModel res join res.roles role join role.emps em where em.uuid = ?";
		return this.getHibernateTemplate().find(hql,empUuid);
	}
	
	public List<String> getUrls() {
		String hql = "select url from ResModel";
		return this.getHibernateTemplate().find(hql);
	}
}
