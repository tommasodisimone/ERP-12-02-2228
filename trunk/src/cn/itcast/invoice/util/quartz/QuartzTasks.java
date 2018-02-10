package cn.itcast.invoice.util.quartz;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.invoice.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.invoice.util.format.FormatUtil;
/**
 * this class is a time class that send automatic emails
 *
 */
@Transactional
public class QuartzTasks {
	private GoodsDao goodsDao;
	private JavaMailSenderImpl mailSender;
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	//Ã¨Â¦ï¿½Ã¦Â±â€šÃ¦Å’â€¡Ã¥Â®Å¡Ã¤Âºâ€¹Ã¤Â»Â¶Ã§â€šÂ¹Ã¤Â¸Å Ã¥Â®Å’Ã¦Ë†ï¿½Ã¤Â¸â€¹Ã¥Ë†â€”Ã¤Â»Â»Ã¥Å Â¡ 
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	public void goodsUseNumUpdate(){
		System.out.println(123);
		//Ã¨Â°Æ’Ã§â€�Â¨goodsÃ¦Â¨Â¡Ã¥ï¿½â€”Ã§Å¡â€ždaoÃ¦â€°Â§Ã¨Â¡Å’Ã¥Â¯Â¹Ã¥Âºâ€�Ã§Å¡â€župdateÃ¨Â¯Â­Ã¥ï¿½Â¥
		/*
		update 
			tbl_goods g
		set
			useNum =
		(
		select 
			count(goodsUuid)
		from 
			tbl_orderdetail
		where 
			goodsUuid = g.uuid
		)
		*/
		goodsDao.updateUseNum();
	}
	
	public void storeWarn(){
		try {
			
		
		//Ã¨Å½Â·Ã¥ï¿½â€“Ã¦Å Â¥Ã¨Â­Â¦Ã¦â€¢Â°Ã¦ï¿½Â®
		/*
		select
			gm.name,sum(sdm.num)>gm.maxNum,sum(sdm.num)<gm.minNum
		from 
			tbl_storedetail sdm,
			tbl_goods gm
		where
			gm.uuid = sdm.goodsUuid
		group by
			goodsUuid
		*/
		List<Object[]> temp = goodsDao.getStoreWarnInfo();
		//Ã¤Â½Â¿Ã§â€�Â¨emailÃ¥ï¿½â€˜Ã©â‚¬ï¿½Ã¦â€¢Â°Ã¦ï¿½Â®Ã¥Ë†Â°Ã¦Å’â€¡Ã¥Â®Å¡Ã¨Â´Â¦Ã¦Ë†Â·
		//Ã¤Â½Â¿Ã§â€�Â¨mailsenderÃ¥Â¯Â¹Ã¨Â±Â¡
		SimpleMailMessage msg = new SimpleMailMessage();
		//Ã¨Â®Â¾Ã§Â½Â®Ã¥ï¿½â€˜Ã©â‚¬ï¿½Ã¤ÂºÂº
		msg.setFrom("itcast0228@126.com");
		//Ã¨Â®Â¾Ã§Â½Â®Ã¦Å½Â¥Ã¦â€�Â¶Ã¤ÂºÂº
		msg.setTo("1@126.com");
		//Ã¨Â®Â¾Ã§Â½Â®Ã¤Â¸Â»Ã©Â¢Ëœ
		msg.setSubject("Ã¥Âºâ€œÃ¥Â­ËœÃ©Â¢â€žÃ¨Â­Â¦Ã¤Â¿Â¡Ã¦ï¿½Â¯"+FormatUtil.formatDateTime(System.currentTimeMillis()));
		//Ã¨Â®Â¾Ã§Â½Â®Ã¦Â­Â£Ã¦â€“â€¡
		StringBuilder sbf = new StringBuilder();
		//Ã¦Â Â¹Ã¦ï¿½Â®Ã¦â€¢Â°Ã¦ï¿½Â®Ã§Â»â€žÃ§Â»â€¡Ã¦Â­Â£Ã¦â€“â€¡Ã¥â€ â€¦Ã¥Â®Â¹
		for(Object[] objs:temp){
			BigInteger flag = (BigInteger) objs[1];
			if(flag.intValue() == 1){
				//Ã¨Â¶â€¦Ã¨Â¿â€¡Ã¤Â¸Å Ã©â„¢ï¿½
				sbf.append("Ã¥â€¢â€ Ã¥â€œï¿½Ã£â‚¬ï¿½"+objs[0].toString()+"Ã£â‚¬â€˜Ã¥Âºâ€œÃ¥Â­ËœÃ¨Â¶â€¦Ã¨Â¿â€¡Ã¦Å“â‚¬Ã¥Â¤Â§Ã¥â‚¬Â¼Ã¯Â¼Å’Ã¨Â¯Â·Ã¥ï¿½Å“Ã¦Â­Â¢Ã¨Â¡Â¥Ã¨Â´Â§Ã¯Â¼Å’Ã¥Â¹Â¶Ã¨Â¿â€ºÃ¨Â¡Å’Ã¤Â¿Æ’Ã©â€�â‚¬Ã¯Â¼ï¿½");
				sbf.append("\r\n");
				continue;
			}
			flag = (BigInteger) objs[2];
			if(flag.intValue() == 1){
				//Ã¤Â½Å½Ã¤ÂºÅ½Ã¤Â¸â€¹Ã©â„¢ï¿½
				sbf.append("Ã¥â€¢â€ Ã¥â€œï¿½Ã£â‚¬ï¿½"+objs[0].toString()+"Ã£â‚¬â€˜Ã¥Âºâ€œÃ¥Â­ËœÃ¤Â¸ï¿½Ã¨Â¶Â³Ã¯Â¼Å’Ã¨Â¯Â·Ã¥ï¿½Å Ã¦â€”Â¶Ã¨Â¡Â¥Ã¨Â´Â§Ã¯Â¼ï¿½");
				sbf.append("\r\n");
			}
		}
		msg.setText(sbf.toString());
		//Ã¨Â®Â¾Ã§Â½Â®Ã¥ï¿½â€˜Ã©â‚¬ï¿½Ã¦â€”Â¶Ã©â€”Â´
		msg.setSentDate(new Date());
		mailSender.send(msg);
		} catch (Exception e) {
			 System.out.println("Something was wrong");
		}
	}
}
