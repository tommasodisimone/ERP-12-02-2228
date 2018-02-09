package cn.itcast.invoice.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.invoice.auth.menu.business.ebi.MenuEbi;
import cn.itcast.invoice.auth.menu.dao.dao.MenuDao;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void delete(MenuModel mm) {
		//é¡µé�¢æ”¶é›†çš„æ•°æ�®ä¸­ä»…åŒ…å�«uuid,æ­¤å¤„åˆ é™¤ä¸šåŠ¡éœ€è¦�è¿›è¡Œçº§è�”ï¼Œå¿…é¡»å…ˆå°†å…³è�”å…³ç³»æ•°æ�®åŠ è½½ä¸Š
		//å› æ­¤åœ¨åˆ é™¤ä¹‹å‰�è¿›è¡Œä¸€æ¬¡æŸ¥è¯¢ï¼ŒåŠ è½½å…³è�”æ•°æ�®
		mm = menuDao.get(mm.getUuid());
		menuDao.delete(mm);
	}

	public void update(MenuModel mm) {
		menuDao.update(mm);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	public List<MenuModel> getParentMenu() {
		return menuDao.getByUuidAndPuuidIsOne();
	}

	public List<MenuModel> getParentMenu2() {
		return menuDao.getByPuuidIsOne();
	}

	public List<MenuModel> getMenusByPuuid(Long puuid) {
		return menuDao.getByPuuid(puuid);
	}

	public List<MenuModel> getParentMenuByEmp(Long uuid) {
		return menuDao.getParentByEmpUuid(uuid);
	}

	public List<MenuModel> getMenusByPuuidAndEmp(Long puuid, Long empUuid) {
		return menuDao.getMenusByPuuidAndEmp(puuid,empUuid);
	}

}
