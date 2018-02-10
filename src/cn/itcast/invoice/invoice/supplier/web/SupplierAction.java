package cn.itcast.invoice.invoice.supplier.web;

import java.util.List;

import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class SupplierAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public SupplierModel sm = new SupplierModel();
	/**
	 * this public element is a public element
	 *
	 */
	public SupplierQueryModel sqm = new SupplierQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String list(){
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList",supplierList);
		return LIST;
	}

	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹
	/**
	 * this public element is a public element
	 *
	 */
	public String save(){
		if(sm.getUuid()== null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢
	/**
	 * this public element is a public element
	 *
	 */
	public String input(){
		if(sm.getUuid()!=null){
			sm = supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//Ã¥Ë†Â Ã©â„¢Â¤
	/**
	 * this public element is a public element
	 *
	 */
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}

}
