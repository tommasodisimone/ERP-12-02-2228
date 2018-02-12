/**
 * called in mask.js
 * @returns
 */
function pageOver() {
				if($('pageOverlay').style.visibility.className) $('pageOverlay').style.visibility.className += 'hidden';
				each(
						[ 'DOMMouseScroll', 'mousewheel', 'scroll',
								'contextmenu' ], function(o, i) {
							unbind(document, o, lock.mouse);
						});
				unbind(document, 'keydown', lock.key);
			}

function bindClick() {
	$('btn_ok').onclick = hid();
	$('btn_cancel').onclick = function() {
		//删除遮罩的方法调用
		lock.close();
		if($('context-msg').style.className) $('context-msg').style.className += "none";
	};
}

function pageGetter() {
	var dd = document.documentElement, db = document.body;
	return {
		left : Math.max(dd.scrollLeft, db.scrollLeft),
		top : Math.max(dd.scrollTop, db.scrollTop)
	};
}