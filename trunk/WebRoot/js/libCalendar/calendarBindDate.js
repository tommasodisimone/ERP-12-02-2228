/**
 * called in Calendar.js
 * @param date
 * @returns
 */
function calendarBindDate(date)
{
	document.writeln("<script type='text/javascript' src='forCalBindDate.js'></script>");	

	var _monthDays = new Array(31,30,31,30,31,30,31,31,30,31,30,31);	
	var _arr = date.split('-');		
	var _date = new Date(_arr[0],_arr[1]-1,_arr[2]);	
	if(isNaN(_date)) _date = new Date();	
	this.date = _date;
	this.bindHeader();	
	var _year = _date.getFullYear(); var _month = _date.getMonth(); var _day = 1;	
	var _startDay = new Date(_year,_month,1).getDay();

	var _previYear;

	if(_month==0){
		_previYear=_year-1;
	}else{
		_previYear=_year;
	}

	var _previMonth;

	if(_month==0){
		_previMonth=11;
	}else{
		_previMonth=_month-1;
	}
 
	var _previDay = _monthDays[_previMonth];

	//if (_previMonth == 1)_previDay =((_previYear%4==0)&&(_previYear%100!=0)||(_previYear%400==0))?29:28;	
	//_previDay -= _startDay - 1;

	if(_previMonth == 1){
		if((((_previYear%4==0)&&(_previYear%100!=0))||(_previYear%400==0))){
			_previDay =29;
		}
	}else{
		_previDay =28;
	}
	_previDay -= _startDay - 1;

	var _nextDay = 1;

	if((_year%4==0)&&(_year%100!=0)||(_year%400==0)){
		_monthDays[1]=29;
	}else{
		_monthDays[1]=28;
	}
	var _previDate, _nextDate, _curDate, i=0, temp=new Date(_year,_month,1).getDay() && _day <= _monthDays[_month];
	
	
	forCalBindDate(_startDay, _monthDays);
	
	
	var _menu = getObjById("cdrMenu");
	_menu.style.display = "none";	
}