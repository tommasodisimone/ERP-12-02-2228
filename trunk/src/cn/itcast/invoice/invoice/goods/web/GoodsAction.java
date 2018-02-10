package cn.itcast.invoice.invoice.goods.web;

import java.util.List;

import cn.itcast.invoice.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class GoodsAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public GoodsModel gm = new GoodsModel();
	/**
	 * this public element is a public element
	 *
	 */
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	
	/**
	 * this public element is a public element
	 *
	 */
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

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
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		//Ã¥Å Â Ã¨Â½Â½Ã¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã§Å¡â€žÃ¥â€¦Â¨Ã©Æ’Â¨Ã¦â€¢Â°Ã¦ï¿½Â®
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList",goodsList);
		return LIST;
	}

	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(gm.getUuid()== null){
			goodsEbi.save(gm);
		}else{
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		//Ã¥Å Â Ã¨Â½Â½Ã¦â€°â‚¬Ã¦Å“â€°Ã§Å¡â€žÃ¥â€¦Â·Ã¦Å“â€°Ã¥â€¢â€ Ã¥â€œï¿½Ã§Â±Â»Ã¥Ë†Â«Ã§Å¡â€žÃ¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã¤Â¿Â¡Ã¦ï¿½Â¯
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList",supplierList);
		//Ã¥Å Â Ã¨Â½Â½Ã§Â¬Â¬Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã¥Â¯Â¹Ã¥Âºâ€�Ã§Å¡â€žÃ¥â€¢â€ Ã¥â€œï¿½Ã§Â±Â»Ã¥Ë†Â«Ã¤Â¿Â¡Ã¦ï¿½Â¯
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySupplier(supplierList.get(0).getUuid());
		put("gtmList",gtmList);
		if(gm.getUuid()!=null){
			gm = goodsEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		goodsEbi.delete(gm);
		return TO_LIST;
	}

}
