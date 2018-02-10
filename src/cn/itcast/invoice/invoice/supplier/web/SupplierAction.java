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
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	//è·³è½¬åˆ°åˆ—è¡¨é¡µé�¢
	public String list(){
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList",supplierList);
		return LIST;
	}

	//ä¿�å­˜/ä¿®æ”¹
	public String save(){
		if(sm.getUuid()== null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	//è·³è½¬åˆ°æ·»åŠ /ä¿®æ”¹é¡µé�¢
	public String input(){
		if(sm.getUuid()!=null){
			sm = supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//åˆ é™¤
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}

}
