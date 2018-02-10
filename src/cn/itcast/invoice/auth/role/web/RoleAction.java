package cn.itcast.invoice.auth.role.web;

import java.util.List;

import cn.itcast.invoice.auth.menu.business.ebi.MenuEbi;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.auth.res.business.ebi.ResEbi;
import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.auth.role.business.ebi.RoleEbi;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.auth.role.vo.RoleQueryModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class RoleAction extends BaseAction{
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm,pageNum,pageCount);
		put("roleList",roleList);
		return LIST;
	}
	//èµ„æº�çš„uuidæ•°ç»„
	public Long[] resUuids;
	//è�œå�•çš„uuidæ•°ç»„
	public Long[] menuUuids;
	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(rm.getUuid()== null){
			roleEbi.save(rm,resUuids,menuUuids);
		}else{
			roleEbi.update(rm,resUuids,menuUuids);
		}
		return TO_LIST;
	}
	
	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		//åŠ è½½æ‰€æœ‰çš„èµ„æº�æ•°æ�®
		List<ResModel> resList = resEbi.getAll();
		put("resList",resList);
		
		//åŠ è½½æ‰€æœ‰çš„è�œå�•æ•°æ�®
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList",menuList);
		
		if(rm.getUuid()!=null){
			rm = roleEbi.get(rm.getUuid());
			//å°†rmå¯¹è±¡ä¸­çš„resesè½¬æ�¢ä¸ºé¡µé�¢å�¯ä»¥æŽ¥æ”¶çš„æ•°æ�®æ ¼å¼� resUuidsæ•°ç»„
			//é›†å�ˆSet->Long[]
			resUuids = new Long[rm.getReses().size()];
			int i = 0;
			for(ResModel temp: rm.getReses()){
				resUuids[i++] = temp.getUuid();
			}
			
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for(MenuModel temp: rm.getMenus()){
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		roleEbi.delete(rm);
		return TO_LIST;
	}

}
