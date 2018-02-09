/**
 * called in mask.js
 * @param e
 * @returns
 */
function preventDefault(e) {
	if(e.preventDefault){
		e.preventDefault()
	}else{
		e.returnValue = false;
	}
			
		}