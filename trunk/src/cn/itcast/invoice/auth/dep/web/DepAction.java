package cn.itcast.invoice.auth.dep.web;

import java.util.List;

import cn.itcast.invoice.auth.dep.business.ebi.DepEbi;
import cn.itcast.invoice.auth.dep.vo.DepModel;
import cn.itcast.invoice.auth.dep.vo.DepQueryModel;
import cn.itcast.invoice.util.base.BaseAction;


/**
 * this interface extends BaseAction
 *
 */

public class DepAction extends BaseAction{

	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		//è®¡ç®—æœ€å¤§é¡µç �å€¼ : è®°å½•æ€»æ�¡ç›®æ•°ä¸Žæ¯�é¡µæ˜¾ç¤ºæ•°è®¡ç®—è€Œæ�¥
		//dataTotal = depEbi.getCount(dqm);
		//æœ€å¤§é¡µç �å€¼
		/*
		37  10 	4
		(37+10 -1) /10 	4
		39	10	4
		(39+10-1) /10  4
		40	10	4
		(40+10-1) /10  4
		41  10	5
		(41+10-1) /10  5
		*/
		//maxPageNum = (dataTotal + pageCount -1) / pageCount; 
		setDataTotal(depEbi.getCount(dqm));
		//èŽ·å�–æ‰€æœ‰çš„éƒ¨é—¨æ•°æ�®
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		//å°†è¯¥æ•°æ�®æ”¾å…¥æŒ‡å®šèŒƒå›´
		//ActionContext.getContext().put("depList", depList);
		put("depList",depList);
		//è·³è½¬é¡µé�¢
		//é¡µé�¢ä¸­ä»ŽæŒ‡å®šèŒƒå›´å†…èŽ·å�–æ•°æ�®å±•ç¤º
		return LIST;
	}
	
	/*
	//æŒ‰æ�¡ä»¶æŸ¥è¯¢
	public String queryList(){
		//æ ¹æ�®æŸ¥è¯¢æ�¡ä»¶è¿›è¡ŒæŸ¥è¯¢èŽ·å¾—æœ€ç»ˆæ˜¾ç¤ºçš„æ•°æ�®
		List<DepModel> depList = depEbi.getAll(dqm);
		//æ”¾å…¥æŒ‡å®šèŒƒå›´
		ActionContext.getContext().put("depList",depList);
		//è·³è½¬é¡µé�¢
		return "list";
	}
	*/
	
	//æ–°å»ºéƒ¨é—¨
	public String save(){
		//é€šè¿‡é¡µé�¢æ˜¯å�¦ä¼ é€’æœ‰å…·ä½“çš„uuidå€¼æ�¥åŒºåˆ†ç©¶ç«Ÿæ˜¯æ·»åŠ è¿˜æ˜¯ä¿®æ”¹
		if(dm.getUuid()== null){	//æ²¡æœ‰ä¼ é€’uuidå€¼ï¼Œæ·»åŠ 
			depEbi.save(dm);
		}else{						//ä¼ é€’äº†uuidå€¼ï¼Œä¿®æ”¹
			depEbi.update(dm);
		}
		return TO_LIST;
	}
	
	//è·³è½¬åˆ°ä¿®æ”¹é¡µé�¢
	public String input(){
		//å¦‚æžœä¼ é€’æœ‰uuidï¼Œæ‰§è¡ŒæŸ¥è¯¢
		if(dm.getUuid()!=null){
			//æ ¹æ�®ä¼ é€’çš„uuidèŽ·å�–å¯¹åº”çš„æ•°æ�®
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
	//åˆ é™¤éƒ¨é—¨ä¿¡æ�¯
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
	
}


/*
public class DepAction extends ActionSupport{
	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	public Integer pageNum = 1;
	public Integer pageCount = 2;
	public Integer maxPageNum ;
	public Integer dataTotal;
	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		//è®¡ç®—æœ€å¤§é¡µç �å€¼ : è®°å½•æ€»æ�¡ç›®æ•°ä¸Žæ¯�é¡µæ˜¾ç¤ºæ•°è®¡ç®—è€Œæ�¥
		dataTotal = depEbi.getCount(dqm);
		//æœ€å¤§é¡µç �å€¼
		37  10 	4
		(37+10 -1) /10 	4
		39	10	4
		(39+10-1) /10  4
		40	10	4
		(40+10-1) /10  4
		41  10	5
		(41+10-1) /10  5
		maxPageNum = (dataTotal + pageCount -1) / pageCount; 
		//èŽ·å�–æ‰€æœ‰çš„éƒ¨é—¨æ•°æ�®
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		//å°†è¯¥æ•°æ�®æ”¾å…¥æŒ‡å®šèŒƒå›´
		ActionContext.getContext().put("depList", depList);
		//è·³è½¬é¡µé�¢
		//é¡µé�¢ä¸­ä»ŽæŒ‡å®šèŒƒå›´å†…èŽ·å�–æ•°æ�®å±•ç¤º
		return "list";
	}
	
	//æŒ‰æ�¡ä»¶æŸ¥è¯¢
	public String queryList(){
		//æ ¹æ�®æŸ¥è¯¢æ�¡ä»¶è¿›è¡ŒæŸ¥è¯¢èŽ·å¾—æœ€ç»ˆæ˜¾ç¤ºçš„æ•°æ�®
		List<DepModel> depList = depEbi.getAll(dqm);
		//æ”¾å…¥æŒ‡å®šèŒƒå›´
		ActionContext.getContext().put("depList",depList);
		//è·³è½¬é¡µé�¢
		return "list";
	}
	
	//æ–°å»ºéƒ¨é—¨
	public String save(){
		//é€šè¿‡é¡µé�¢æ˜¯å�¦ä¼ é€’æœ‰å…·ä½“çš„uuidå€¼æ�¥åŒºåˆ†ç©¶ç«Ÿæ˜¯æ·»åŠ è¿˜æ˜¯ä¿®æ”¹
		if(dm.getUuid()== null){	//æ²¡æœ‰ä¼ é€’uuidå€¼ï¼Œæ·»åŠ 
			depEbi.save(dm);
		}else{						//ä¼ é€’äº†uuidå€¼ï¼Œä¿®æ”¹
			depEbi.update(dm);
		}
		return "toList";
	}
	
	//è·³è½¬åˆ°ä¿®æ”¹é¡µé�¢
	public String input(){
		//å¦‚æžœä¼ é€’æœ‰uuidï¼Œæ‰§è¡ŒæŸ¥è¯¢
		if(dm.getUuid()!=null){
			//æ ¹æ�®ä¼ é€’çš„uuidèŽ·å�–å¯¹åº”çš„æ•°æ�®
			dm = depEbi.get(dm.getUuid());
		}
		return "input";
	}
	
	//åˆ é™¤éƒ¨é—¨ä¿¡æ�¯
	public String delete(){
		depEbi.delete(dm);
		return "toList";
	}
	
}
*/