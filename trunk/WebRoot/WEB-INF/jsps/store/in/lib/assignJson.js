/**
 * called in clickEvent.js
 * @param jsonParam
 * @returns
 */
function assignJson(jsonParam){
	document.writeln("<script type='text/javascript' src='pickerFun.js'></script>");

	jsonParam["num"] = $nowTr.children("td:eq(3)").children("input").val();

			jsonParam["odmUuid"] = $nowTr.prev().attr("odm");
			$.post("store_ajaxInGoods.action",jsonParam,pickerFun(data));
}