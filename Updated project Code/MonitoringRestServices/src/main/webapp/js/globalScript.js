var loggedUserID = null;
var loggedUserFlag = null;

/*function invokeEventsInterval() {
	
	if($('#fetchQueueEventsData').is(':visible')) {
		if(applicationId != "") {
			getQueueEventList();
		}
	}
	
	if($('#fetchAllQueueManagerData').is(':visible')) {
		if(QueueManagerId != "") {
			getQMQueueEventList();
		}
	}
	
}

$(document).ready(function() {
	setInterval(function() {invokeEventsInterval();},60000);
});*/
function getHeadersData(){
	var headerData = {
			"userid" : loggedUserID
	};
	return headerData;
};
/** Delete **/ 
function deleteIt(){
	console.log("Before Alert");
	 if (confirm("Are you sure you want to Delete?") == true) {
	       console.log("You pressed Yes");
	       return true;
	    } else {
	    	console.log("You pressed No");
	    	return false;
	    }  
	console.log("After Alert");
} 


