/**
 * this file is called in input.js
 * 
 * @returns
 */

function ajOnClick(){
			//0.页面校验输入是否合法（省略）
			//1.组织ajax提交的数据
			document.writeln("<script type='text/javascript' src='jsonInitializer.js'></script>");
			document.writeln("<script type='text/javascript' src='jsonFinalizer.js'></script>");
			document.writeln("<script type='text/javascript' src='upMyIni.js'></script>");
			var jsonParam =jsonInitializer();
			jsonParam =jsonFinalizer();
			//为ajax提交操作后的操作对象进行初始化
			var temp = upMy();
			var $upTr = temp[0];
			var $upCenter =temp[1];
			var $upRight = temp[2];
			var $myTr = temp[3];
				
				
				if(0 == 0){
					$upTr.remove();
					$myTr.remove();
				}
			}