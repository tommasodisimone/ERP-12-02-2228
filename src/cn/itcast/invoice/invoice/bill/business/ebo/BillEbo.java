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
	public static int fourteen = 14, twelve = 12, ten = 10;
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
		localPiePlot.setLabelFont(new Font("SansSerif", 0, twelve));
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setLabelGap(zerozero2D);
		//jfreechartÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¨Ã‚Â£Ã¢â‚¬Â¦ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		try {
			ImageIO.write(localJFreeChart.createBufferedImage(456, 360), "png", os);
		} catch (IOException e) {
			 System.out.println("Something was wrong");
		}
	}

	public InputStream getExcelBill(List<Object[]> billList) throws Exception{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//ÃƒÂ¥Ã¢â‚¬Â Ã¢â€žÂ¢ExcelÃƒÂ¦Ã¢â‚¬â€œÃ¢â‚¬Â¡ÃƒÂ¤Ã‚Â»Ã‚Â¶
		WritableWorkbook b = ExcelUtil.cWorkbook(os); 
		
		//WritableSheet s = workbook.createSheet("ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¦Ã¢â‚¬Â¹Ã‚Â¬", 0); 
		WritableSheet s = ExcelUtil.cSheet(b, 1, "ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¦Ã¢â‚¬Â¹Ã‚Â¬");
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¨Ã‚Â¡Ã…â€™ÃƒÂ©Ã‚Â«Ã‹Å“
		ExcelUtil.sRowSize(s, 1, 15);
		ExcelUtil.sRowSize(s, 2, 37);
		ExcelUtil.sRowSize(s, 3, 6);
		ExcelUtil.sRowSize(s, 4, 23);
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¥Ã‹â€ Ã¢â‚¬â€�ÃƒÂ¥Ã‚Â®Ã‚Â½
		ExcelUtil.sColumnSize(s, 1, 8);
		ExcelUtil.sColumnSize(s, 2, 8);
		ExcelUtil.sColumnSize(s, 3, 25);
		ExcelUtil.sColumnSize(s, 4, 25);
		ExcelUtil.sColumnSize(s, 5, 25);
		
		//ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ ÃƒÂ¥Ã‚Â¹Ã‚Â¶
		ExcelUtil.sMerge(s, 2 , 2 , 2 , 4);
		ExcelUtil.sMerge(s, 3 , 2 , 3 , 5);
		
		//ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¨Ã‚Â´Ã‚Â§ÃƒÂ§Ã‚Â»Ã…Â¸ÃƒÂ¨Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã…Â Ã‚Â¥ÃƒÂ¨Ã‚Â¡Ã‚Â¨		
		Label lab22 = ExcelUtil.cLabel(2, 2, "ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¨Ã‚Â´Ã‚Â§ÃƒÂ§Ã‚Â»Ã…Â¸ÃƒÂ¨Ã‚Â®Ã‚Â¡ÃƒÂ¦Ã…Â Ã‚Â¥ÃƒÂ¨Ã‚Â¡Ã‚Â¨");
		ExcelUtil.sLabelStyle(lab22, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		ExcelUtil.aLabelToSheet(lab22, s);
		//ÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ©Ã¢â€žÂ¢Ã¯Â¿Â½
		Label lab25 = ExcelUtil.cLabel(2, 5, "ÃƒÂ¤Ã‚Â¸Ã¯Â¿Â½ÃƒÂ©Ã¢â€žÂ¢Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab25, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		ExcelUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = ExcelUtil.cLabel(3, 2, "");
		ExcelUtil.sLabelStyle(lab32, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
		ExcelUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = ExcelUtil.cLabel(4, 2, "ÃƒÂ§Ã‚Â¼Ã¢â‚¬â€œÃƒÂ¥Ã¯Â¿Â½Ã‚Â·");
		ExcelUtil.sLabelStyle(lab42, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = ExcelUtil.cLabel(4, 3, "ÃƒÂ¥Ã…Â½Ã¢â‚¬Å¡ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ");
		ExcelUtil.sLabelStyle(lab43, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab43, s);
		
		Label lab44 = ExcelUtil.cLabel(4, 4, "ÃƒÂ¥Ã¢â‚¬Â¢Ã¢â‚¬Â ÃƒÂ¥Ã¢â‚¬Å“Ã¯Â¿Â½ÃƒÂ¥Ã¯Â¿Â½Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab44, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = ExcelUtil.cLabel(4, 5, "ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ©Ã¢â‚¬Â¡Ã¯Â¿Â½");
		ExcelUtil.sLabelStyle(lab45, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab45, s);
					
		int row = 4;
		//ÃƒÂ¥Ã‚Â¾Ã‚ÂªÃƒÂ§Ã…Â½Ã‚Â¯ÃƒÂ¤Ã‚ÂºÃ‚Â§ÃƒÂ§Ã¢â‚¬ï¿½Ã…Â¸ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®
		int i = 1;
		Long sum = 0L;
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel)objs[0];
			Long num = (Long)objs[1];
			
			Label lab_data_1 = ExcelUtil.cLabel(row+i, 2, i+"");
			ExcelUtil.sLabelStyle(lab_data_1, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 14, Colour.BLACK, null, 1, "0120");
			ExcelUtil.aLabelToSheet(lab_data_1, s);
			
			Label lab_data_2 = ExcelUtil.cLabel(row+i, 3, gm.getGtm().getSm().getName());
			ExcelUtil.sLabelStyle(lab_data_2, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_data_2, s);
			
			Label lab_data_3 = ExcelUtil.cLabel(row+i, 4, gm.getName());
			ExcelUtil.sLabelStyle(lab_data_3, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_data_3, s);
			
			Label lab_data_4 = ExcelUtil.cLabel(row+i, 5, num.toString());
			ExcelUtil.sLabelStyle(lab_data_4, "ÃƒÂ¥Ã‚Â®Ã¢â‚¬Â¹ÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 14, Colour.BLACK, null, 1, "0112");
			ExcelUtil.aLabelToSheet(lab_data_4, s);
			
			sum += num;
			i++;
		}
		
		//ÃƒÂ¨Ã‚Â®Ã‚Â¾ÃƒÂ§Ã‚Â½Ã‚Â®ÃƒÂ¨Ã‚Â¡Ã…â€™ÃƒÂ©Ã‚Â«Ã‹Å“
		ExcelUtil.sRowSize(s, row+i, 24);
		//ÃƒÂ¥Ã¯Â¿Â½Ã‹â€ ÃƒÂ¥Ã‚Â¹Ã‚Â¶
		ExcelUtil.sMerge(s,row+i  , 2 , row+i , 4);
		
		Label lab_toatl_tital = ExcelUtil.cLabel(row+i, 2, "ÃƒÂ¦Ã¢â€šÂ¬Ã‚Â»ÃƒÂ¨Ã‚Â®Ã‚Â¡");
		ExcelUtil.sLabelStyle(lab_toatl_tital, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab_toatl_tital, s);
		
		Label lab_toatl_data = ExcelUtil.cLabel(row+i, 5, sum.toString());
		ExcelUtil.sLabelStyle(lab_toatl_data, "ÃƒÂ©Ã‚Â»Ã¢â‚¬ËœÃƒÂ¤Ã‚Â½Ã¢â‚¬Å“", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab_toatl_data, s);
					
		b.write(); 
		b.close(); 
		//ÃƒÂ¦Ã¢â‚¬Â¢Ã‚Â°ÃƒÂ¦Ã¯Â¿Â½Ã‚Â®ÃƒÂ¥Ã‚Â·Ã‚Â²ÃƒÂ§Ã‚Â»Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¿Ã¢â‚¬ÂºÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¯Ã‚Â¼Ã…â€™ÃƒÂ©Ã…â€œÃ¢â€šÂ¬ÃƒÂ¨Ã‚Â¦Ã¯Â¿Â½ÃƒÂ¥Ã‚Â°Ã¢â‚¬Â osÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡ÃƒÂ¨Ã‚Â½Ã‚Â¬ÃƒÂ¦Ã¯Â¿Â½Ã‚Â¢ÃƒÂ¦Ã‹â€ Ã¯Â¿Â½ÃƒÂ¨Ã‚Â¾Ã¢â‚¬Å“ÃƒÂ¥Ã¢â‚¬Â¦Ã‚Â¥ÃƒÂ¦Ã‚ÂµÃ¯Â¿Â½ÃƒÂ¥Ã‚Â¯Ã‚Â¹ÃƒÂ¨Ã‚Â±Ã‚Â¡
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}


}
