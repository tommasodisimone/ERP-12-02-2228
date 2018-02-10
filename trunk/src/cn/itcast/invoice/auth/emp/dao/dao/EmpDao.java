package cn.itcast.invoice.auth.emp.dao.dao;

import java.util.List;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.util.base.BaseDao;
/**
 * this interface extends BaseDao
 *
 */
public interface EmpDao extends BaseDao<EmpModel> {
	/**
	 * æ ¹æ�®ç”¨æˆ·å��å¯†ç �èŽ·å�–ç”¨æˆ·ä¿¡æ�¯
	 * @param userName ç”¨æˆ·å��
	 * @param pwd å¯†ç �
	 * @return ç™»é™†ç”¨æˆ·ä¿¡æ�¯æ¨¡åž‹
	 */
	public EmpModel getByNameAndPwd(String userName, String pwd);

	public boolean updatePwdByUserNameAndPwd(String userName, String oldPwd,
			String newPwd);

	public List<EmpModel> getAllByDepUuid(Long depUuid);
}
