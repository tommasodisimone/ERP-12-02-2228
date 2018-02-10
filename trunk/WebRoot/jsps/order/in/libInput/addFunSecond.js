/**
 * called addFun.js
 * @returns
 */
function addFunSecond($tr, goodsSelectStr){
	document.writeln("<script type='text/javascript' src='callIntFun.js'></script>");
	callIntFun();
	var $td2 = $("<td>"+goodsSelectStr+"</td>");
	$tr.append($td2);
	
	//创建td组件，生成商品数量input，默认值1
	var $td3 = $("<td>&nbsp;<input name='nums' type='text' value='1' class='num order_num' style='width:67px;border:1px solid black;text-align:right;padding:2px' /></td>");
	$tr.append($td3);
	
	var $td4 = $("<td style='padding-left:4px'><input name='prices' type='text' value='"+222+"' class='prices order_num' style='width:93px;border:1px solid black;text-align:right;padding:2px' /> 元</td>");
	$tr.append($td4);
	
	$tr = addFunJquery($tr);
	
	
	
	//在最后一个tr对象前添加该tr组件
	addFunFinal($tr);
}