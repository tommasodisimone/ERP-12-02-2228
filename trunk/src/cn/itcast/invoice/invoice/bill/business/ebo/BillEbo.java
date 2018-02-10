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
	private static final GoodsModel[] objs = null;

	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("Ã¥Â®â€¹Ã¤Â½â€œ", Font.PLAIN, 20));
		theme.setLargeFont(new Font("Ã¥Â®â€¹Ã¤Â½â€œ", Font.PLAIN, 14));
		theme.setRegularFont(new Font("Ã¥Â®â€¹Ã¤Â½â€œ", Font.PLAIN, 12));
		theme.setSmallFont(new Font("Ã¥Â®â€¹Ã¤Â½â€œ", Font.PLAIN, 10));
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
		//Ã¥Â°â€ Ã©Â¥Â¼Ã¥â€ºÂ¾Ã¥â€ â€¦Ã¥Â®Â¹Ã¨Â½Â¬Ã¥â€¦Â¥osÃ¥Â¯Â¹Ã¨Â±Â¡
		//Ã¥â€¡â€ Ã¥Â¤â€¡Ã¦â€¢Â°Ã¦ï¿½Â®
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel) objs[0];
			Long num = (Long) objs[1];
			localDefaultPieDataset.setValue(gm.getName(), new Double(num));
		}
		//Ã¦â€¢Â°Ã¦ï¿½Â®Ã¨Â½Â¬Ã¥Å’â€“Ã¤Â¸ÂºjfreechartÃ¥Â¯Â¹Ã¨Â±Â¡
		JFreeChart localJFreeChart = ChartFactory.createPieChart("Ã©â€¡â€¡Ã¨Â´Â­Ã§Â»Å¸Ã¨Â®Â¡", localDefaultPieDataset, true, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setLabelFont(new Font("SansSerif", 0, 12));
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setLabelGap(0.02D);
		//jfreechartÃ¥Â¯Â¹Ã¨Â±Â¡Ã¨Â£â€¦Ã¥â€¦Â¥osÃ¥Â¯Â¹Ã¨Â±Â¡
		try {
			ImageIO.write(localJFreeChart.createBufferedImage(456, 360), "png", os);
		} catch (IOException e) {
			 System.out.println("Something was wrong");
		}
	}

	public InputStream getExcelBill(List<Object[]> billList) throws Exception{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		//Ã¥â€ â„¢ExcelÃ¦â€“â€¡Ã¤Â»Â¶
		WritableWorkbook b = ExcelUtil.cWorkbook(os); 
		
		//WritableSheet s = workbook.createSheet("Ã¦â‚¬Â»Ã¦â€¹Â¬", 0); 
		WritableSheet s = ExcelUtil.cSheet(b, 1, "Ã¦â‚¬Â»Ã¦â€¹Â¬");
		
		//Ã¨Â®Â¾Ã§Â½Â®Ã¨Â¡Å’Ã©Â«Ëœ
		ExcelUtil.sRowSize(s, 1, 15);
		ExcelUtil.sRowSize(s, 2, 37);
		ExcelUtil.sRowSize(s, 3, 6);
		ExcelUtil.sRowSize(s, 4, 23);
		
		//Ã¨Â®Â¾Ã§Â½Â®Ã¥Ë†â€”Ã¥Â®Â½
		ExcelUtil.sColumnSize(s, 1, 8);
		ExcelUtil.sColumnSize(s, 2, 8);
		ExcelUtil.sColumnSize(s, 3, 25);
		ExcelUtil.sColumnSize(s, 4, 25);
		ExcelUtil.sColumnSize(s, 5, 25);
		
		//Ã¥ï¿½Ë†Ã¥Â¹Â¶
		ExcelUtil.sMerge(s, 2 , 2 , 2 , 4);
		ExcelUtil.sMerge(s, 3 , 2 , 3 , 5);
		
		//Ã¨Â¿â€ºÃ¨Â´Â§Ã§Â»Å¸Ã¨Â®Â¡Ã¦Å Â¥Ã¨Â¡Â¨		
		Label lab22 = ExcelUtil.cLabel(2, 2, "Ã¨Â¿â€ºÃ¨Â´Â§Ã§Â»Å¸Ã¨Â®Â¡Ã¦Å Â¥Ã¨Â¡Â¨");
		ExcelUtil.sLabelStyle(lab22, "Ã©Â»â€˜Ã¤Â½â€œ", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		ExcelUtil.aLabelToSheet(lab22, s);
		//Ã¤Â¸ï¿½Ã©â„¢ï¿½
		Label lab25 = ExcelUtil.cLabel(2, 5, "Ã¤Â¸ï¿½Ã©â„¢ï¿½");
		ExcelUtil.sLabelStyle(lab25, "Ã©Â»â€˜Ã¤Â½â€œ", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		ExcelUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = ExcelUtil.cLabel(3, 2, "");
		ExcelUtil.sLabelStyle(lab32, "Ã©Â»â€˜Ã¤Â½â€œ", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
		ExcelUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = ExcelUtil.cLabel(4, 2, "Ã§Â¼â€“Ã¥ï¿½Â·");
		ExcelUtil.sLabelStyle(lab42, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = ExcelUtil.cLabel(4, 3, "Ã¥Å½â€šÃ¥â€¢â€ ");
		ExcelUtil.sLabelStyle(lab43, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab43, s);
		
		Label lab44 = ExcelUtil.cLabel(4, 4, "Ã¥â€¢â€ Ã¥â€œï¿½Ã¥ï¿½ï¿½");
		ExcelUtil.sLabelStyle(lab44, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = ExcelUtil.cLabel(4, 5, "Ã¦â€¢Â°Ã©â€¡ï¿½");
		ExcelUtil.sLabelStyle(lab45, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab45, s);
					
		int row = 4;
		//Ã¥Â¾ÂªÃ§Å½Â¯Ã¤ÂºÂ§Ã§â€�Å¸Ã¦â€¢Â°Ã¦ï¿½Â®
		int i = 1;
		Long sum = 0L;
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel)objs[0];
			Long num = (Long)objs[1];
			
			Label lab_data_1 = ExcelUtil.cLabel(row+i, 2, i+"");
			ExcelUtil.sLabelStyle(lab_data_1, "Ã¥Â®â€¹Ã¤Â½â€œ", 14, Colour.BLACK, null, 1, "0120");
			ExcelUtil.aLabelToSheet(lab_data_1, s);
			
			Label lab_data_2 = ExcelUtil.cLabel(row+i, 3, gm.getGtm().getSm().getName());
			ExcelUtil.sLabelStyle(lab_data_2, "Ã¥Â®â€¹Ã¤Â½â€œ", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_data_2, s);
			
			Label lab_data_3 = ExcelUtil.cLabel(row+i, 4, gm.getName());
			ExcelUtil.sLabelStyle(lab_data_3, "Ã¥Â®â€¹Ã¤Â½â€œ", 14, Colour.BLACK, null, 1, "0110");
			ExcelUtil.aLabelToSheet(lab_data_3, s);
			
			Label lab_data_4 = ExcelUtil.cLabel(row+i, 5, num.toString());
			ExcelUtil.sLabelStyle(lab_data_4, "Ã¥Â®â€¹Ã¤Â½â€œ", 14, Colour.BLACK, null, 1, "0112");
			ExcelUtil.aLabelToSheet(lab_data_4, s);
			
			sum += num;
			i++;
		}
		
		//Ã¨Â®Â¾Ã§Â½Â®Ã¨Â¡Å’Ã©Â«Ëœ
		ExcelUtil.sRowSize(s, row+i, 24);
		//Ã¥ï¿½Ë†Ã¥Â¹Â¶
		ExcelUtil.sMerge(s,row+i  , 2 , row+i , 4);
		
		Label lab_toatl_tital = ExcelUtil.cLabel(row+i, 2, "Ã¦â‚¬Â»Ã¨Â®Â¡");
		ExcelUtil.sLabelStyle(lab_toatl_tital, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2220");
		ExcelUtil.aLabelToSheet(lab_toatl_tital, s);
		
		Label lab_toatl_data = ExcelUtil.cLabel(row+i, 5, sum.toString());
		ExcelUtil.sLabelStyle(lab_toatl_data, "Ã©Â»â€˜Ã¤Â½â€œ", 18, Colour.BLACK, null, 1, "2222");
		ExcelUtil.aLabelToSheet(lab_toatl_data, s);
					
		b.write(); 
		b.close(); 
		//Ã¦â€¢Â°Ã¦ï¿½Â®Ã¥Â·Â²Ã§Â»ï¿½Ã¨Â¿â€ºÃ¥â€¦Â¥osÃ¥Â¯Â¹Ã¨Â±Â¡Ã¯Â¼Å’Ã©Å“â‚¬Ã¨Â¦ï¿½Ã¥Â°â€ osÃ¥Â¯Â¹Ã¨Â±Â¡Ã¨Â½Â¬Ã¦ï¿½Â¢Ã¦Ë†ï¿½Ã¨Â¾â€œÃ¥â€¦Â¥Ã¦Âµï¿½Ã¥Â¯Â¹Ã¨Â±Â¡
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}


}
