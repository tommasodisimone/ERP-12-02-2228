package cn.itcast.invoice.util.format;

import java.time.LocalDate;
import java.time.format.*;
import java.text.DecimalFormat;

/**
 * this class is used to format all the date and time string of the project
 *
 */
public class FormatUtil {
	public static String formatDate(Long time){
		if(time == null) return "-";
		LocalDate date = LocalDate.now();
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		  String text = date.format(formatter);
		  LocalDate parsedDate = LocalDate.parse(text, formatter);
		return  parsedDate.toString();
	}
	public static String formatTime(Long time){
		if(time == null) return "-";
		LocalDate date = LocalDate.now();
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
		  String text = date.format(formatter);
		  LocalDate parsedDate = LocalDate.parse(text, formatter);
		return  parsedDate.toString();
	}
	public static String formatDateTime(Long time){
		if(time == null) return "-";
		LocalDate date = LocalDate.now();
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
		  String text = date.format(formatter);
		  LocalDate parsedDate = LocalDate.parse(text, formatter);
		return  parsedDate.toString();
	}
	//å°†ä¸€ä¸ªå°�æ•°æ˜¾ç¤ºä¸¤ä½�å°�æ•°
	public static String formatMoney(Double money){
		DecimalFormat df = new DecimalFormat("#0.00");
	    return df.format(money);
	}
	
}
