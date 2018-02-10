/**
 * this file is called in ajaxGet.js
 * @returns
 */

function tdSet(){
	var $td2 = $("<td></td>");
	var $selectGm = $("<select name='goodsUuids' class='goods' style='width:200px'></select>");
	for(var i = 0;i<gmList.length;i++){
		var gm = gmList[i];
	var	$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
		$selectGm.append($op);
	}
	$td2.append($selectGm);
	$tr.append($td2);
	
	var $td3 = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>');
	$tr.append($td3);
	
}