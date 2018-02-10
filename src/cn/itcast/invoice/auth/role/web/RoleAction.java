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
	
	/**
	 * this public element is a public element
	 *
	 */
	public RoleModel rm = new RoleModel();
	/**
	 * this public element is a public element
	 *
	 */
	public RoleQueryModel rqm = new RoleQueryModel();

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	
	/**
	 * this public element is a public element
	 *
	 */
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm,pageNum,pageCount);
		put("roleList",roleList);
		return LIST;
	}
	//Ã¨Âµâ€žÃ¦Âºï¿½Ã§Å¡â€žuuidÃ¦â€¢Â°Ã§Â»â€ž
	/**
	 * this public element is a public element
	 *
	 */
	public Long[] resUuids;
	//Ã¨ï¿½Å“Ã¥ï¿½â€¢Ã§Å¡â€žuuidÃ¦â€¢Â°Ã§Â»â€ž
	/**
	 * this public element is a public element
	 *
	 */
	public Long[] menuUuids;
	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(rm.getUuid()== null){
			roleEbi.save(rm,resUuids,menuUuids);
		}else{
			roleEbi.update(rm,resUuids,menuUuids);
		}
		return TO_LIST;
	}
	
	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		//Ã¥Å Â Ã¨Â½Â½Ã¦â€°â‚¬Ã¦Å“â€°Ã§Å¡â€žÃ¨Âµâ€žÃ¦Âºï¿½Ã¦â€¢Â°Ã¦ï¿½Â®
		List<ResModel> resList = resEbi.getAll();
		put("resList",resList);
		
		//Ã¥Å Â Ã¨Â½Â½Ã¦â€°â‚¬Ã¦Å“â€°Ã§Å¡â€žÃ¨ï¿½Å“Ã¥ï¿½â€¢Ã¦â€¢Â°Ã¦ï¿½Â®
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList",menuList);
		
		if(rm.getUuid()!=null){
			rm = roleEbi.get(rm.getUuid());
			//Ã¥Â°â€ rmÃ¥Â¯Â¹Ã¨Â±Â¡Ã¤Â¸Â­Ã§Å¡â€žresesÃ¨Â½Â¬Ã¦ï¿½Â¢Ã¤Â¸ÂºÃ©Â¡ÂµÃ©ï¿½Â¢Ã¥ï¿½Â¯Ã¤Â»Â¥Ã¦Å½Â¥Ã¦â€�Â¶Ã§Å¡â€žÃ¦â€¢Â°Ã¦ï¿½Â®Ã¦Â Â¼Ã¥Â¼ï¿½ resUuidsÃ¦â€¢Â°Ã§Â»â€ž
			//Ã©â€ºâ€ Ã¥ï¿½Ë†Set->Long[]
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

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		roleEbi.delete(rm);
		return TO_LIST;
	}

}
