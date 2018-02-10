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
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

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
		//èŽ·å�–é‡‡è´­æŠ¥è¡¨æ•°æ�®
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
		//åŠ è½½æ‰€æœ‰ä¾›åº”å•†æ•°æ�®
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		return "buyBill";
	}
	private List<OrderDetailModel> odmList;
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}
	public String ajaxBillDetailByGoods(){
		odmList = billEbi.getBillDetailByGoods(bqm);
		return "ajaxBillDetailByGoods";
	}
	//èŽ·å�–é¥¼å›¾
	public void billForPie() throws IOException{
		//1.èŽ·å�–jfreechartå¯¹è±¡ï¼Œå°†å…¶è£…å…¥æµ�å¯¹è±¡
		//2.ä¼ é€’åˆ°å�Žå�°ä¸€ä¸ªæµ�å¯¹è±¡ï¼Œåœ¨å�Žå�°å®Œæˆ�jfreechartå¯¹è±¡è½¬å…¥æµ�çš„æ“�ä½œ
		//å‡†å¤‡æ•°æ�®bqm->list
		List<Object[]> billList = billEbi.getBillByGoods(bqm);
		//å‡†å¤‡ä¸€ä¸ªæµ�å¯¹è±¡
		OutputStream os = getResponse().getOutputStream();
		//ä¼ é€’åˆ°å�Žå�°ï¼Œå°†jfreechartè½¬æ�¢åˆ°æµ�ä¸­
		billEbi.getBillForPie(os,billList);
		//åˆ·æ–°æµ�ï¼Œå°†å›¾åƒ�é€�å›žé¡µé�¢
		os.flush();
	}
	//ä¸‹è½½ExcelæŠ¥è¡¨
	private InputStream downloadExcel;
	public InputStream getDownloadExcel() {
		return downloadExcel;
	}
	private String xlsName;
	public String getXlsName() throws UnsupportedEncodingException {
		System.out.println(xlsName);
		return new String(xlsName.getBytes("UTF-8"),"ISO8859-1");
	}

	public String downloadExcelBill() throws Exception{
		xlsName = "è´§ç‰©ç»Ÿè®¡æŠ¥è¡¨ï¼»"+FormatUtil.formatDate(System.currentTimeMillis())+"ï¼½.xls";
		List<Object[]> billList = billEbi.getBillByGoods(bqm);
		downloadExcel = billEbi.getExcelBill(billList);
		return "downloadExcelBill";
	}
}
