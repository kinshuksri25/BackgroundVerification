document.addEventListener('DOMContentLoaded', function(){
 	var cookieString = document.cookie;
 	var cookie;
 	var uname;
 	var cookies = cookieString.split(';');
 	
		cookies.map(function(currentcookie){ 
 			if(currentcookie.includes('EmployeeID'))
 				cookie = currentcookie;
 			else if(currentcookie.includes('EMPNAME'))
 				uname = currentcookie;
			});

 	var id_position = cookie.indexOf("=");
 	var name_position = uname.indexOf("=");
 	var employeeID = cookie.substring(id_position+1);
 	var name = uname.substring(name_position+1);
 	name = name.charAt(0).toUpperCase() + name.slice(1);
 	getMenuJson(employeeID);


function getMenuJson(employeeID)
						{
						  $.ajax({
				                url : "async_menu_details",
				                data: {employee_ID:employeeID},
				                type: "GET",
				                contentType: "application/json",
				                dataType: "text",
				                success:function(response)
				                   {
				                    var responseJson = JSON.parse(response);	
				                    console.log(responseJson);			                
				                   	generateTab(responseJson); 
				                   },
				                error: function (response) {
                						console.log(response);
            						}
				                });
						}

function generateTab(json)
{
	var tabContainer = document.getElementById("tabContainer");
	for(var jsonObject in json)
	{
		var tab = document.createElement("DIV");
		var icon = document.createElement("I");
		var anchortag = document.createElement("A");

		var tabName = json[jsonObject].tabName;
		var urlMapping =  json[jsonObject].urlmapping;
		
		tab.setAttribute('class', 'tab col-lg-4 col-md-4 col-sm-6 col-xs-12');
		icon.setAttribute('class','col-lg-12');
		icon = setIcon(icon,tabName);
		icon.setAttribute('aria-hidden','true');	
		anchortag.setAttribute('class','anchor col-lg-12');
		anchortag.innerHTML = tabName;
		anchortag.href = urlMapping;

		tab.onclick = function(event){
			var location = window.location.toString();
			var urlMapping =event.srcElement.children[1].attributes.href.nodeValue;
			location = location.substring(0,location.lastIndexOf("/")+1);
			window.location = location+urlMapping;
		};
		tab.appendChild(icon);
		tab.appendChild(anchortag);
		tabContainer.appendChild(tab); 

		var logoutTab  = document.getElementById("username");
		logoutTab.innerHTML = "Welcome "+name;
	}
}
var setIcon = function(icon,tabName)
{
	switch(tabName)
	{
		case "Create-User" : icon.setAttribute('class','fa fa-5x fa-plus');
							 break;
		case "User-Profile" : icon.setAttribute('class','fa fa-5x fa-user');
							 break;
		case "Create-Request" : icon.setAttribute('class','fa fa-5x fa-share');
							 break;
		case "Search-Request" : icon.setAttribute('class','fa fa-5x fa-search');
							 break;
		case "Inbox" : icon.setAttribute('class','fa fa-5x fa-inbox');
							 break;							 							 							 							  	
	}	
	return icon;
};

});