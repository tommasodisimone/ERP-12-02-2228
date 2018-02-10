package cn.itcast.invoice.invoice.goodstype.web;

import java.util.List;

import cn.itcast.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class GoodsTypeAction extends BaseAction{
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;
	
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList",goodsTypeList);
		return LIST;
	}

	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(gm.getUuid()== null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		//åŠ è½½ä¾›åº”å•†ä¿¡æ�¯åˆ—è¡¨
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		if(gm.getUuid()!=null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
	
	//--ajax---------------------------------------------
	public Long supplierUuid;
	
	private List<GoodsTypeModel> gtmList;
	public List<GoodsTypeModel> getGtmList(){
		return gtmList;
	}
	
	public String ajaxGetGtmBySupplier(){
		//æ ¹æ�®ä¾›åº”å•†çš„uuidèŽ·å�–å¯¹åº”çš„æ‰€æœ‰å•†å“�ç±»åˆ«ä¿¡æ�¯
		gtmList = goodsTypeEbi.getAllBySupplier(supplierUuid);
		//å°†æ•°æ�®ä¼ é€’åˆ°é¡µé�¢,jsonæ ¼å¼�
		//å¦‚ä½•å°†æ•°æ�®è½¬æ�¢ä¸ºjsonæ ¼å¼�ï¼Ÿï¼ˆä½¿ç”¨struts2-json-plugin-2.3.7.jarå®Œæˆ�)
		//å°†å¯¹åº”Actionç±»ä¸­æ‰€æœ‰getå¼€å¤´çš„æ–¹æ³•å¯¹åº”çš„æ•°æ�®ï¼Œè½¬æ�¢ä¸ºjsonæ ¼å¼�ï¼Œjsonå±žæ€§å��ä¸ºgetæ–¹æ³•çš„å��ç§°(ä¸�åŒ…å�«get)
		return "ajaxGetGtmBySupplier";
	}
	/*
	//{"abc":"heihei"}
	public String getAbc(){
		return "heihei";
	}
	*/
}
