package cn.itcast.invoice.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.res.vo.ResModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this class extends BaseEbi<ResModel>
 *
 */
@Transactional
public interface ResEbi extends BaseEbi<ResModel> {

	public void save(ResModel rm, Long[] roleUuids);

	public void update(ResModel rm, Long[] roleUuids);
	/**
	 * èŽ·å�–æŒ‡å®šå‘˜å·¥çš„æ‰€æœ‰å�¯æ“�ä½œèµ„æº�ä¿¡æ�¯
	 * @param uuid å‘˜å·¥uuid
	 * @return
	 */
	public List<String> getAllResByEmp(Long uuid);

	public List<String> getAllUrl();
}
