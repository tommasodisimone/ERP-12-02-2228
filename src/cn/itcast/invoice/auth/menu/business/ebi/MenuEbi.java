package cn.itcast.invoice.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.util.base.BaseEbi;
/**
 * this interface extends BaseEbi<MenuModel>
 *
 */
@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {
	/**
	 * èŽ·å�–æ‰€æœ‰çˆ¶è�œå�•
	 * @return
	 */
	public List<MenuModel> getParentMenu();
	/**
	 * èŽ·å�–æ‰€æœ‰çˆ¶äº²è�œå�•ï¼Œä¸�åŒ…å�«ç³»ç»Ÿè�œå�•
	 * @return
	 */
	public List<MenuModel> getParentMenu2();
	/**
	 * èŽ·å�–æŒ‡å®šidä¸‹çš„æ‰€æœ‰å­�è�œå�•é¡¹
	 * @param puuid æŒ‡å®šè�œå�•uuid
	 * @return
	 */
	public List<MenuModel> getMenusByPuuid(Long puuid);
	/**
	 * æ ¹æ�®ç™»é™†äººèŽ·å�–è¯¾æ“�ä½œè�œå�•
	 * @param uuid ç™»é™†äººuuid
	 * @return
	 */
	public List<MenuModel> getParentMenuByEmp(Long uuid);
	
	public List<MenuModel> getMenusByPuuidAndEmp(Long puuid, Long uuid);
}
