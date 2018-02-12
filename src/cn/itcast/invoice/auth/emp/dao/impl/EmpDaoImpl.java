package cn.itcast.invoice.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.invoice.auth.emp.dao.dao.EmpDao;
import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.auth.emp.vo.EmpQueryModel;
import cn.itcast.invoice.util.base.BaseDaoImpl;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this Class extends BaseDaoImpl and implements EmpDao
 *
 */
public class EmpDaoImpl extends BaseDaoImpl<EmpModel> implements EmpDao{
	public static int two = 2, zero = 0;
	public static long eightysix = 86400000L;
	public void addUserName(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getUserName()!=null && eqm.getUserName().trim().length()>0){
			dc.add(Restrictions.like("userName", "%"+eqm.getUserName().trim()+"%"));
		}
	}
	
	public void addName(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getPersonalInformation(0)!=null && eqm.getPersonalInformation(0).trim().length()>0){
			dc.add(Restrictions.like("name", "%"+eqm.getPersonalInformation(0).trim()+"%"));
		}
	}
	
	public void addTele(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getPersonalInformation(two)!=null && eqm.getPersonalInformation(two).trim().length()>zero){
			dc.add(Restrictions.like("tele", "%"+eqm.getPersonalInformation(two).trim()+"%"));
		}
	}
	
	public void addGender(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getGender()!=null && eqm.getGender()!=-1){
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		}
	}
	
	public void addEmail(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getPersonalInformation(1)!=null && eqm.getPersonalInformation(1).trim().length()>0){
			dc.add(Restrictions.like("email", "%"+eqm.getPersonalInformation(1).trim()+"%"));
		}
	}
	
	public void addLastLoginTime(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getLastLoginTime()!=null){
			dc.add(Restrictions.ge("lastLoginTime", eqm.getLastLoginTime()));
		}
	}
	
	public void addLastLoginTime2(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getLastLoginTime2()!=null){
			dc.add(Restrictions.le("lastLoginTime", eqm.getLastLoginTime2()+eightysix));
		}
	}
	
	public void addDm(DetachedCriteria dc,EmpQueryModel eqm) {
		if(eqm.getDm()!=null && eqm.getDm().getUuid()!=null && eqm.getDm().getUuid()!=-1){
			dc.createAlias("dm", "d");
			dc.add(Restrictions.eq("d.uuid", eqm.getDm().getUuid()));
		}
	}	
	
	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		EmpQueryModel eqm = (EmpQueryModel) qm;
		
		addUserName(dc,eqm);
		addName(dc,eqm);
		addTele(dc,eqm);
		addEmail(dc,eqm);
		addLastLoginTime(dc,eqm);
		addLastLoginTime2(dc,eqm);
		addDm(dc,eqm);
	
	}
	
	
	
	
	public EmpModel getByNameAndPwd(String userName, String pwd) {
		String hql = "from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> temp = this.getHibernateTemplate().find(hql,userName,pwd);
		return temp.size()>0 ? temp.get(0):null;
	}

	public boolean updatePwdByUserNameAndPwd(String userName, String oldPwd, String newPwd) {
		//æ‰§è¡Œupdateæ“�ä½œ 	update
		String hql = "update EmpModel set pwd = ? where userName = ? and pwd = ?";
		return this.getHibernateTemplate().bulkUpdate(hql,newPwd,userName,oldPwd)>0; 
	}

	public List<EmpModel> getAllByDepUuid(Long depUuid) {
		String hql = "from EmpModel where dm.uuid = ?";
		return this.getHibernateTemplate().find(hql,depUuid);
	}
}
