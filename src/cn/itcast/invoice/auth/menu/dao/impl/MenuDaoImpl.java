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
	public static long oneL = 1L;
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		MenuQueryModel mqm = (MenuQueryModel) qm;
		//Ã¦Â­Â¤Ã¥Â¤â€žÃ¨Â¿â€¡Ã¦Â»Â¤Ã¦Å½â€°Ã§Â³Â»Ã§Â»Å¸Ã¨ï¿½Å“Ã¥ï¿½â€¢
		dc.add(Restrictions.not(Restrictions.eq("uuid",oneL)));

		if(mqm.getName()!=null && mqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		if(mqm.getParent()!=null && mqm.getParent().getUuid()!=null && mqm.getParent().getUuid()!= -1L){
			dc.createAlias("parent", "p");
			dc.add(Restrictions.eq("p.uuid", mqm.getParent().getUuid()));
		}
		
	}
	//Ã¥Â¾â€¦Ã¥Â®Å¡
	public List<MenuModel> getByUuidAndPuuidIsOne() {
		String hql = "from MenuModel where uuid = ? or parent.uuid = ?";
		//TODO Ã¦Â­Â¤Ã¥Â¤â€žÃ¤Â½Â¿Ã§â€�Â¨Ã§Å¡â€žÃ¦ËœÂ¯Ã¥â€ºÂºÃ¥Â®Å¡Ã¥â‚¬Â¼Ã¯Â¼Ë†Ã¥Å¸ÂºÃ¤ÂºÅ½Ã©Å“â‚¬Ã¦Â±â€šÃ¥â€ Â³Ã¥Â®Å¡Ã§Å¡â€žÃ¯Â¼â€°
		return this.getHibernateTemplate().find(hql,oneL,oneL);
	}

	public List<MenuModel> getByPuuidIsOne() {
		String hql = "from MenuModel where parent.uuid = ?";
		//TODO Ã¦Â­Â¤Ã¥Â¤â€žÃ¤Â½Â¿Ã§â€�Â¨Ã§Å¡â€žÃ¦ËœÂ¯Ã¥â€ºÂºÃ¥Â®Å¡Ã¥â‚¬Â¼Ã¯Â¼Ë†Ã¥Å¸ÂºÃ¤ÂºÅ½Ã©Å“â‚¬Ã¦Â±â€šÃ¥â€ Â³Ã¥Â®Å¡Ã§Å¡â€žÃ¯Â¼â€°
		return this.getHibernateTemplate().find(hql,oneL);
	}
	
	public List<MenuModel> getByPuuid(Long puuid) {
		String hql = "from MenuModel where parent.uuid = ?";
		return this.getHibernateTemplate().find(hql,puuid);
	}
	
	public List<MenuModel> getParentByEmpUuid(Long empUuid) {
		//Ã¦ï¿½Â¡Ã¤Â»Â¶Ã¦ËœÂ¯Ã¤ÂºÂºÃ§Å¡â€žuuid
		//Ã¦Å¸Â¥Ã¨Â¯Â¢Ã§Å¡â€žÃ¦ËœÂ¯Ã¨ï¿½Å“Ã¥ï¿½â€¢
		//Menu->Role->Emp
		//from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ?
		//String hql = "select distinct mm from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ? and mm.parent.uuid = ?";
		//return this.getHibernateTemplate().find(hql,uuid,1L);
		return getMenusByPuuidAndEmp(oneL,empUuid);
	}

	public List<MenuModel> getMenusByPuuidAndEmp(Long puuid, Long empUuid) {
		//Ã§Ë†Â¶uuidÃ¦Å’â€¡Ã¥Â®Å¡Ã¯Â¼Å’Ã©Å“â‚¬Ã¨Â¦ï¿½Ã¤Â½Â¿Ã§â€�Â¨Ã§â€�Â¨Ã¦Ë†Â·Ã¨Â¿â€¡Ã¦Â»Â¤Ã¤Â¸â‚¬Ã¤Â¸â€¹
		//from MenuModel mm join mm.roles rm join rm.emps em where em.uuid = ? 
		String hql = "select distinct mm from MenuModel mm join mm.roles rm join rm.emps em where mm.parent.uuid = ? and em.uuid = ? order by mm.uuid";
		return this.getHibernateTemplate().find(hql,puuid,empUuid);
	}
}





