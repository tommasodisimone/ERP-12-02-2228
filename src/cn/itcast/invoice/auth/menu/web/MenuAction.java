package cn.itcast.invoice.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cn.itcast.invoice.auth.menu.business.ebi.MenuEbi;
import cn.itcast.invoice.auth.menu.vo.MenuModel;
import cn.itcast.invoice.auth.menu.vo.MenuQueryModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseActions
 *
 */
public class MenuAction extends BaseAction{
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		//åŠ è½½æ‰€æœ‰çš„çˆ¶è�œå�•æ•°æ�®
		List<MenuModel> parentList = menuEbi.getParentMenu();
		put("parentList",parentList);
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
		put("menuList",menuList);
		return LIST;
	}

	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(mm.getUuid()== null){
			menuEbi.save(mm);
		}else{
			menuEbi.update(mm);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		//åŠ è½½æ‰€æœ‰çš„çˆ¶è�œå�•
		List<MenuModel> menuList = menuEbi.getParentMenu();
		put("menuList",menuList);
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		//è¿›è¡Œåˆ é™¤æ—¶ï¼Œå¦‚æžœå­˜åœ¨æœ‰ä¸€å¯¹å¤šå…³ç³»
		//åˆ é™¤ä¸€æ—¶ï¼Œé¦–å…ˆå°†å¤šçš„å¤–é”®è®¾ç½®ä¸ºnull
		//ç„¶å�Žåˆ é™¤å¤šæ–¹å¯¹è±¡
		//ç„¶å�Žåˆ é™¤ä¸€æ–¹å¯¹è±¡
		
		menuEbi.delete(mm);
		return TO_LIST;
	}

	//----------------
	public String showMenu() throws IOException{
		//æ ¹æ�®phpçš„å†…å®¹ï¼ŒæŒ‰ç…§åŽŸå§‹æ ¼å¼�è¿”å›žphpçš„æ•°æ�®
		//å­¦ä¹ source.phpå†…å®¹ï¼Œç¿»è¯‘æˆ�actionå†…å®¹
		//å°†phpä»£ç �è§£æž�å�Žå¾—åˆ°ç»“è®º
		//è¯·æ±‚å�‚æ•°rootçš„å€¼å¦‚æžœæ˜¯sourceè¿”å›žä¸€ç§�jsonæ•°æ�®æ•°ç»„
		//çˆ¶è�œå�•
		//{"text":"aaa","expanded":false,"classes":"è‡ªå®šä¹‰çš„æ ·å¼�class","id":"ä¸ºæ­¢ç¼–å�·","hasChildren":true}
		//æœ€å�Žä¸€çº§è�œå�•
		//{"text":"aaa","classes":"è‡ªå®šä¹‰çš„æ ·å¼�class","id":"æœªçŸ¥ç¼–å�·","hasChildren":false}
		//å�¦åˆ™è¿”å›žå�¦ä¸€ç§�jsonæ•°æ�®æ•°ç»„
		
		/*
		//æµ‹è¯•ä¸€:
		//æ•°æ�®çš„è¿”å›žéœ€è¦�ä½¿ç”¨response
		PrintWriter pw = getResponse().getWriter();
		
		pw.write("[");
		pw.write("{\"text\":\"aaa\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"1\"},");
		pw.write("{\"text\":\"bbb\",\"hasChildren\":false,\"classes\":\"file\",\"id\":\"2\"},");
		pw.write("{\"text\":\"ccc\",\"expanded\":false,\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"3\"}");
		pw.write("]");
		
		pw.flush();
		return null;
		*/
		
		/*
		//æµ‹è¯•äºŒ:
		//é¡µé�¢ç»“æž„å·²ç»�æµ‹è¯•å®Œæ¯•ï¼Œéœ€è¦�èŽ·å�–çœŸå®žæ•°æ�®è¿›è¡Œæµ‹è¯•
		//èŽ·å�–æ‰€æœ‰çš„çˆ¶è�œå�•æ•°æ�®ï¼ˆä¸�åŒ…å�«ç³»ç»Ÿè�œå�•ï¼‰
		List<MenuModel> menuList = menuEbi.getParentMenu2();
		PrintWriter pw = getResponse().getWriter();
		
		StringBuilder jsonStr = new StringBuilder();
		
		jsonStr.append("[");
		for(MenuModel temp:menuList){
			jsonStr.append("{\"text\":\"");
			jsonStr.append(temp.getName());
			jsonStr.append("\",\"expanded\":false,\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
			jsonStr.append(temp.getUuid().toString());
			jsonStr.append("\"},");
		}
		//jsonStr.substring(0, jsonStr.length()-1);
		jsonStr.deleteCharAt(jsonStr.length()-1);
		jsonStr.append("]");
		
		pw.write(jsonStr.toString());
		
		pw.flush();
		return null;
		*/
		
		/*
		//æµ‹è¯•ä¸‰:
		//å½“ç³»ç»Ÿç¬¬ä¸€æ¬¡åŠ è½½è�œå�•æ—¶ï¼Œè¯·æ±‚ä¸­åŒ…å�«æœ‰root=sourceå�‚æ•°
		//å½“ç‚¹å‡»å­�è�œå�•é¡¹æ—¶ï¼ŒåŠ è½½çš„è¯·æ±‚ä¸­åŒ…å�«æœ‰root=id(uuid)
		String root = getRequest().getParameter("root");
		
		getResponse().setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = getResponse().getWriter();
		
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("[");
		
		List<MenuModel> menuList = null;
		
		if(root.equals("source")){
			menuList = menuEbi.getParentMenu2();
			for(MenuModel temp:menuList){
				jsonStr.append("{\"text\":\"");
				jsonStr.append(temp.getName());
				jsonStr.append("\",\"expanded\":false,\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				jsonStr.append(temp.getUuid().toString());
				jsonStr.append("\"},");
			}
		}else{
			//æ ¹æ�®ä¼ é€’çš„idå€¼ï¼ŒèŽ·å�–å¯¹åº”çš„å­�è�œå�•,å±•ç¤º
			//æ ¹æ�®ä¼ é€’çš„è�œå�•é¡¹ç›®çš„uuidèŽ·å�–å…¶å­�é¡¹çš„è�œå�•é›†å�ˆ
			Long puuid = new Long(root); 
			menuList = menuEbi.getMenusByPuuid(puuid);
			//jsonStr.append("{\"text\":\"bbb\",\"hasChildren\":false,\"classes\":\"file\",\"id\":\"2\"},");
			for(MenuModel temp:menuList){
				jsonStr.append("{\"text\":\"");
				jsonStr.append("<a target='main' href='");
				jsonStr.append(temp.getUrl());
				jsonStr.append("'>");
				jsonStr.append(temp.getName());
				jsonStr.append("</a>");
				jsonStr.append("\",\"classes\":\"file\"},");
			}
		}
		
		jsonStr.deleteCharAt(jsonStr.length()-1);
		jsonStr.append("]");
		
		pw.write(jsonStr.toString());
		
		pw.flush();
		return null;
		*/
		
		//æ–¹æ¡ˆå››ï¼šåŸºäºŽç™»é™†ç”¨æˆ·è¿›è¡Œè�œå�•åŠ è½½
		
		String root = getRequest().getParameter("root");
		
		getResponse().setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = getResponse().getWriter();
		
		StringBuilder jsonStr = new StringBuilder();
		jsonStr.append("[");
		
		List<MenuModel> menuList = null;
		
		if(root.equals("source")){
			//æ ¹æ�®ç™»é™†äººèŽ·å�–å¯¹åº”çš„è�œå�•é¡¹
			menuList = menuEbi.getParentMenuByEmp(getLogin().getUuid());
			for(MenuModel temp:menuList){
				jsonStr.append("{\"text\":\"");
				jsonStr.append(temp.getName());
				jsonStr.append("\",\"expanded\":false,\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				jsonStr.append(temp.getUuid().toString());
				jsonStr.append("\"},");
			}
		}else{
			//æ ¹æ�®ä¼ é€’çš„è�œå�•é¡¹ç›®çš„uuidå’Œç™»é™†äººä¿¡æ�¯èŽ·å�–å…¶å­�é¡¹çš„è�œå�•é›†å�ˆ
			Long puuid = new Long(root); 
			menuList = menuEbi.getMenusByPuuidAndEmp(puuid,getLogin().getUuid());
			//jsonStr.append("{\"text\":\"bbb\",\"hasChildren\":false,\"classes\":\"file\",\"id\":\"2\"},");
			for(MenuModel temp:menuList){
				jsonStr.append("{\"text\":\"");
				jsonStr.append("<a target='main' href='");
				jsonStr.append(temp.getUrl());
				jsonStr.append("'>");
				jsonStr.append(temp.getName());
				jsonStr.append("</a>");
				jsonStr.append("\",\"classes\":\"file\"},");
			}
		}
		
		jsonStr.deleteCharAt(jsonStr.length()-1);
		jsonStr.append("]");
		
		pw.write(jsonStr.toString());
		
		pw.flush();
		return null;
	}
	
}
/*
[
 	{"text":"bb","expanded":false,"hasChildren":true,"classes":"folder","id":"3"},
 	{"text":"cc","expanded":false,"hasChildren":true,"classes":"folder","id":"4"},
 	{"text":"dd","expanded":false,"hasChildren":true,"classes":"folder","id":"8"}
 	,
 ]
*/		
		
		
		
		
		
		
		
		
		
		