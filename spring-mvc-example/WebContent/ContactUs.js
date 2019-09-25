var requestJson =  sessionStorage.getItem("requestJson");	
requestJson = JSON.parse(requestJson);
document.addEventListener('DOMContentLoaded',function()
{	
	var username;
	var empId;
	var cookieString = document.cookie;
	var cookies = cookieString.split(';');

	cookies.map(function(currentcookie){
	 	if(currentcookie.includes('EmployeeID'))
	 		empId = currentcookie;
	 	if(currentcookie.includes('EMPNAME'))
	 		username = currentcookie;
			});

	var id_position = empId.indexOf("=");
	empId = empId.substring(id_position+1);
	
	var name_position = username.indexOf("=");
	var name = username.substring(name_position+1);
	username = name.charAt(0).toUpperCase() + name.slice(1);

	var logoutTab  = document.getElementById("username");
	logoutTab.innerHTML = "Welcome "+username;
});	