package cn.itcast.invoice.auth.res.web;

import java.util.List;

import cn.itcast.invoice.auth.res.business.ebi.ResEbi;
import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.auth.res.vo.ResQueryModel;
import cn.itcast.invoice.auth.role.business.ebi.RoleEbi;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class ResAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public ResModel rm = new ResModel();
	/**
	 * this public element is a public element
	 *
	 */
	public ResQueryModel rqm = new ResQueryModel();
	/**
	 * this public element is a public element
	 *
	 */
	
	private ResEbi resEbi;
	/**
	 * this public element is a public element
	 *
	 */
	private RoleEbi roleEbi;
	
	/**
	 * this public element is a public element
	 *
	 */
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
		put("resList",resList);
		return LIST;
	}
	
	/**
	 * this public element is a public element
	 *
	 */
	public Long[] roleUuids;
	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(rm.getUuid()== null){
			resEbi.save(rm,roleUuids);
		}else{
			resEbi.update(rm,roleUuids);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		if(rm.getUuid()!=null){
			rm = resEbi.get(rm.getUuid());
			//Ã¥Â°â€ Ã©â€ºâ€ Ã¥ï¿½Ë†Ã¨Â½Â¬Ã¦ï¿½Â¢Ã¤Â¸ÂºÃ¦â€¢Â°Ã§Â»â€ž
			roleUuids = new Long[rm.getRoles().size()];
			int i = 0;
			for(RoleModel temp : rm.getRoles()){
				roleUuids[i++] = temp.getUuid();
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
		resEbi.delete(rm);
		return TO_LIST;
	}

}
