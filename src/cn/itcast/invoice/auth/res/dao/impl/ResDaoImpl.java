package cn.itcast.invoice.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.invoice.auth.res.dao.dao.ResDao;
import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.auth.res.vo.ResQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class implements ResDao and extends BaseDaoImpl<ResModel>
 *
 */
public class ResDaoImpl extends BaseDaoImpl<ResModel> implements ResDao{
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		ResQueryModel rqm = (ResQueryModel) qm;
		//TODO Ã¦Â·Â»Ã¥Å Â Ã¨â€¡ÂªÃ¥Â®Å¡Ã¤Â¹â€°Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¨Â§â€žÃ¥Ë†â„¢
	}

	public List<String> getAllResByEmp(Long empUuid) {
		//from tbl_res where .....
		//from ResModel
		//Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¤Â¸Â¤Ã¤Â¸ÂªÃ¨Â¡Â¨Ã¯Â¼Å’Ã¨Â¦ï¿½Ã¦Â±â€šÃ¥â€¦Â³Ã¨ï¿½â€�Ã§Å¡â€žÃ¦â€¢Â°Ã¦ï¿½Â®Ã¦â€°ï¿½Ã¥â€¡ÂºÃ§Å½Â°Ã¯Â¼Å’Ã¤Â¸ï¿½Ã¥â€¦Â³Ã¨ï¿½â€�Ã§Å¡â€žÃ¦â€¢Â°Ã¦ï¿½Â®Ã¤Â¸ï¿½Ã¥â€¡ÂºÃ§Å½Â°(6Ã©â‚¬â€°1)
		//Ã¥â€ â€¦Ã¨Â¿Å¾Ã¯Â¼Å¡
		//Ã¥Â¤â€“Ã¨Â¿Å¾Ã¯Â¼Å¡
		
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
