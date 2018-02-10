package cn.itcast.invoice.util.interceptor;

import cn.itcast.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * this class extends AbstractInterceptor
 *
 */
public class LoginInterceptor extends AbstractInterceptor {
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//Ã¥Â¦â€šÃ¦Å¾Å“Ã¦ËœÂ¯Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã§â„¢Â»Ã©â„¢â€ Ã©Â¡ÂµÃ§Å¡â€žÃ¦â€œï¿½Ã¤Â½Å“Ã¯Â¼Å’Ã¦â€�Â¾Ã¨Â¡Å’
		String an = invocation.getProxy().getActionName(); //pages_login
		if(an.equals("pages_login")){
			return invocation.invoke();
		}
		//Ã¥Â¦â€šÃ¦Å¾Å“Ã¦ËœÂ¯Ã§â„¢Â»Ã©â„¢â€ Ã¥Å Å¸Ã¨Æ’Â½Ã¯Â¼Å’Ã¦â€�Â¾Ã¨Â¡Å’
		//Ã¨Å½Â·Ã¥ï¿½â€“Ã¥Â½â€œÃ¥â€°ï¿½Ã¦â€°Â§Ã¨Â¡Å’Ã§Å¡â€žÃ¦â€œï¿½Ã¤Â½Å“Ã¯Â¼Å’Ã¥Â¦â€šÃ¦Å¾Å“Ã¦ËœÂ¯Ã§â„¢Â»Ã©â„¢â€ cn.itcast.invoice.auth.emp.web.EmpAction.loginÃ¦â€�Â¾Ã¨Â¡Å’
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;

		if(totalName.equals("cn.itcast.invoice.auth.emp.web.EmpAction.login")){
			return invocation.invoke();
		}
		
		//Ã¥Ë†Â¤Ã¦â€“Â­Ã§â€�Â¨Ã¦Ë†Â·Ã¦ËœÂ¯Ã¥ï¿½Â¦Ã§â„¢Â»Ã©â„¢â€ Ã¯Â¼Å’Ã¥Â¦â€šÃ¦Å¾Å“Ã§â„¢Â»Ã©â„¢â€ Ã¯Â¼Å’Ã¥ï¿½â€˜Ã¤Â¸â€¹Ã¦â€�Â¾Ã¨Â¡Å’
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get("loginEm");
		if(loginEm == null){
			//Ã¥Â¦â€šÃ¦Å¾Å“Ã¦Å“ÂªÃ§â„¢Â»Ã©â„¢â€ Ã¯Â¼Å’Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã§â„¢Â»Ã©â„¢â€ Ã¥Â¤Â±Ã¨Â´Â¥Ã©Â¡Âµ
			return "loginFail"; 
		}else{
			//Ã¥Â¦â€šÃ¦Å¾Å“Ã§â„¢Â»Ã©â„¢â€ Ã¦Ë†ï¿½Ã¥Å Å¸Ã¯Â¼Å’Ã¦â€�Â¾Ã¨Â¡Å’Ã¨Â¯Â¥Ã¦â€œï¿½Ã¤Â½Å“
			return invocation.invoke();
		}
	}
}
