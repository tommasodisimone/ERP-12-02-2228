package cn.itcast.invoice.invoice.bill.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.itcast.invoice.invoice.bill.business.ebi.BillEbi;
import cn.itcast.invoice.invoice.bill.vo.BillQueryModel;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.invoice.invoice.supplier.vo.SupplierModel;
import cn.itcast.invoice.util.base.BaseAction;
import cn.itcast.invoice.util.format.FormatUtil;
/**
 * this class extends BaseAction
 *
 */
public class BillAction extends BaseAction{
	/**
	 * this public element is a public element
	 *
	 */
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
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
	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	/**
	 * this public element is a public element
	 *
	 */
	public String buyBill(){
		/*
		select 
			g.uuid,		
			g.name,			
			sum(od.num)
		from 
			tbl_orderdetail od,
			tbl_goods g
		where
			od.goodsUuid = g.uuid
		group by
			od.goodsUuid 
		*/
		//Ã¨Å½Â·Ã¥ï¿½â€“Ã©â€¡â€¡Ã¨Â´Â­Ã¦Å Â¥Ã¨Â¡Â¨Ã¦â€¢Â°Ã¦ï¿½Â®
		List<Object[]> billList = billEbi.getBillByGoods(bqm);
		/*
		for(Object[] objs:billList){
			GoodsModel temp = (GoodsModel) objs[0];
			Long sum = (Long) objs[1];
			System.out.println(temp.getName()+","+sum);
			System.out.println("------------------");
		}
		*/
		put("billList",billList);
		//Ã¥Å Â Ã¨Â½Â½Ã¦â€°â‚¬Ã¦Å“â€°Ã¤Â¾â€ºÃ¥Âºâ€�Ã¥â€¢â€ Ã¦â€¢Â°Ã¦ï¿½Â®
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		return "buyBill";
	}
	private List<OrderDetailModel> odmList;
	/**
	 * this public element is a public element
	 *
	 */
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}
	/**
	 * this public element is a public element
	 *
	 */
	public String ajaxBillDetailByGoods(){
		odmList = billEbi.getBillDetailByGoods(bqm);
		return "ajaxBillDetailByGoods";
	}
	//Ã¨Å½Â·Ã¥ï¿½â€“Ã©Â¥Â¼Ã¥â€ºÂ¾
	/**
	 * this public element is a public element
	 *
	 */
	public void billForPie() throws IOException{
		//1.Ã¨Å½Â·Ã¥ï¿½â€“jfreechartÃ¥Â¯Â¹Ã¨Â±Â¡Ã¯Â¼Å’Ã¥Â°â€ Ã¥â€¦Â¶Ã¨Â£â€¦Ã¥â€¦Â¥Ã¦Âµï¿½Ã¥Â¯Â¹Ã¨Â±Â¡
		//2.Ã¤Â¼Â Ã©â‚¬â€™Ã¥Ë†Â°Ã¥ï¿½Å½Ã¥ï¿½Â°Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¦Âµï¿½Ã¥Â¯Â¹Ã¨Â±Â¡Ã¯Â¼Å’Ã¥Å“Â¨Ã¥ï¿½Å½Ã¥ï¿½Â°Ã¥Â®Å’Ã¦Ë†ï¿½jfreechartÃ¥Â¯Â¹Ã¨Â±Â¡Ã¨Â½Â¬Ã¥â€¦Â¥Ã¦Âµï¿½Ã§Å¡â€žÃ¦â€œï¿½Ã¤Â½Å“
		//Ã¥â€¡â€ Ã¥Â¤â€¡Ã¦â€¢Â°Ã¦ï¿½Â®bqm->list
		List<Object[]> billList = billEbi.getBillByGoods(bqm);
		//Ã¥â€¡â€ Ã¥Â¤â€¡Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¦Âµï¿½Ã¥Â¯Â¹Ã¨Â±Â¡
		OutputStream os = getResponse().getOutputStream();
		//Ã¤Â¼Â Ã©â‚¬â€™Ã¥Ë†Â°Ã¥ï¿½Å½Ã¥ï¿½Â°Ã¯Â¼Å’Ã¥Â°â€ jfreechartÃ¨Â½Â¬Ã¦ï¿½Â¢Ã¥Ë†Â°Ã¦Âµï¿½Ã¤Â¸Â­
		billEbi.getBillForPie(os,billList);
		//Ã¥Ë†Â·Ã¦â€“Â°Ã¦Âµï¿½Ã¯Â¼Å’Ã¥Â°â€ Ã¥â€ºÂ¾Ã¥Æ’ï¿½Ã©â‚¬ï¿½Ã¥â€ºÅ¾Ã©Â¡ÂµÃ©ï¿½Â¢
		os.flush();
	}
	//Ã¤Â¸â€¹Ã¨Â½Â½ExcelÃ¦Å Â¥Ã¨Â¡Â¨
	private InputStream downloadExcel;
	/**
	 * this public element is a public element
	 *
	 */
	public InputStream getDownloadExcel() {
		return downloadExcel;
	}
	private String xlsName;
	/**
	 * this public element is a public element
	 *
	 */
	public String getXlsName() throws UnsupportedEncodingException {
		System.out.println(xlsName);
		return new String(xlsName.getBytes("UTF-8"),"ISO8859-1");
	}

	/**
	 * this public element is a public element
	 *
	 */
	public String downloadExcelBill() throws Exception{
		xlsName = "Ã¨Â´Â§Ã§â€°Â©Ã§Â»Å¸Ã¨Â®Â¡Ã¦Å Â¥Ã¨Â¡Â¨Ã¯Â¼Â»"+FormatUtil.formatDate(System.currentTimeMillis())+"Ã¯Â¼Â½.xls";
		List<Object[]> billList = billEbi.getBillByGoods(bqm);
		downloadExcel = billEbi.getExcelBill(billList);
		return "downloadExcelBill";
	}
}
