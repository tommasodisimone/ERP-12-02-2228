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
	/**
	 * this public element is a public element
	 *
	 */
	public GoodsTypeModel gm = new GoodsTypeModel();
	/**
	 * this public element is a public element
	 *
	 */
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	/**
	 * this public element is a public element
	 *
	 */
	private GoodsTypeEbi goodsTypeEbi;
	/**
	 * this public element is a public element
	 *
	 */
	private SupplierEbi supplierEbi;
	
	/**
	 * this public element is a public element
	 *
	 */
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList",goodsTypeList);
		return LIST;
	}

	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(gm.getUuid()== null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		//Ã¥Å Â Ã¨Â½Â½Ã¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã¤Â¿Â¡Ã¦ï¿½Â¯Ã¥Ë†â€”Ã¨Â¡Â¨
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		if(gm.getUuid()!=null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
	
	//--ajax---------------------------------------------
	/**
	 * this public element is a public element
	 *
	 */
	public Long supplierUuid;
	
	private List<GoodsTypeModel> gtmList;
	/**
	 * this public element is a public element
	 *
	 */
	public List<GoodsTypeModel> getGtmList(){
		return gtmList;
	}
	
	/**
	 * this public element is a public element
	 *
	 */
	public String ajaxGetGtmBySupplier(){
		//Ã¦Â Â¹Ã¦ï¿½Â®Ã¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã§Å¡â€žuuidÃ¨Å½Â·Ã¥ï¿½â€“Ã¥Â¯Â¹Ã¥Âºâ€�Ã§Å¡â€žÃ¦â€°â‚¬Ã¦Å“â€°Ã¥â€¢â€ Ã¥â€œï¿½Ã§Â±Â»Ã¥Ë†Â«Ã¤Â¿Â¡Ã¦ï¿½Â¯
		gtmList = goodsTypeEbi.getAllBySupplier(supplierUuid);
		//Ã¥Â°â€ Ã¦â€¢Â°Ã¦ï¿½Â®Ã¤Â¼Â Ã©â‚¬â€™Ã¥Ë†Â°Ã©Â¡ÂµÃ©ï¿½Â¢,jsonÃ¦Â Â¼Ã¥Â¼ï¿½
		//Ã¥Â¦â€šÃ¤Â½â€¢Ã¥Â°â€ Ã¦â€¢Â°Ã¦ï¿½Â®Ã¨Â½Â¬Ã¦ï¿½Â¢Ã¤Â¸ÂºjsonÃ¦Â Â¼Ã¥Â¼ï¿½Ã¯Â¼Å¸Ã¯Â¼Ë†Ã¤Â½Â¿Ã§â€�Â¨struts2-json-plugin-2.3.7.jarÃ¥Â®Å’Ã¦Ë†ï¿½)
		//Ã¥Â°â€ Ã¥Â¯Â¹Ã¥Âºâ€�ActionÃ§Â±Â»Ã¤Â¸Â­Ã¦â€°â‚¬Ã¦Å“â€°getÃ¥Â¼â‚¬Ã¥Â¤Â´Ã§Å¡â€žÃ¦â€“Â¹Ã¦Â³â€¢Ã¥Â¯Â¹Ã¥Âºâ€�Ã§Å¡â€žÃ¦â€¢Â°Ã¦ï¿½Â®Ã¯Â¼Å’Ã¨Â½Â¬Ã¦ï¿½Â¢Ã¤Â¸ÂºjsonÃ¦Â Â¼Ã¥Â¼ï¿½Ã¯Â¼Å’jsonÃ¥Â±Å¾Ã¦â‚¬Â§Ã¥ï¿½ï¿½Ã¤Â¸ÂºgetÃ¦â€“Â¹Ã¦Â³â€¢Ã§Å¡â€žÃ¥ï¿½ï¿½Ã§Â§Â°(Ã¤Â¸ï¿½Ã¥Å’â€¦Ã¥ï¿½Â«get)
		return "ajaxGetGtmBySupplier";
	}
	/*
	//{"abc":"heihei"}
	public String getAbc(){
		return "heihei";
	}
	*/
}
