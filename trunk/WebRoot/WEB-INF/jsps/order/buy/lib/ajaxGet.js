/**
 * this file is called in addClick.js
 * @param data
 * @returns
 */

function ajaxGet(data){
		//data.gtmList	uuid name
		var gtmList = data.gtmList;
		//data.gmList		uuid name
		var gmList = data.gmList;
		
		var $tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
		
		var $td1 = $("<td height='30px'></td>");
		var $selectGtm = $("<select class='goodsType' style='width:200px'></select>");
		for(var i = 0;i<gtmList.length;i++){
			var gtm = gtmList[i];
		var	$op = $("<option value='"+gtm.uuid+"'>"+gtm.name+"</option>");
			$selectGtm.append($op);
		}
		$td1.append($selectGtm);
		$tr.append($td1);
		
		document.writeln("<script type='text/javascript' src='tdSet.js'></script>");
		tdSet();
		document.writeln("<script type='text/javascript' src='tdFinal.js'></script>");
		tdFinal();
		
	}