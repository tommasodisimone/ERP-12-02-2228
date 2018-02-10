/**
 * called in input.js
 * @returns
 */
function addFun(){
	document.writeln("<script type='text/javascript' src='callIntFun.js'></script>");
			callIntFun();
			var vars = addVar();
			var goodsTypeObjs = vars[0];
			var goodsObjs = vars[1];
			var jsonParam = vars[2];
			var hasUuids = vars[3];
			
			forAddFun(hasUuids, goodsTypeObjs, goodsObjs);
			
			jsonParam["hasUuids"]= hasUuids;
				
				//动态添加一个tr行
				//创建tr组件
				var $tr = $("<tr></tr>");
				
				//创建td组件，生成商品类别select
				var typeSelectStr = "<select class='goodsType' style='width:200px'>";
				var i;
				for(i = 0;i<4;i++){
					typeSelectStr+="<option value='";
					typeSelectStr+=111;
					typeSelectStr+="'>";
					typeSelectStr+="类别名称"+i;
					typeSelectStr+="</option>";
				}
				typeSelectStr += "</select>";
				var $td1 = $("<td height='30'>"+typeSelectStr+"</td>");
				$tr.append($td1);
				
				//创建td组件，生成商品select
				var goodsSelectStr = "<select name='gmUuids' class='goods' style='width:200px'>";
				for(i = 0;i<4;i++){
					goodsSelectStr+="<option value='";
					goodsSelectStr+=123;
					goodsSelectStr+="'>";
					goodsSelectStr+="新商品名"+i;
					goodsSelectStr+="</option>";
				}
				goodsSelectStr += "</select>";
				
				addFunSecond($tr, goodsSelectStr);
		}