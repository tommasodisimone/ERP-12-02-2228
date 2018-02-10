package cn.itcast.invoice.auth.emp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.invoice.auth.dep.business.ebi.DepEbi;
import cn.itcast.invoice.auth.dep.vo.DepModel;
import cn.itcast.invoice.auth.emp.business.ebi.EmpEbi;
import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.auth.emp.vo.EmpQueryModel;
import cn.itcast.invoice.auth.role.business.ebi.RoleEbi;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class EmpAction extends BaseAction{
	//1.getUm() ç”¨
	//2.å°�è¯•æ€§çš„ä½¿ç”¨publicæ�ƒé™�è®¿é—® ç”¨
	//3.setUm() æœ‰
	//3.1 åœ¨æ¯�æ¬¡è°ƒç”¨å¯¹è±¡æ—¶ï¼Œæ‰§è¡Œsetæ–¹æ³•ï¼Œåˆ›å»ºä¸€ä¸ªæ–°å¯¹è±¡
	/*
	localhost:8080/ERP/emp_fn.action?um.uuid=1&um.name=2&um.age=3
	um.uuid
	um.get?  um.public? new UserModel() setUm(new...)  setUuid(1);
	um.get?	 um.public? new UserModel() setUm(new...)  setName(2);
	um.get?	 um.public? new UserModel() setUm(new...)  setAge(3);
	age name uuid 
	*/
	/*
	private UserModel um = new UserModel();
	
	public void setUm(UserModel um) {
		System.out.println("set.........................");
		this.um = um;
	}

	public String fn(){
		System.out.println(um.getUuid());
		System.out.println(um.getName());
		System.out.println(um.getAge());
		return "fn";
	}
	*/
	
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();

	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		//åŠ è½½éƒ¨é—¨å…¨ä¿¡æ�¯
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
		put("empList",empList);
		return LIST;
	}

	public Long[] roleUuids;
	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(em.getUuid()== null){
			empEbi.save(em,roleUuids);
		}else{
			empEbi.update(em,roleUuids);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		//å°†éƒ¨é—¨åˆ—è¡¨æ•°æ�®
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		//åŠ è½½è§’è‰²æ•°æ�®
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		if(em.getUuid()!=null){
			em = empEbi.get(em.getUuid());
			//é›†å�ˆ->æ•°ç»„
			roleUuids = new Long[em.getRoles().size()];
			int i = 0;
			for(RoleModel rm:em.getRoles()){
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		empEbi.delete(em);
		return TO_LIST;
	}

	//ç™»é™†ï¼šä½¿ç”¨ç”¨æˆ·ä¼ é€’çš„ç”¨æˆ·å��å¯†ç �è¿›è¡Œç™»é™†
	public String login(){
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		//ä½¿ç”¨ç”¨æˆ·å��å¯†ç �åˆ°æ•°æ�®åº“è¿›è¡Œæ ¡éªŒæŸ¥è¯¢
		EmpModel loginEm = empEbi.login(em.getUserName(),em.getPwd(),loginIp);
		//åˆ¤æ–­æ˜¯å�¦ç™»é™†æˆ�åŠŸ
		if(loginEm == null){
			//å¦‚æžœåŒ¹é…�å¤±è´¥
			//è·³è½¬åˆ°ç™»é™†é¡µ
			return "loginFail";
		}else{
			//å¦‚æžœåŒ¹é…�æˆ�åŠŸ
			//å°†ç™»é™†çš„ä¿¡æ�¯æ”¾å…¥Session
			putSession("loginEm", loginEm);
			//è·³è½¬åˆ°ä¸»é¡µ
			return "loginSuccess";
		}
	}
	
	//ç™»å‡º/æ³¨é”€
	public String logout(){
		//å°†sessionä¸­çš„æ•°æ�®æ¸…é™¤
		putSession("loginEm", null);
		//è·³è½¬åˆ°ç™»é™†é¡µé�¢
		return "loginFail";
	}
	
	//ä¿®æ”¹å¯†ç �
	public String changePwd(){
		//old: em.pwd
		//new: request.getParameter();
		String oldPwd = em.getPwd();
		String newPwd = getRequest().getParameter("newPwd");
		boolean flag = empEbi.changePwd(getLogin().getUserName(),oldPwd,newPwd);
		if(flag){
			//ä¿®æ”¹æˆ�åŠŸ
			return logout();
		}else{
			//ä¿®æ”¹å¤±è´¥
			return "hehe";
		}
	}
}







