package cn.itcast.invoice.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.List;

import cn.itcast.invoice.auth.menu.business.ebi.MenuEbi;
import cn.itcast.invoice.auth.menu.dao.dao.MenuDao;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.util.base.BaseQueryModel;
/**
 * this class implements MenuEbi
 *
 */
public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void save(MenuModel mm) {
		menuDao.save(mm);
	}

	public void delete(MenuModel mm) {
		//Ã©Â¡ÂµÃ©ï¿½Â¢Ã¦â€�Â¶Ã©â€ºâ€ Ã§Å¡â€žÃ¦â€¢Â°Ã¦ï¿½Â®Ã¤Â¸Â­Ã¤Â»â€¦Ã¥Å’â€¦Ã¥ï¿½Â«uuid,Ã¦Â­Â¤Ã¥Â¤â€žÃ¥Ë†Â Ã©â„¢Â¤Ã¤Â¸Å¡Ã¥Å Â¡Ã©Å“â‚¬Ã¨Â¦ï¿½Ã¨Â¿â€ºÃ¨Â¡Å’Ã§ÂºÂ§Ã¨ï¿½â€�Ã¯Â¼Å’Ã¥Â¿â€¦Ã©Â¡Â»Ã¥â€¦Ë†Ã¥Â°â€ Ã¥â€¦Â³Ã¨ï¿½â€�Ã¥â€¦Â³Ã§Â³Â»Ã¦â€¢Â°Ã¦ï¿½Â®Ã¥Å Â Ã¨Â½Â½Ã¤Â¸Å 
		//Ã¥â€ºÂ Ã¦Â­Â¤Ã¥Å“Â¨Ã¥Ë†Â Ã©â„¢Â¤Ã¤Â¹â€¹Ã¥â€°ï¿½Ã¨Â¿â€ºÃ¨Â¡Å’Ã¤Â¸â‚¬Ã¦Â¬Â¡Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¯Â¼Å’Ã¥Å Â Ã¨Â½Â½Ã¥â€¦Â³Ã¨ï¿½â€�Ã¦â€¢Â°Ã¦ï¿½Â®
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
