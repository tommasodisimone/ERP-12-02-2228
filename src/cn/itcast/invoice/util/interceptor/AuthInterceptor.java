package cn.itcast.invoice.util.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor{
	/*
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	*/
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get("loginEm");
		if(loginEm == null){
			return invocation.invoke();
		}
		
		//ç”±äºŽå…¨èµ„æº�æ•°æ�®æ¯�æ¬¡æ“�ä½œå�‡è¦�ä½¿ç”¨
		//å› æ­¤éœ€è¦�å°†å…¶æ��å‰�åˆ�å§‹åŒ–å®Œæ¯•ï¼Œå¹¶æ”¾ç½®åœ¨æŸ�ä¸ªå›ºå®šçš„ä½�ç½®ï¼Œå…±æ•´ä¸ªåº”ç”¨å…±äº«
		//æ€�è€ƒï¼šä»€ä¹ˆæ—¶å€™æ”¾ç½®è¯¥æ•°æ�®ï¼Ÿ
		//å�¯åŠ¨æœ�åŠ¡å™¨æ—¶ï¼ŒåŠ è½½è¯¥æ•°æ�®â€”â€”â€”â€”ç›‘å�¬å™¨
		List<String> resAllUrl = (List<String>) ServletActionContext.getServletContext().getAttribute("resAllUrl");
		if(resAllUrl.contains(totalName)){
			//ç”±äºŽç”¨æˆ·ç™»é™†å�Žï¼Œæ¯�æ¬¡æ“�ä½œæ‰€æœ‰çš„åŠŸèƒ½å�‡éœ€è¦�è¿›è¡Œæ�ƒé™�æ ¡éªŒï¼Œå¯¹äºŽå‘˜å·¥å…·æœ‰çš„å�¯æ“�ä½œèµ„æº�åº”è¯¥è¿›è¡Œä¼˜åŒ–
			//å�¦åˆ™å°†åœ¨æ¯�æ¬¡è°ƒç”¨æ—¶ï¼Œé‡�æ–°æŸ¥æ‰¾ï¼Œé€ æˆ�æ•´ä½“ç³»ç»Ÿæ€§èƒ½ä¸‹é™�
			//ServletContextèŒƒå›´å†…å�ªèƒ½æ”¾ç½®å…¨åº”ç”¨ç”¨æˆ·çš„å…¬å…±ä¿¡æ�¯
			//å¯¹äºŽå½“å‰�ç™»é™†å‘˜å·¥çš„ç§�æœ‰ä¿¡æ�¯ï¼Œå�ªèƒ½é€‰æ‹©æ›´ä½ŽèŒƒå›´çš„ä¿¡æ�¯å…±äº«(Session)
			//List<String> resList = resEbi.getAllResByEmp(loginEm.getUuid());
			
			//ä»Žsessionä¸­å�ŽåŽ»çš„æ•°æ�®å·²ç»�å…·æœ‰äº†å½“å‰�ç”¨æˆ·å�¯æ“�ä½œçš„å…¨èµ„æº�
			if(loginEm.getResValue().contains(totalName)){
				return invocation.invoke();
			}else{
				throw new AppException("å¯¹ä¸�èµ·ï¼�è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�æ‚¨ä¸�å…·æœ‰å½“å‰�æ“�ä½œçš„æ�ƒé™�ï¼�");
			}
		}else{
			return invocation.invoke();
		}
	}

	/*
	public String intercept(ActionInvocation invocation) throws Exception {
		//èŽ·å�–æœ¬æ¬¡è¯·æ±‚çš„æ“�ä½œå†…å®¹
		//æ€�è€ƒï¼šæ•°æ�®å­˜å‚¨æ–¹é�¢ï¼Œä»€ä¹ˆæ ¼å¼�æ��è¿°çš„è¯·æ±‚ï¼Ÿå…¨åŒ…å��.ç±»å��.æ–¹æ³•å��
		//èŽ·å�–æœ¬æ¬¡è¯·æ±‚çš„Actionç±»çš„ç±»å��+æ–¹æ³•å��
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;
		//èŽ·å�–ç™»é™†äººä¿¡æ�¯
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get("loginEm");
		if(loginEm == null){
			return invocation.invoke();
		}
		
		//æ–¹æ¡ˆä¸€:å»¶è¿ŸåŠ è½½
		//ä¸Žå½“å‰�ç™»é™†äººæ‰€èƒ½æ“�ä½œçš„èµ„æº�è¿›è¡Œæ¯”å¯¹
		//ç”±äºŽç™»é™†æ—¶ä½¿ç”¨çš„session(Hiberate)å¯¹è±¡å·²ç»�å…³é—­ï¼ŒopenSessionInViewåŠŸèƒ½æ— æ³•æ­£å¸¸å·¥ä½œ
		//æ‰€ä»¥æ­¤å¤„æ— æ³•ä½¿ç”¨empçš„å»¶è¿ŸåŠ è½½åŠŸèƒ½ï¼ŒåŠ è½½å…¶å…³è�”æ•°æ�®ï¼ŒæŠ›å‡ºå¼‚å¸¸
		Set<RoleModel> roleSet = emp.getRoles();
		for(RoleModel rm:roleSet){
			Set<ResModel> reses = rm.getReses();
			for(ResModel resm :reses){
				//å¦‚æžœæ¯”å¯¹æˆ�åŠŸ
				if(totalName.equals(resm.getUrl())){
					//æ”¾è¡Œ
					return invocation.invoke();
				}
			}
		}
		
		//å¦‚æžœæ¯”å¯¹å¤±è´¥
		//æ‹¦æˆªï¼šå¯¹ä¸�èµ·ï¼Œæ‚¨æ²¡æœ‰æ�ƒé™�ï¼�
		throw new AppException("å¯¹ä¸�èµ·ï¼�è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�æ‚¨ä¸�å…·æœ‰å½“å‰�æ“�ä½œçš„æ�ƒé™�ï¼�");
		
		
		
		
		
		
		//æ–¹æ¡ˆäºŒï¼šé‡�æ–°æŸ¥è¯¢(è§’è‰²ï¼Œå‘˜å·¥ï¼Œèµ„æº�)
		//åŒºåˆ†å½“å‰�æ“�ä½œæ˜¯å�¦éœ€è¦�æ‹¦æˆªï¼Œå¦‚æžœä¸�éœ€è¦�æ‹¦æˆªï¼Œæ”¾è¡Œ
		//éœ€è¦�æ‹¦æˆªçš„æœ‰å“ªäº›ï¼Ÿåœ¨èµ„æº�è¡¨ä¸­å‡ºçŽ°çš„æ‰�éœ€è¦�æ‹¦æˆªï¼Œå¦‚æžœæœªå‡ºçŽ°ï¼Œå�¯ä»¥éš�ä¾¿è°ƒç”¨
		List<String> resAllUrl = resEbi.getAllUrl();
		//æ¯”å¯¹
		if(resAllUrl.contains(totalName)){
			List<String> resList = resEbi.getAllResByEmp(loginEm.getUuid());
			//æ£€æµ‹ç”¨æˆ·è°ƒç”¨çš„èµ„æº�æ˜¯å�¦åœ¨å�¯æ“�ä½œèµ„æº�åˆ—è¡¨ä¸­
			if(resList.contains(totalName)){
				//æ”¾è¡Œ
				return invocation.invoke();
			}else{
				throw new AppException("å¯¹ä¸�èµ·ï¼�è¯·ä¸�è¦�è¿›è¡Œé�žæ³•æ“�ä½œï¼�æ‚¨ä¸�å…·æœ‰å½“å‰�æ“�ä½œçš„æ�ƒé™�ï¼�");
			}
		}else{
			//æ‰€æœ‰èµ„æº�è¿‡æ»¤
			return invocation.invoke();
		}
	}
	 */
}
