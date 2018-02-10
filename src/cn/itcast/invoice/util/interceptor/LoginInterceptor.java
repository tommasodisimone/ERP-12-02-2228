package cn.itcast.invoice.util.interceptor;

import cn.itcast.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//å¦‚æžœæ˜¯è·³è½¬åˆ°ç™»é™†é¡µçš„æ“�ä½œï¼Œæ”¾è¡Œ
		String an = invocation.getProxy().getActionName(); //pages_login
		if(an.equals("pages_login")){
			return invocation.invoke();
		}
		//å¦‚æžœæ˜¯ç™»é™†åŠŸèƒ½ï¼Œæ”¾è¡Œ
		//èŽ·å�–å½“å‰�æ‰§è¡Œçš„æ“�ä½œï¼Œå¦‚æžœæ˜¯ç™»é™†cn.itcast.invoice.auth.emp.web.EmpAction.loginæ”¾è¡Œ
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;

		if(totalName.equals("cn.itcast.invoice.auth.emp.web.EmpAction.login")){
			return invocation.invoke();
		}
		
		//åˆ¤æ–­ç”¨æˆ·æ˜¯å�¦ç™»é™†ï¼Œå¦‚æžœç™»é™†ï¼Œå�‘ä¸‹æ”¾è¡Œ
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get("loginEm");
		if(loginEm == null){
			//å¦‚æžœæœªç™»é™†ï¼Œè·³è½¬åˆ°ç™»é™†å¤±è´¥é¡µ
			return "loginFail"; 
		}else{
			//å¦‚æžœç™»é™†æˆ�åŠŸï¼Œæ”¾è¡Œè¯¥æ“�ä½œ
			return invocation.invoke();
		}
	}
}
