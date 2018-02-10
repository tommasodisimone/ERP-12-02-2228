package cn.itcast.invoice.util.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * this class extends ActionSupport and is the base for many classes
 *
 */
public class BaseAction extends ActionSupport{
	/**
	 * this public element is a public element
	 *
	 */
	public static final String LIST = "list";
	/**
	 * this public element is a public element
	 *
	 */
	public static final String TO_LIST = "toList";
	/**
	 * this public element is a public element
	 *
	 */
	public static final String INPUT = "input";
	
	//é¡µç �å€¼
	/**
	 * this public element is a public element
	 *
	 */
	public Integer pageNum = 1;
	//æ¯�é¡µæ˜¾ç¤ºæ•°æ�®æ€»é‡�
	/**
	 * this public element is a public element
	 *
	 */
	public Integer pageCount = 10;
	//æœ€å¤§é¡µç �å€¼
	/**
	 * this public element is a public element
	 *
	 */
	public Integer maxPageNum ;
	//æ•°æ�®æ€»é‡�
	/**
	 * this public element is a public element
	 *
	 */
	public Integer dataTotal;
	
	/**
	 * this public element is a public element
	 *
	 */ 
	private String actionName;
	
	/**
	 * this public element is a public element
	 *
	 */
	public String getActionName() {
		//æ ¹æ�®å½“å‰�æ‰§è¡Œçš„Actionç±»èŽ·å�–Actionç±»çš„å��ç§°ä¸­çš„å±€éƒ¨å­—ç¬¦ä¸²
		String actionName =getClass().getSimpleName();	//DepAction
		String subName = actionName.substring(0,actionName.length()-6);
		return subName.substring(0,1).toLowerCase()+subName.substring(1);
	} 
	/*
	public static void main(String[] args) {
		String s = "StoreDetailAction";
		String a = s.substring(0,s.length()-6);
		//å�–å‡ºç¬¬ä¸€ä¸ªå­—æ¯�
		String first = a.substring(0,1).toLowerCase();
		System.out.println(first+a.substring(1));
	}
	*/
	
	protected void setDataTotal(Integer dataTotal){
		this.dataTotal = dataTotal;
		maxPageNum = (dataTotal + pageCount -1) / pageCount; 
	}
	
	protected void put(String name,Object obj){
		ActionContext.getContext().put(name, obj);
	}
	
	protected void putSession(String name,Object obj){
		ActionContext.getContext().getSession().put(name, obj);
	}

	protected EmpModel getLogin(){
		return (EmpModel) ActionContext.getContext().getSession().get("loginEm");
	}
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
}
