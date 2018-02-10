package cn.itcast.invoice.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.auth.role.business.ebi.RoleEbi;
import cn.itcast.invoice.auth.role.dao.dao.RoleDao;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class implements RoleEbi
 *
 */
public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void save(RoleModel rm) {
		roleDao.save(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public void update(RoleModel rm) {
		roleDao.update(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		//å°†å¯¹åº”çš„èµ„æº�å€¼è½¬æ�¢ä¸ºé›†å�ˆï¼Œè®¾ç½®åˆ°è§’è‰²å¯¹è±¡ä¸­
		//æ•°ç»„è½¬é›†å�ˆ
		Set<ResModel> reses = new HashSet<ResModel>();
		//æ•°ç»„ä¸­çš„æ•°æ�®ï¼Œè¿›å…¥åˆ°é›†å�ˆä¸­
		for(Long uuid:resUuids){
			//uuid->reses
			ResModel temp = new ResModel();
			temp.setSegreto(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setSegreto(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.save(rm);
	}

	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		//å°†å¯¹åº”çš„èµ„æº�å€¼è½¬æ�¢ä¸ºé›†å�ˆï¼Œè®¾ç½®åˆ°è§’è‰²å¯¹è±¡ä¸­
		//æ•°ç»„è½¬é›†å�ˆ
		Set<ResModel> reses = new HashSet<ResModel>();
		//æ•°ç»„ä¸­çš„æ•°æ�®ï¼Œè¿›å…¥åˆ°é›†å�ˆä¸­
		for(Long uuid:resUuids){
			//uuid->reses
			ResModel temp = new ResModel();
			temp.setSegreto(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setSegreto(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.update(rm);
	}

}
