/**
 * called in clickEvent.js
 * @param data
 * @returns
 */
function pickerFun(data){
	document.writeln("<script type='text/javascript' src='ifSurplus.js'></script>");
				//if(msg){
					//输入的东西有问题
					//找个合适的地方提示用户
				//}
				var	surplus = data.odm.surplus;
    			var num = data.odm.num;
    			var has = data.has;
    			//获取该订单是否全部操作完毕
    			if(has){
					//已经全部入库完毕
					$("#inOrderTitle").remove();
					$("#inOrder").remove();
					$("#allInTitle").css("display","block");
					$("#return").css("display","block");
    			}
    			
    			ifSurplus(surplus);
}