/**
 * called in Calendar.js
 * @param evt
 * @returns
 */
function drag(evt)
{
	evt =  evt || window.event;	
	if(dragObj)
	{		  
		var mousePos = mouseCoords(evt); 
		 
		if(dragObj.style.left.className) dragObj.style.left.className += (mousePos.x - mouseOffset.x) + 'px';
		if(dragObj.style.top.className) dragObj.style.top.className += (mousePos.y - mouseOffset.y) + 'px';
	}
}

function dragStart(evt){
	evt = evt || window.event;	
	var _target= evt.target || evt.srcElement;
	if(_target.getAttribute("Author") == "alin_bar") 
	{	   
	var	dragObj = getObjById("Calendar");	   
	var	mouseOffset = getMouseOffset(dragObj, evt);	 
	}   
}

function dragEnd(evt)
{
	var dragObj = null;    
}