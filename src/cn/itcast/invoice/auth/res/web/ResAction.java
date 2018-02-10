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
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;
	private RoleEbi roleEbi;
	
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
		put("resList",resList);
		return LIST;
	}
	
	public Long[] roleUuids;
	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(rm.getUuid()== null){
			resEbi.save(rm,roleUuids);
		}else{
			resEbi.update(rm,roleUuids);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		if(rm.getUuid()!=null){
			rm = resEbi.get(rm.getUuid());
			//å°†é›†å�ˆè½¬æ�¢ä¸ºæ•°ç»„
			roleUuids = new Long[rm.getRoles().size()];
			int i = 0;
			for(RoleModel temp : rm.getRoles()){
				roleUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		resEbi.delete(rm);
		return TO_LIST;
	}

}
