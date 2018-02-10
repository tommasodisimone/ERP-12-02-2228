package cn.itcast.invoice.util.interceptor;

import cn.itcast.invoice.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * this class extends AbstractInterceptor
 *
 */
public class ExceptionInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			//Ã¤Â½Â¿Ã§â€�Â¨Ã¤Â¸â€¹Ã¥Ë†â€”Ã¥Â¯Â¹Ã¨Â±Â¡Ã¨Å½Â·Ã¥ï¿½â€“actionsupportÃ¥Â¸Â¸Ã§â€�Â¨Ã¦â€œï¿½Ã¤Â½Å“
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(as.getText(e.getMessage()));
			//Ã§Â»â„¢Ã¥Â¼â‚¬Ã¥ï¿½â€˜Ã¤ÂºÂºÃ¥â€˜ËœÃ¥ï¿½â€˜Ã¤Â¸â‚¬Ã¤Â»Â½email
			//Ã¨Â®Â°Ã¥Â½â€¢Ã¦â€”Â¥Ã¥Â¿â€”e.printStackTrace();
			//Ã¨Å½Â·Ã¥ï¿½â€“ActionÃ§Å¡â€žÃ¨Â°Æ’Ã§â€�Â¨Ã§Å½Â¯Ã¥Â¢Æ’
			return "error";
		} catch(Exception e){
			 System.out.println("Something was wrong");
			return "error";
		}
	}
}
