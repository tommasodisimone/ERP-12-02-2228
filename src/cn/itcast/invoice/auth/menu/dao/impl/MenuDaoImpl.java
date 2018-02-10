package cn.itcast.invoice.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.invoice.auth.menu.dao.dao.MenuDao;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.auth.menu.vo.MenuQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class extends BaseDaoImpl<MenuModel> and implements MenuDao
 *
 */
public class MenuDaoImpl extends BaseDaoImpl<MenuModel> implements MenuDao{
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		MenuQueryModel mqm = (MenuQueryModel) qm;
		//æ­¤å¤„è¿‡æ»¤æŽ‰ç³»ç»Ÿè�œå�•
		dc.add(Restrictions.not(Restrictions.eq("uuid",1L)));

		if(mqm.getName()!=null && mqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		if(mqm.getParent()!=null && mqm.getParent().getUuid()!=null && mqm.getParent().getUuid()!= -1L){
			dc.createAlias("parent", "p");
			dc.add(Restrictions.eq("p.uuid", mqm.getParent().getUuid()));
		}
		
	}
	//å¾…å®š
	public List<MenuModel> getByUuidAndPuuidIsOne() {
		String hql = "from MenuModel where uuid = ? or parent.uuid = ?";
		//TODO æ­¤å¤„ä½¿ç”¨çš„æ˜¯å›ºå®šå€¼ï¼ˆåŸºäºŽéœ€æ±‚å†³å®šçš„ï¼‰
		return this.getHibernateTemplate().find(hql,1L,1L);
	}

	public List<MenuModel> getByPuuidIsOne() {
		String hql = "from MenuModel where parent.uuid = ?";
		//TODO æ­¤å¤„ä½¿ç”¨çš„æ˜¯å›ºå®šå€¼ï¼ˆåŸºäºŽéœ€æ±‚å†³å®šçš„ï¼‰
		return this.getHibernateTemplate().find(hql,1L);
	}
	
	public List<MenuModel> getByPuuid(Long puuid) {
		String hql = "from MenuModel where parent.uuid = ?";
		return this.getHibernateTemplate().find(hql,puuid);
	}
	
	public List<MenuModel> getParentByEmpUuid(Long empUuid) {
		//æ�¡ä»¶æ˜¯äººçš„uuid
		//æŸ¥è¯¢çš„æ˜¯è�œå�•
		//Menu->Role->Emp
		//from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ?
		//String hql = "select distinct mm from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ? and mm.parent.uuid = ?";
		//return this.getHibernateTemplate().find(hql,uuid,1L);
		return getMenusByPuuidAndEmp(1L,empUuid);
	}

	public List<MenuModel> getMenusByPuuidAndEmp(Long puuid, Long empUuid) {
		//çˆ¶uuidæŒ‡å®šï¼Œéœ€è¦�ä½¿ç”¨ç”¨æˆ·è¿‡æ»¤ä¸€ä¸‹
		//from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ? 
		String hql = "select distinct mm from MenuModel mm join mm.roles rm join rm.emps em where mm.parent.uuid = ? and em.uuid = ? order by mm.uuid";
		return this.getHibernateTemplate().find(hql,puuid,empUuid);
	}
}





