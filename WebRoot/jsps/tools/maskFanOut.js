/**
 * this file is called in mask.js
 * @returns
 */
function visibility() {
				$('pageOverlay').style.visibility = '';
				if($('pageOverlay').style.visibility.className) $('pageOverlay').style.visibility.className += 'visible';
				var p = getPage(), left = p.left, top = p.top;

				// 页面鼠标操作限制
				this.mouse = function(evt) {
					var e = evt || window.event;
					stopDefault(e);
					scroll(left, top);
				};
				each(
						[ 'DOMMouseScroll', 'mousewheel', 'scroll',
								'contextmenu' ], function(o, i) {
							bind(document, o, lock.mouse);
						});
				// 屏蔽特定按键: F5, Ctrl + R, Ctrl + A, Tab, Up, Down
				this.key = function(evt) {
					var e = evt || window.event, key = e.keyCode;
					if ((key == 116) || (e.ctrlKey && key == 82)
							|| (e.ctrlKey && key == 65) || (key == 9)
							|| (key == 38) || (key == 40)) {
						try {
							e.keyCode = 0;
						} catch (_) {
						}
						;
						stopDefault(e);
					}
					;
				};
				bind(document, 'keydown', lock.key);
			}


function hid() {
	//显示遮罩的方法调用
	//lock.show();
	//以上为测试操作，以下为真实操作-----------------------------
	lock.close();
	$('frame-contect').src = $('hid-action').value;
	if($('context-msg').style.display.className) $('context-msg').style.display.className += "none";
}