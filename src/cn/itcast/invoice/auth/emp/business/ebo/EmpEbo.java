package cn.itcast.invoice.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.invoice.auth.emp.business.ebi.EmpEbi;
import cn.itcast.invoice.auth.emp.dao.dao.EmpDao;
import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.auth.res.dao.dao.ResDao;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.util.base.BaseQueryModel;
import cn.itcast.invoice.util.format.MD5Utils;

public class EmpEbo implements EmpEbi{
	private EmpDao empDao;
	private ResDao resDao;
	
	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public void save(EmpModel em) {
		//ä¸ºæŸ�äº›æ•°æ�®è¿›è¡Œåˆ�å§‹åŒ–
		em.setPwd(MD5Utils.sha256(em.getPwd()));
		em.setLastLoginIp("--");
		em.setLastLoginTime(System.currentTimeMillis());
		//è®¾ç½®æ–°æ³¨å†Œç”¨æˆ·ç™»å½•æ¬¡æ•°ä¸º0
		em.setLoginTimes(0);
		empDao.save(em);
	}

	public void delete(EmpModel em) {
		empDao.delete(em);
	}

	public void update(EmpModel em) {
		//ç¼ºå°‘ä¸€äº›æ•°æ�®ï¼Œè¿™äº›æ•°æ�®ä¸�èƒ½ä»Žé¡µé�¢æ”¶é›†
		//å¿«ç…§æ›´æ–°
		EmpModel temp = empDao.get(em.getUuid());
		//å°†ç¼ºå°‘çš„å€¼å…¨éƒ¨èµ‹å€¼ä¸ŠåŽ»
		temp.setName(em.getPersonalInformation(0));
		temp.setEmail(em.getPersonalInformation(1));
		temp.setTele(em.getPersonalInformation(2));
		temp.setAddress(em.getPersonalInformation(3));
		temp.setBirthday(em.getBirthday());
		temp.setGender(em.getGender());
		temp.setDm(em.getDm());
		
		//hibernateä¸€çº§ç¼“å­˜ä¸­çš„å¯¹è±¡OIDä¸�å�¯ä¿®æ”¹ï¼Œå› æ­¤æŠ›å‡ºå¼‚å¸¸
		//temp.getDm().setUuid(em.getDm().getUuid());
		
		/*
		//ç”±äºŽä½¿ç”¨getæ–¹æ³•åŠ è½½çš„å¯¹è±¡æ—¶POå¯¹è±¡
		//æ­¤æ—¶ä¿®æ”¹çš„å¯¹è±¡å°†ä»ŽDO->PO
		//ä¸¤ä¸ªå¯¹è±¡å°†å…·æœ‰ç›¸å�Œçš„OIDï¼Œä½�äºŽå�Œä¸€ä¸€çº§ç¼“å­˜ç©ºé—´å†…ï¼Œå¼•å�‘IDé‡�å¤�å†²çª�
		//å°†ç¼ºå°‘çš„å€¼å…¨éƒ¨èµ‹å€¼ä¸ŠåŽ»
		em.setUserName(temp.getUserName());
		em.setPwd(temp.getPwd());
		em.setLastLoginIp(temp.getLastLoginIp());
		em.setLastLoginTime(temp.getLastLoginTime());
		em.setLoginTimes(temp.getLoginTimes());
		empDao.update(em);
		*/
	}

	public EmpModel get(Serializable uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public List<EmpModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return empDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return empDao.getCount(qm);
	}

	public EmpModel login(String userName, String pwd ,String lastLoginIp) {
		//å¯¹å¯†ç �è¿›è¡Œmd5åŠ å¯†
		pwd = MD5Utils.sha256(pwd);
		EmpModel loginEm = empDao.getByNameAndPwd(userName,pwd);
		//ä»»æ„�æ—¶åˆ»å�–å‡ºçš„æ•°æ�®ï¼Œç¬¬ä¸€ä»¶äº‹å¿…é¡»å�šnullåˆ¤å®š
		if(loginEm != null){
			//è®°å½•ç™»é™†çš„æ—¶é—´,ip,æ¬¡æ•°
			loginEm.setLastLoginIp(lastLoginIp);
			loginEm.setLastLoginTime(System.currentTimeMillis());
			//ä¿®æ”¹ç™»é™†æ¬¡æ•°
			loginEm.setLoginTimes(loginEm.getLoginTimes()+1);
			
			//ç™»é™†æ—¶ï¼Œå°†å½“å‰�ç”¨æˆ·çš„æ‰€æœ‰å�¯æ“�ä½œèµ„æº�è½¬æ�¢ä¸ºä¸€ä¸ªé•¿å­—ç¬¦ä¸²ï¼Œå¹¶è®¾ç½®åˆ°ç™»é™†å¯¹è±¡ä¸­
			List<String> resList = resDao.getAllResByEmp(loginEm.getUuid());
			StringBuilder sbf = new StringBuilder();
			for(String url:resList){
				sbf.append(url);
				sbf.append(" ");
			}
			loginEm.setResValue(sbf.toString());
		}
		/*
		else{
			throw new AppException("INFO_EMP_USERNAME_OR_PWD_ERROR");
		}
		*/
		return loginEm;
	}

	public boolean changePwd(String userName, String oldPwd, String newPwd) {
		oldPwd = MD5Utils.sha256(oldPwd);
		newPwd = MD5Utils.sha256(newPwd);
		return empDao.updatePwdByUserNameAndPwd(userName,oldPwd,newPwd);
	}

	public void save(EmpModel em, Long[] roleUuids) {
		//ä¸ºæŸ�äº›æ•°æ�®è¿›è¡Œåˆ�å§‹åŒ–
		em.setPwd(MD5Utils.sha256(em.getPwd()));
		em.setLastLoginIp("--");
		em.setLastLoginTime(System.currentTimeMillis());
		//è®¾ç½®æ–°æ³¨å†Œç”¨æˆ·ç™»å½•æ¬¡æ•°ä¸º0
		em.setLoginTimes(0);
		
		//å»ºç«‹ä¸Žè§’è‰²çš„å…³ç³»
		//æ•°ç»„->é›†å�ˆ
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		em.setRoles(roles);
		empDao.save(em);
	}

	public void update(EmpModel em, Long[] roleUuids) {
		//ç¼ºå°‘ä¸€äº›æ•°æ�®ï¼Œè¿™äº›æ•°æ�®ä¸�èƒ½ä»Žé¡µé�¢æ”¶é›†
		//å¿«ç…§æ›´æ–°
		EmpModel temp = empDao.get(em.getUuid());
		//å°†ç¼ºå°‘çš„å€¼å…¨éƒ¨èµ‹å€¼ä¸ŠåŽ»
		temp.setName(em.getPersonalInformation(0));
		temp.setEmail(em.getPersonalInformation(1));
		temp.setTele(em.getPersonalInformation(2));
		temp.setAddress(em.getPersonalInformation(3));
		temp.setBirthday(em.getBirthday());
		temp.setGender(em.getGender());
		temp.setDm(em.getDm());
		
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		temp.setRoles(roles);
	}

	public List<EmpModel> getAllByDep(Long depUuid) {
		return empDao.getAllByDepUuid(depUuid);
	}
	
}
