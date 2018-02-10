/**
 * this file is called in buyBill.js
 *
 * @param data
 * @returns
 */
function dataBill(data){
				//后台发送了明细数据
				var odmList = data.odmList;
				
				//使用上述值拼写tr
					//添加表头
			var	$headTr = $('<tr align="center" style="background:url(images/table_bg.gif) repeat-x;" class="ajaxMsg"><td height="30">订单号</td><td>订单时间</td><td>数量</td><td>单价</td><td>合计</td></tr>'); 
				$nowTr.after($headTr);
			var	$nowTr = $headTr;
				var sum = 0;
				for(var i = 0;i<odmList.length;i++){
					var odm = odmList[i];
					
					var orderNum = odm.om.orderNum;
					var createTimeView = odm.om.createTimeView;
					var num = odm.num;
					var priceView = odm.priceView;
				
					sum+=num*priceView;
					
					//添加数据（循环）
				var	$dataTr = $('<tr align="center" class="ajaxMsg"><td height="30">'+orderNum+'</td><td>'+createTimeView+'</td><td>'+num+'</td><td align="right">'+priceView+'&nbsp;元</td><td align="right">'+intToFloat(num*priceView)+'&nbsp;元</td></tr>');
					$nowTr.after($dataTr);
					$nowTr = $dataTr;
				}
				//添加表尾
				var $tailTr = $('<tr align="center" class="ajaxMsg"><td height="30" align="right" colspan="4">总计：</td><td align="right">'+intToFloat(sum)+' 元</td></tr>'); 
				$nowTr.after($tailTr);
				
			}