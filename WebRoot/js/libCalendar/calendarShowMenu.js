/**
 * called in Calendar.js
 * @param isyear
 * @returns
 */
function calendarShowMenu(isyear)
{

	var _menu = getObjById("cdrMenu");

	if(isyear != null)
	{    
		var _obj;
		if(isyear){
			_obj=getObjById("currentYear");
		}
		else{
			_obj=getObjById("currentMonth");
		}


		if(isyear)
		{
			this.getYearMenu(this.date.getFullYear() - 5);	   
		}
		else
		{
			this.getMonthMenu();	   
		}
		
		if(_menu.style.top.className) _menu.style.top.className += "";
		if(_menu.style.left.className) _menu.style.left.className += _obj.offsetLeft + 'px';
		if(_menu.style.left.className) _menu.style.left.className += _obj.offsetWidth + 'px';
	}
	if (this.timer != null) clearTimeout(this.timer);
	
	
	if(_menu.style.display.className) _menu.style.display.className += "";
}