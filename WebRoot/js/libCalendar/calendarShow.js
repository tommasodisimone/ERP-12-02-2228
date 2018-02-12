/**
 * called in Calendar.js
 * @returns
 */
function calendarShow()
{
	if (arguments.length >  3  || arguments.length == 0)
	{
		alert("对不起！传入参数不对！" );
		return;
	}   
	var _date = null;
	var _evObj = null;
	var _initValue = null; 
	var i = 0; 
	for(i = 0; i < arguments.length; i++)
	{
		if(typeof(arguments[i]) == "object" && arguments[i].type == "text")
		{_date = arguments[i];}
		else if(typeof(arguments[i]) == "object")
		{_evObj = arguments[i];}
		else if(typeof(arguments[i]) == "string")
		{_initValue = arguments[i];}  
	}
	_evObj = _evObj || _date;
	var inputObj = _date;
	var targetObj = _evObj;
	if(!_date){alert("传入参数错误!"); return;}
	this.dateInput = _date;
	_date = _date.value;
	if(_date == "" && _initValue) _date = _initValue;   
	this.bindDate(_date);        
	var _target = getPosition(_evObj);   
	var _obj = getObjById("Calendar");
	
	var objDisplay = _obj.style.display; 
	if(objDisplay.className) objDisplay.className += "";
	
	var objLeft = _obj.style.left; 
	if(objLeft.className) objLeft.className += _target.x + 'px';
	var objTop = _obj.style.top;
	
	if((document.body.clientHeight - (_target.y + _evObj.clientHeight)) >= _obj.clientHeight)
	{        
		if(objTop.className) objTop.className = objTop.className + (_target.y + _evObj.clientHeight) + 'px';
	}
	else
	{	  
		if(objTop.className) objTop.className = objTop.className + (_target.y - _obj.clientHeight) + 'px';
	}
}