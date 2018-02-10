package cn.itcast.invoice.auth.emp.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.util.base.BaseEbi;


/**
 * this interface extends BaseEbi
 *
 */
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel> {
	/**
	 * æ ¹æ�®ç”¨æˆ·å��ï¼Œå¯†ç �è¿›è¡Œç™»é™†
	 * @param userName ç”¨æˆ·å��
	 * @param pwd å¯†ç �
	 * @param lastLoginIp ç™»é™†IPåœ°å�€
	 * @return
	 */
	public EmpModel login(String userName, String pwd,String lastLoginIp);
	/**
	 * ä¿®æ”¹å¯†ç �
	 * @param userName ç”¨æˆ·å��
	 * @param oldPwd åŽŸå§‹å¯†ç �
	 * @param newPwd æ–°å¯†ç �
	 * @return
	 */
	public boolean changePwd(String userName, String oldPwd, String newPwd);
	public void save(EmpModel em, Long[] roleUuids);
	public void update(EmpModel em, Long[] roleUuids);
	/**
	 * æ ¹æ�®éƒ¨é—¨uuidèŽ·å�–éƒ¨é—¨å‘˜å·¥ä¿¡æ�¯
	 * @param depUuid éƒ¨é—¨uuid
	 * @return
	 */
	public List<EmpModel> getAllByDep(Long depUuid);
}
