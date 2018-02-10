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
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		//åŠ è½½ä¾›åº”å•†çš„å…¨éƒ¨æ•°æ�®
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList",goodsList);
		return LIST;
	}

	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(gm.getUuid()== null){
			goodsEbi.save(gm);
		}else{
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		//åŠ è½½æ‰€æœ‰çš„å…·æœ‰å•†å“�ç±»åˆ«çš„ä¾›åº”å•†ä¿¡æ�¯
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList",supplierList);
		//åŠ è½½ç¬¬ä¸€ä¸ªä¾›åº”å•†å¯¹åº”çš„å•†å“�ç±»åˆ«ä¿¡æ�¯
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySupplier(supplierList.get(0).getUuid());
		put("gtmList",gtmList);
		if(gm.getUuid()!=null){
			gm = goodsEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		goodsEbi.delete(gm);
		return TO_LIST;
	}

}
