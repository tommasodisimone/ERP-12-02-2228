package cn.itcast.invoice.invoice.order.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.invoice.auth.emp.business.ebi.EmpEbi;
import cn.itcast.invoice.auth.emp.vo.EmpModel;
import cn.itcast.invoice.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.invoice.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.invoice.invoice.order.business.ebi.OrderDetailEbi;
import cn.itcast.invoice.invoice.order.business.ebi.OrderEbi;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.order.vo.OrderModel;
import cn.itcast.invoice.invoice.order.vo.OrderQueryModel;
import cn.itcast.invoice.invoice.store.business.ebi.StoreEbi;
import cn.itcast.invoice.invoice.store.vo.StoreModel;
import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseAction;
/**
 * this class extends BaseAction
 *
 */
public class OrderAction extends BaseAction{
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	private OrderDetailEbi orderDetailEbi;
	
	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}

	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	//ÃƒÂ¨Ã‚Â·Ã‚Â³ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã‹â€ Ã‚Â°ÃƒÂ¥Ã‹â€ Ã¢â‚¬â€�ÃƒÂ¨Ã‚Â¡Ã‚Â¨ÃƒÂ©Ã‚Â¡Ã‚ÂµÃƒÂ©Ã¯Â¿Â½Ã‚Â¢
	public String list(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return LIST;
	}

	//ÃƒÂ¤Ã‚Â¿Ã¯Â¿Â½ÃƒÂ¥Ã‚Â­Ã‹Å“/ÃƒÂ¤Ã‚Â¿Ã‚Â®ÃƒÂ¦Ã¢â‚¬ï¿½Ã‚Â¹
	public String save(){
		if(om.getUuid()== null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//ÃƒÂ¨Ã‚Â·Ã‚Â³ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã‹â€ Ã‚Â°ÃƒÂ¦Ã‚Â·Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â /ÃƒÂ¤Ã‚Â¿Ã‚Â®ÃƒÂ¦Ã¢â‚¬ï¿½Ã‚Â¹ÃƒÂ©Ã‚Â¡Ã‚ÂµÃƒÂ©Ã¯Â¿Â½Ã‚Â¢
	public String input(){
		if(om.getUuid()!=null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}
	//ÃƒÂ¨Ã‚Â·Ã‚Â³ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã‹â€ Ã‚Â°ÃƒÂ©Ã¢â‚¬Â¡Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â´Ã‚Â­ÃƒÂ¨Ã‚Â®Ã‚Â¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬Â¢ÃƒÂ©Ã‚Â¡Ã‚Âµ
	
	public String buyInput(){
		List<SupplierModel> supplierList = supplierEbi.getAll();
		//ÃƒÂ¥Ã¯Â¿Â½Ã‹Å“ÃƒÂ¦Ã‹â€ Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â»Ã‚Â¤ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		//ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â½Ã¢â‚¬Â ÃƒÂ¦Ã‹Å“Ã‚Â¯ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â»Ã‚Â¤ÃƒÂ¦Ã…Â½Ã¢â‚¬Â°
		//ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â»Ã‚Â¤ÃƒÂ¦Ã…Â½Ã¢â‚¬Â°
		//ÃƒÂ¥Ã‚Â¾Ã‚ÂªÃƒÂ§Ã…Â½Ã‚Â¯ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ¥Ã‹â€ Ã‚Â¤ÃƒÂ¥Ã‚Â®Ã…Â¡ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¦Ã‹Å“Ã‚Â¯ÃƒÂ¥Ã¯Â¿Â½Ã‚Â¦ÃƒÂ¤Ã‚Â¿Ã¯Â¿Â½ÃƒÂ§Ã¢â‚¬Â¢Ã¢â€žÂ¢ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ¥Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¦Ã…Â¾Ã…â€œÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¿Ã¯Â¿Â½ÃƒÂ§Ã¢â‚¬Â¢Ã¢â€žÂ¢ÃƒÂ¥Ã‹â€ Ã‚Â ÃƒÂ©Ã¢â€žÂ¢Ã‚Â¤
		/*
		for(int i = supplierList.size()-1;i>=0;i--){
			SupplierModel sm = supplierList.get(i);
			List<GoodsTypeModel> gtms = new ArrayList(sm.getGtms());
			//ÃƒÂ¥Ã‚Â¾Ã‚ÂªÃƒÂ§Ã…Â½Ã‚Â¯ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
			for(int j = gtms.size()-1;j>=0;j--){
				GoodsTypeModel gtm = gtms.get(j);
				if(gtm.getGms().size() == 0){
					gtms.remove(j);
				}
			}
			if(gtms.size() == 0){
				supplierList.remove(i);
			}
		}
		*/
		int flag=0;
		for(int i = supplierList.size()-1;i>=0;i--){
			flag=0;
			SupplierModel sm = supplierList.get(i);
			//List<GoodsTypeModel> gtms = new ArrayList(sm.getGtms());
			//ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â§ÃƒÂ¨Ã†â€™Ã‚Â½ÃƒÂ¤Ã‚Â¼Ã‹Å“ÃƒÂ¤Ã‚ÂºÃ…Â½ÃƒÂ¤Ã‚Â¸Ã…Â ÃƒÂ©Ã¯Â¿Â½Ã‚Â¢ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â¹ÃƒÂ¦Ã‚Â¡Ã‹â€ 
			List<GoodsTypeModel> gtms = goodsTypeEbi.getAllBySupplier(sm.getUuid());
			//ÃƒÂ¥Ã‚Â¾Ã‚ÂªÃƒÂ§Ã…Â½Ã‚Â¯ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
			for(int j = gtms.size()-1;j>=0;j--){
				GoodsTypeModel gtm = gtms.get(j);
				if(gtm.getGms().size() > 0){
					flag=1;
					continue;
				}
			}
			if(flag==1) {
				continue;
		    }else supplierList.remove(i);
		}

		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnionBySupplier(supplierList.get(0).getUuid());
		List<GoodsModel> gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		put("supplierList",supplierList);
		put("gtmList",gtmList);
		put("gmList",gmList);
		return "buyInput";
	}
	
	/*
	public static void main(String[] args) {
		List<String> al = new ArrayList<String>();
		
		al.add("aa1");
		al.add("bb1");
		al.add("cc1");
		al.add("dd");
		
		for(int i = al.size()-1;i>=0;i--){
			String s = al.get(i);
			if(s.contains("1")){
				al.remove(i);
			}
		}
		
		for(String s:al){
			System.out.println(s);
		}
	}
	*/
	
	
	/*
	public String buyInput(){
		//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		//ÃƒÂ©Ã¢â‚¬â€�Ã‚Â®ÃƒÂ©Ã‚Â¢Ã‹Å“ÃƒÂ¯Ã‚Â¼Ã…Â¡ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ¨Ã‚Â¢Ã‚Â«ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½
		//ÃƒÂ¨Ã‚Â§Ã‚Â£ÃƒÂ¥Ã¢â‚¬Â Ã‚Â³ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â¹ÃƒÂ¦Ã‚Â¡Ã‹â€ ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¯Ã‚Â¼Ã…Â¡
		//ÃƒÂ¨Ã‚Â¯Ã‚Â»ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¥Ã¯Â¿Â½Ã…Â½ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ©Ã¢â€šÂ¬Ã…Â¡ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â¿Ã‚Â­ÃƒÂ¤Ã‚Â»Ã‚Â£ÃƒÂ©Ã¢â‚¬ÂºÃ¢â‚¬Â ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã‹â€ Ã‚Â ÃƒÂ©Ã¢â€žÂ¢Ã‚Â¤ÃƒÂ¦Ã…Â½Ã¢â‚¬Â°
		//ÃƒÂ¨Ã‚Â§Ã‚Â£ÃƒÂ¥Ã¢â‚¬Â Ã‚Â³ÃƒÂ¦Ã¢â‚¬â€œÃ‚Â¹ÃƒÂ¦Ã‚Â¡Ã‹â€ ÃƒÂ¤Ã‚ÂºÃ…â€™ÃƒÂ¯Ã‚Â¼Ã…Â¡
		//ÃƒÂ¦Ã…Â¸Ã‚Â¥ÃƒÂ¨Ã‚Â¯Ã‚Â¢ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¦Ã¢â‚¬â€�Ã‚Â¶ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ§Ã¢â‚¬ÂºÃ‚Â´ÃƒÂ¦Ã…Â½Ã‚Â¥ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â»Ã‚Â¤ÃƒÂ¦Ã…Â½Ã¢â‚¬Â°
		//ÃƒÂ¦Ã…Â¸Ã‚Â¥ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â   ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â³ÃƒÂ¨Ã¯Â¿Â½Ã¢â‚¬ï¿½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«
		//ÃƒÂ©Ã¢â‚¬â€�Ã‚Â®ÃƒÂ©Ã‚Â¢Ã‹Å“ÃƒÂ¥Ã‹â€ Ã¢â‚¬Â ÃƒÂ¦Ã…Â¾Ã¯Â¿Â½ÃƒÂ¯Ã‚Â¼Ã…Â¡ÃƒÂ¦Ã…Â¸Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â·ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã‚Â¤Ã…Â¡ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ¤Ã‚Â½Ã¢â‚¬Â ÃƒÂ¦Ã‹Å“Ã‚Â¯ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¯Ã‚Â¼Ã…Â¸
		//ÃƒÂ¦Ã…Â¸Ã‚Â¥ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â   ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â³ÃƒÂ¨Ã¯Â¿Â½Ã¢â‚¬ï¿½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â³ÃƒÂ¨Ã¯Â¿Â½Ã¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ distinct
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		/*
		1ÃƒÂ¥Ã¯Â¿Â½Ã‚Â·		A	B	C
				a1	b1	
				a2	b2
				a3
		*/
	/*
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnionBySupplier(supplierList.get(0).getUuid());
		//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		List<GoodsModel> gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â»Ã‚Â·ÃƒÂ¦Ã‚Â Ã‚Â¼ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®(ÃƒÂ§Ã…â€œÃ¯Â¿Â½ÃƒÂ§Ã¢â‚¬Â¢Ã‚Â¥)
		//GoodsModel gm = gmList.get(0);
		//put("gm",gm);
		/*
		put("supplierList",supplierList);
		put("gtmList",gtmList);
		put("gmList",gmList);
		
		return "buyInput";
	}*/

	//ÃƒÂ¥Ã‹â€ Ã‚Â ÃƒÂ©Ã¢â€žÂ¢Ã‚Â¤
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}
	
	//--ajax----------------------------
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String used;
	
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;
	
	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}

	public String ajaxGetGtmAndGm(){
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾uuidÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â¸Ã…Â½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		//ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ¥Ã‚Â¿Ã¢â‚¬Â¦ÃƒÂ©Ã‚Â¡Ã‚Â»ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½
		gtmList = goodsTypeEbi.getAllUnionBySupplier(supplierUuid);
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½
		gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		//ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¿Ã‚Â¡ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¯
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	//ÃƒÂ©Ã…â€œÃ¢â€šÂ¬ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â»Ã‚Â¤ÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
	public String ajaxGetGtmAndGm2(){
		//ÃƒÂ¨Ã‚Â§Ã‚Â£ÃƒÂ¦Ã…Â¾Ã¯Â¿Â½ÃƒÂ¥Ã¢â‚¬Â¡Ã‚ÂºÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾uuid
		String[] uuidsArr = used.split(",");
		//ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ uuidÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¢ÃƒÂ¤Ã‚Â¸Ã‚ÂºÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ§Ã‚Â»Ã¢â‚¬Å¾/ÃƒÂ©Ã¢â‚¬ÂºÃ¢â‚¬Â ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ 
		Set<Long> uuids = new HashSet<Long>();
		for(String uuidStr:uuidsArr){
			uuids.add(new Long(uuidStr));
		}
		
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â¾Ã¢â‚¬ÂºÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾uuidÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¤Ã‚Â¸Ã…Â½ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		//ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ¥Ã‚Â¿Ã¢â‚¬Â¦ÃƒÂ©Ã‚Â¡Ã‚Â»ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½
		gtmList = goodsTypeEbi.getAllUnionBySupplier(supplierUuid);
		//1.ÃƒÂ¥Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¦Ã…Â¾Ã…â€œÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ©Ã†â€™Ã‚Â½ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¥Ã‹â€ Ã‚Â ÃƒÂ©Ã¢â€žÂ¢Ã‚Â¤
		//1.xÃƒÂ¥Ã‚Â¦Ã¢â‚¬Å¡ÃƒÂ¦Ã…Â¾Ã…â€œÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¸Ã‚Â­ÃƒÂ¦Ã…Â¸Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¤Ã‚Â¿Ã¯Â¿Â½ÃƒÂ§Ã¢â‚¬Â¢Ã¢â€žÂ¢
		int flag=0;
		for(int i = gtmList.size()-1;i>=0;i--){
			flag = 0;
			GoodsTypeModel gtm = gtmList.get(i);
			//Ã¦Â Â¹Ã¦ï¿½Â®Ã¥â€¢â€ Ã¥â€œï¿½Ã§Â±Â»Ã¥Ë†Â«Ã¨Å½Â·Ã¥ï¿½â€“Ã¥â€¢â€ Ã¥â€œï¿½
			gmList = goodsEbi.getAllByGtmUuid(gtm.getUuid());
			for(GoodsModel temp:gmList){
				if(!uuids.contains(temp.getUuid())){
					flag = 1;
					continue;
				}
			}
			//Ã¨Â¯Â¥Ã§Â±Â»Ã¥Ë†Â«Ã¤Â¸Â­Ã¦â€°â‚¬Ã¦Å“â€°Ã¥â€¢â€ Ã¥â€œï¿½Ã¥â€¦Â¨Ã©Æ’Â¨Ã¤Â½Â¿Ã§â€�Â¨Ã¨Â¿â€¡
			if(flag ==1){
				continue;
			} else gtmList.remove(i);
		}

		
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ§Ã‚Â±Ã‚Â»ÃƒÂ¥Ã‹â€ Ã‚Â«ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½
		gmList = goodsEbi.getAllByGtmUuid(gtmList.get(0).getUuid());
		//ÃƒÂ¥Ã‹â€ Ã‚Â ÃƒÂ©Ã¢â€žÂ¢Ã‚Â¤ÃƒÂ¦Ã…Â½Ã¢â‚¬Â°ÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½
		for(int i = gmList.size()-1;i>=0;i--){
			GoodsModel gm = gmList.get(i);
			if(uuids.contains(gm.getUuid())){
				//ÃƒÂ¨Ã‚Â¯Ã‚Â¥ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¤Ã‚Â½Ã‚Â¿ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡
				gmList.remove(i);
			}
		}
		
		//ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ§Ã‚Â¬Ã‚Â¬ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¿Ã‚Â¡ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¯
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	public String ajaxGetGm(){
		gmList = goodsEbi.getAllByGtmUuid(goodsTypeUuid);
		gm = gmList.get(0);
		return "ajaxGetGm";
	}
	
	public String ajaxGetOne(){
		gm = goodsEbi.get(goodsUuid);
		return "ajaxGetOne";
	}
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	
	//ÃƒÂ§Ã¢â‚¬ï¿½Ã…Â¸ÃƒÂ¦Ã‹â€ Ã¯Â¿Â½ÃƒÂ©Ã¢â‚¬Â¡Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â´Ã‚Â­ÃƒÂ¨Ã‚Â®Ã‚Â¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬Â¢
	public String buyOrder(){
		//ÃƒÂ¦Ã¢â‚¬ï¿½Ã‚Â¶ÃƒÂ©Ã¢â‚¬ÂºÃ¢â‚¬Â ÃƒÂ©Ã‚Â¡Ã‚ÂµÃƒÂ©Ã¯Â¿Â½Ã‚Â¢ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¥Ã¢â€šÂ¬Ã‚Â¼
		//om.sm.uuid->om
		/*
		System.out.println(om.getSm().getUuid());
		System.out.println("-----------------");
		for(Long temp:goodsUuids){
			System.out.println(temp);
		}
		System.out.println("-----------------");
		for(Integer temp:nums){
			System.out.println(temp);
		}
		System.out.println("-----------------");
		for(Double temp:prices){
			System.out.println(temp);
		}
		*/
		orderEbi.save(getLogin(),om,goodsUuids,nums,prices);
		return TO_LIST;
	}
	
	public String buyDetailList(){
		om = orderEbi.get(om.getUuid());
		return "buyDetailList";
	}
	
	//--ÃƒÂ¥Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã‚Â Ã‚Â¸ÃƒÂ§Ã¢â‚¬ÂºÃ‚Â¸ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â³----------------------
	
	/**
	 * 
	 * @param check insert 0 to get buyCheck, 1 to get toBuyCheckDetail, 2 to get buyCheckPass, 3 to get buyCheckNoPass
	 * @return a string with the desidered value
	 */
	public String Check(int check){
		String toReturn = null;
		if (check==0) {
		setDataTotal(orderEbi.getCountByTypes(oqm));
		List<OrderModel> orderList = orderEbi.getAllNoCheckOrder(oqm,pageNum,pageCount);
		put("orderList",orderList);
		toReturn= "buyCheck";
		}
		//ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã‹â€ Ã‚Â°ÃƒÂ¥Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã‚Â Ã‚Â¸ÃƒÂ¨Ã‚Â¯Ã‚Â¦ÃƒÂ¦Ã†â€™Ã¢â‚¬Â¦ÃƒÂ©Ã‚Â¡Ã‚Âµ
		else if (check==1) {
			//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¨Ã‚Â®Ã‚Â¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬Â¢ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾uuidÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¨Ã‚Â®Ã‚Â¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬Â¢ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
			om = orderEbi.get(om.getUuid());
			toReturn = "toBuyCheckDetail";
		}
		//ÃƒÂ©Ã¢â‚¬Â¡Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â´Ã‚Â­ÃƒÂ¥Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã‚Â Ã‚Â¸ÃƒÂ©Ã¢â€šÂ¬Ã…Â¡ÃƒÂ¨Ã‚Â¿Ã¢â‚¬Â¡
		else if (check==2) {
			orderEbi.buyCheckPass(om.getUuid(),getLogin());
			toReturn = "toBuyCheck";
		}
		//ÃƒÂ©Ã¢â‚¬Â¡Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â´Ã‚Â­ÃƒÂ¥Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã‚Â Ã‚Â¸ÃƒÂ©Ã‚Â©Ã‚Â³ÃƒÂ¥Ã¢â‚¬ÂºÃ…Â¾
		else if (check==3) {
			orderEbi.buyCheckNoPass(om.getUuid(),getLogin());
			toReturn = "toBuyCheck";
		}
		else {
			toReturn = "The Value is not correct, please read documentation!";
		}
		return toReturn;
	}

	/**
	 * 
	 * @param check insert 0 to get assignTask, 1 to get assignTaskList, 2 to get assignTaskDetail
	 * @return a string with the desidered value
	 */
	//ÃƒÂ¦Ã…â€™Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â´Ã‚Â¾ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â·ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“ÃƒÂ¤Ã‚Â»Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â¡ÃƒÂ¤Ã‚ÂºÃ‚Âº
	public String assignTask(int check){
		String toReturn = null;
		if (check==0) {
		//ÃƒÂ¦Ã…â€™Ã¢â‚¬Â¡ÃƒÂ¦Ã‚Â´Ã‚Â¾ÃƒÂ¤Ã‚Â»Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â¡ÃƒÂ¤Ã‚ÂºÃ‚Âº  om.uuid   om.completer.uuid
		orderEbi.assignTask(om);
		toReturn = "toAssignTaskList";
		} else
		if (check ==1) {
			//ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¾Ã¢â‚¬Â¦ÃƒÂ¥Ã‹â€ Ã¢â‚¬Â ÃƒÂ©Ã¢â‚¬Â¦Ã¯Â¿Â½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â»Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â¡ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ©Ã¢â‚¬ÂºÃ¢â‚¬Â ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ 
			List<OrderModel> orderList = orderEbi.getAllTasks(oqm,pageNum,pageCount);
			put("orderList",orderList);
			//ÃƒÂ¨Ã‚Â·Ã‚Â³ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ©Ã‚Â¡Ã‚ÂµÃƒÂ©Ã¯Â¿Â½Ã‚Â¢
			return "assignTaskList";
		}
		else if (check ==2) {
			//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ¨Ã‚Â¿Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¾Ã¢â‚¬Å“ÃƒÂ©Ã†â€™Ã‚Â¨ÃƒÂ©Ã¢â‚¬â€�Ã‚Â¨ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬ËœÃ‹Å“ÃƒÂ¥Ã‚Â·Ã‚Â¥
			Long depUuid = getLogin().getDm().getUuid();
			List<EmpModel> empList = empEbi.getAllByDep(depUuid);
			put("empList",empList);
			om = orderEbi.get(om.getUuid());
			return "assignTaskDetail";
		}
		else {
			toReturn = "The Value is not correct, please read documentation!";
		}
		return toReturn;
	}
	
	public String queryTask(){
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ§Ã¢â€žÂ¢Ã‚Â»ÃƒÂ©Ã¢â€žÂ¢Ã¢â‚¬Â ÃƒÂ¤Ã‚ÂºÃ‚ÂºÃƒÂ¤Ã‚Â¿Ã‚Â¡ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¯ÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¤Ã‚Â»Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â¡ÃƒÂ¥Ã‹â€ Ã¢â‚¬â€�ÃƒÂ¨Ã‚Â¡Ã‚Â¨
		List<OrderModel> orderList = orderEbi.getAllByCompleter(oqm,pageNum,pageCount,getLogin());
		put("orderList",orderList);
		return "queryTask";
	}
	
	public String toTaskDetail(){
		om = orderEbi.get(om.getUuid());
		return "toTaskDetail";
	}
	
	public String completeTask(){
		orderEbi.endTask(om.getUuid());
		return "toQueryTask";
	}
	
	//--ÃƒÂ¤Ã‚Â»Ã‚Â»ÃƒÂ¥Ã…Â Ã‚Â¡ÃƒÂ¥Ã‹â€ Ã¢â‚¬Â ÃƒÂ©Ã¢â‚¬Â¦Ã¯Â¿Â½ÃƒÂ§Ã‚Â»Ã¢â‚¬Å“ÃƒÂ¦Ã¯Â¿Â½Ã…Â¸----------------------
	
	//ÃƒÂ¤Ã‚Â»Ã¢â‚¬Å“ÃƒÂ¥Ã‚ÂºÃ¢â‚¬Å“ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥ÃƒÂ¥Ã‚ÂºÃ¢â‚¬Å“
	public String inGoodsList(){
		//ÃƒÂ¥Ã‚Â±Ã¢â‚¬Â¢ÃƒÂ§Ã‚Â¤Ã‚ÂºÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¦Ã‚Â²Ã‚Â¡ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥ÃƒÂ¥Ã‚ÂºÃ¢â‚¬Å“ÃƒÂ¥Ã‚Â®Ã…â€™ÃƒÂ¦Ã‚Â¯Ã¢â‚¬Â¢ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¨Ã‚Â®Ã‚Â¢ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬Â¢ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		List<OrderModel> orderList = orderEbi.getAllNotIn(oqm,pageNum,pageCount);
		put("orderList",orderList);
		return "inGoodsList";
	}
	public String inGoodsDetail(){
		//ÃƒÂ¥Ã…Â Ã‚Â ÃƒÂ¨Ã‚Â½Ã‚Â½ÃƒÂ¦Ã¢â‚¬Â°Ã¢â€šÂ¬ÃƒÂ¦Ã…â€œÃ¢â‚¬Â°ÃƒÂ¤Ã‚Â»Ã¢â‚¬Å“ÃƒÂ¥Ã‚ÂºÃ¢â‚¬Å“ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		List<StoreModel> storeList = storeEbi.getAll();
		put("storeList",storeList);
		om = orderEbi.get(om.getUuid());
		return "inGoodsDetail";
	}
	
	//--ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥ÃƒÂ¥Ã‚ÂºÃ¢â‚¬Å“--------------------------------
	public Long odmUuid;
	private OrderDetailModel odm;
	
	public OrderDetailModel getOdm() {
		return odm;
	}

	public String ajaxGetSurplusByOdmUuid(){
		//ÃƒÂ¦Ã‚Â Ã‚Â¹ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®odmUuidÃƒÂ¨Ã…Â½Ã‚Â·ÃƒÂ¥Ã¯Â¿Â½Ã¢â‚¬â€œÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¥Ã‚ÂºÃ¢â‚¬ï¿½ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¨Ã‚Â´Ã‚Â§ÃƒÂ§Ã¢â‚¬Â°Ã‚Â©ÃƒÂ¥Ã¢â‚¬Â°Ã‚Â©ÃƒÂ¤Ã‚Â½Ã¢â€žÂ¢ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ©Ã¢â‚¬Â¡Ã¯Â¿Â½
		odm = orderDetailEbi.get(odmUuid);
		return "ajaxGetSurplusByOdmUuid";
	}
}
