package cn.itcast.invoice.invoice.bill.business.ebo;

import java.awt.Font;
import java.awt.RenderingHints;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import cn.itcast.invoice.invoice.bill.business.ebi.BillEbi;
import cn.itcast.invoice.invoice.bill.dao.dao.BillDao;
import cn.itcast.invoice.invoice.bill.vo.BillQueryModel;
import cn.itcast.invoice.invoice.goods.vo.GoodsModel;
import cn.itcast.invoice.invoice.order.vo.OrderDetailModel;
import cn.itcast.invoice.util.jxl.ExcelUtil;
/**
 * this class implements BillEbi
 *
 */
public class BillEbo implements BillEbi{
	public static int twenty = 20;
	public static int fourteen = 14, twelve = 12, ten = 10, fourfivesix = 456, threesixO = 360, one = 1, two = 2, three = 3, four = 4, five = 5, 
			twentyfive = 25, fifteen = 15, thirtyseven = 37, zero = 0, six = 6, twentythree = 23, eight = 8, twentyfour = 24, eighteen = 18;
	public static double zerozero2D = 0.02D;
	private static final GoodsModel[] objs = null;

	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		
		theme.setExtraLargeFont(new Font("ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", Font.PLAIN, twenty));
		theme.setLargeFont(new Font("ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", Font.PLAIN, fourteen));
		theme.setRegularFont(new Font("ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", Font.PLAIN, twelve));
		theme.setSmallFont(new Font("ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", Font.PLAIN, ten));
		ChartFactory.setChartTheme(theme);
	}
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public List<Object[]> getBillByGoods(BillQueryModel bqm) {
		return billDao.getBillByGoods(bqm);
	}

	public List<OrderDetailModel> getBillDetailByGoods(BillQueryModel bqm) {
		return billDao.getBillDetailByGoods(bqm);
	}

	public void getBillForPie(OutputStream os, List<Object[]> billList) {
		//ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â ÃƒÂ©Ã‚Â¥Ã‚Â¼ÃƒÂ¥Ã¢â‚¬ÂºÃ‚Â¾ÃƒÂ¥Ã¢â‚¬Â Ã¢â‚¬Â¦ÃƒÂ¥Ã‚Â®Ã‚Â¹ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		//ÃƒÂ¥Ã¢â‚¬Â¡Ã¢â‚¬Â ÃƒÂ¥Ã‚Â¤Ã¢â‚¬Â¡ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel) objs[0];
			Long num = (Long) objs[1];
			localDefaultPieDataset.setValue(gm.getName(), new Double(num));
		}
		//ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¥Ã…â€™Ã¢â‚¬â€œÃƒÂ¤Ã‚Â¸Ã‚ÂºjfreechartÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		JFreeChart localJFreeChart = ChartFactory.createPieChart("ÃƒÂ©Ã¢â‚¬Â¡Ã¢â‚¬Â¡ÃƒÂ¨Ã‚Â´Ã‚Â­ÃƒÂ§Ã‚Â»Ã…Â¸ÃƒÂ¨Ã‚Â®Ã‚Â¡", localDefaultPieDataset, true, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setLabelFont(new Font("SansSerif", zero, twelve));
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setLabelGap(zerozero2D);
		//jfreechartÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¨Ã‚Â£Ã¢â‚¬Â¦ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		try {
			ImageIO.write(localJFreeChart.createBufferedImage(fourfivesix, threesixO), "png", os);
		} catch (IOException e) {
			 System.out.println("Something was wrong");
		}
	}

	public InputStream getExcelBill(List<Object[]> billList) throws Exception{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//ÃƒÂ¥Ã¢â‚¬Â Ã¢â€žÂ¢ExcelÃƒÂ¦Ã¢â‚¬â€œÃ¢â‚¬Â¡ÃƒÂ¤Ã‚Â»Ã‚Â¶
		WritableWorkbook b = ExcelUtil.cWorkbook(os); 
		
		//WritableSheet s = workbook.createSheet("ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¦Ã¢â‚¬Â¹Ã‚Â¬", 0); 
		WritableSheet s = ExcelUtil.cSheet(b, one, "ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¦Ã¢â‚¬Â¹Ã‚Â¬");
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¨Ã‚Â¡Ã…â€™ÃƒÂ©Ã‚Â«Ã‹Å“
		ExcelUtil.sRowSize(s, one, fifteen);
		ExcelUtil.sRowSize(s, two, thirtyseven);
		ExcelUtil.sRowSize(s, three, six);
		ExcelUtil.sRowSize(s, four, twentythree);
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¥Ã‹â€ Ã¢â‚¬â€�ÃƒÂ¥Ã‚Â®Ã‚Â½
		ExcelUtil.sColumnSize(s, one, eight);
		ExcelUtil.sColumnSize(s, two, eight);
		ExcelUtil.sColumnSize(s, three, twentyfive);
		ExcelUtil.sColumnSize(s, four, twentyfive);
		ExcelUtil.sColumnSize(s, five, twentyfive);
		
		//ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ ÃƒÂ¥Ã‚Â¹Ã‚Â¶
		ExcelUtil.sMerge(s, two , two , two , four);
		ExcelUtil.sMerge(s, three , two , three , five);
		
		//ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¨Ã‚Â´Ã‚Â§ÃƒÂ§Ã‚Â»Ã…Â¸ÃƒÂ¨Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã…Â Ã‚Â¥ÃƒÂ¨Ã‚Â¡Ã‚Â¨		
		Label lab22 = ExcelUtil.cLabel(two, two, "ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¨Ã‚Â´Ã‚Â§ÃƒÂ§Ã‚Â»Ã…Â¸ÃƒÂ¨Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã…Â Ã‚Â¥ÃƒÂ¨Ã‚Â¡Ã‚Â¨");
		ExcelUtil.sLabelStyle(lab22, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", twentyfour, Colour.BLACK, Colour.LIGHT_BLUE, one, "2020");
		ExcelUtil.aLabelToSheet(lab22, s);
		//ÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ©Ã¢â€žÂ¢Ã¯Â¿Â½
		Label lab25 = ExcelUtil.cLabel(two, five, "ÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ©Ã¢â€žÂ¢Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab25, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", twelve, Colour.BLACK, Colour.LIGHT_BLUE, one, "2002");
		ExcelUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = ExcelUtil.cLabel(three, two, "");
		ExcelUtil.sLabelStyle(lab32, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", one, Colour.BLACK, Colour.GRAY_25, one, "2022");
		ExcelUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = ExcelUtil.cLabel(four, two, "ÃƒÂ§Ã‚Â¼Ã¢â‚¬â€œÃƒÂ¥Ã¯Â¿Â½Ã‚Â·");
		ExcelUtil.sLabelStyle(lab42, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2220");
		ExcelUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = ExcelUtil.cLabel(four, three, "ÃƒÂ¥Ã…Â½Ã¢â‚¬Å¡ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ");
		ExcelUtil.sLabelStyle(lab43, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2220");
		ExcelUtil.aLabelToSheet(lab43, s);
		
		Label lab44 = ExcelUtil.cLabel(four, four, "ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¥Ã¯Â¿Â½Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab44, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2220");
		ExcelUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = ExcelUtil.cLabel(four, five, "ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ©Ã¢â‚¬Â¡Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab45, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2222");
		ExcelUtil.aLabelToSheet(lab45, s);
					
		int row = 4;
		//ÃƒÂ¥Ã‚Â¾Ã‚ÂªÃƒÂ§Ã…Â½Ã‚Â¯ÃƒÂ¤Ã‚ÂºÃ‚Â§ÃƒÂ§Ã¢â‚¬ï¿½Ã…Â¸ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		int i = 1;
		Long sum = 0L;
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel)objs[0];
			Long num = (Long)objs[1];
			
			Label lab_data_1 = ExcelUtil.cLabel(row+i, two, i+"");
			ExcelUtil.sLabelStyle(lab_data_1, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", fourteen, Colour.BLACK, null, one, "0120");
			ExcelUtil.aLabelToSheet(lab_data_1, s);
			
			Label lab_data_2 = ExcelUtil.cLabel(row+i, three, gm.getGtm().getSm().getName());
			ExcelUtil.sLabelStyle(lab_data_2, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", fourteen, Colour.BLACK, null, one, "0110");
			ExcelUtil.aLabelToSheet(lab_data_2, s);
			
			Label lab_data_3 = ExcelUtil.cLabel(row+i, four, gm.getName());
			ExcelUtil.sLabelStyle(lab_data_3, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", fourteen, Colour.BLACK, null, one, "0110");
			ExcelUtil.aLabelToSheet(lab_data_3, s);
			
			Label lab_data_4 = ExcelUtil.cLabel(row+i, four, num.toString());
			ExcelUtil.sLabelStyle(lab_data_4, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", fourteen, Colour.BLACK, null, one, "0112");
			ExcelUtil.aLabelToSheet(lab_data_4, s);
			
			sum += num;
			i++;
		}
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¨Ã‚Â¡Ã…â€™ÃƒÂ©Ã‚Â«Ã‹Å“
		ExcelUtil.sRowSize(s, row+i, twentyfour);
		//ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ ÃƒÂ¥Ã‚Â¹Ã‚Â¶
		ExcelUtil.sMerge(s,row+i  , two , row+i , four);
		
		Label lab_toatl_tital = ExcelUtil.cLabel(row+i, two, "ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¨Ã‚Â®Ã‚Â¡");
		ExcelUtil.sLabelStyle(lab_toatl_tital, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2220");
		ExcelUtil.aLabelToSheet(lab_toatl_tital, s);
		
		Label lab_toatl_data = ExcelUtil.cLabel(row+i, five, sum.toString());
		ExcelUtil.sLabelStyle(lab_toatl_data, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", eighteen, Colour.BLACK, null, one, "2222");
		ExcelUtil.aLabelToSheet(lab_toatl_data, s);
					
		b.write(); 
		b.close(); 
		//ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ©Ã…â€œÃ¢â€šÂ¬ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¢ÃƒÂ¦Ã‹â€ Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¾Ã¢â‚¬Å“ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥ÃƒÂ¦Ã‚ÂµÃ¯Â¿Â½ÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}


}
