var username;
document.addEventListener('DOMContentLoaded', function(){
var cookieString = document.cookie;
	var cookies = cookieString.split(';');
	cookies.map(function(currentcookie){
 			if(currentcookie.includes('EMPNAME'))
 				uname = currentcookie;
			});

	var name_position = uname.indexOf("=");
	var name = uname.substring(name_position+1);
 	username = name.charAt(0).toUpperCase() + name.slice(1);

 	var logoutTab  = document.getElementById("username");
	logoutTab.innerHTML = "Welcome "+username;
});


function eventListener(){
	var json ={};
	json.employeeName = document.getElementById("employeeName").value;
	json.employeeID = document.getElementById("employeeID").value;
	json.password = document.getElementById("password").value;
	json.confirmPassword = document.getElementById("confirmPassword").value;
	json.isAdmin = document.getElementById("Yes").checked ? document.getElementById("Yes").value : document.getElementById("No").value; 
	
 	json.createName = username;
	checkInputForm(json);
}

function checkInputForm(employeeJson)
{
	//employeeID check
	var employeeID = employeeJson.employeeID;
	var regex = new RegExp("[a-zA-z!@#$%^&*()_+=-]+");
	var employeeidCheck = regex.test(employeeID) ? false : true;
	if((employeeID.length >= 6 && employeeID.length <= 9)  && employeeidCheck)
	{
		passwordCheck(employeeJson);
	}
	else
	{
		// please enter the correct empid format
		responseMesssage("Invalid Employee ID");
	}

}

function passwordCheck(employeeJson)
{
	var regex = new RegExp("[0-9]+");
	var passwordCheckNumber = regex.test(employeeJson.password);
	regex = new RegExp("[a-z]+");
	var passwordCheckCharLower = regex.test(employeeJson.password);
	regex = new RegExp("[A-Z]+");	
	var passwordCheckCharUpper = regex.test(employeeJson.password);
	regex = new RegExp("[@!.*&%$]+");
	var passwordCheckSpecial = regex.test(employeeJson.password);
	if(passwordCheckNumber && passwordCheckCharLower && passwordCheckCharUpper && passwordCheckSpecial)
	{
		if(employeeJson.password == employeeJson.confirmPassword)
		{
			asyncCreateUser(employeeJson);
		}
		else
		{
			//password and confirm password is not correct
		responseMesssage("Confirm Password is not the same as Password");
		}
	}
	else
	{
		//please enter the correct form of password
		responseMesssage("Invalid password pattern");
	}
}

function asyncCreateUser(employeeJson)
{
	employeeJson = JSON.stringify(employeeJson);
	$.ajax({
    type: 'post',
    dataType: 'text',
    url: 'asyncCreateUser',
    data: {RequestJson:employeeJson},
    success: function(responseString) 
    	{
    		var elements = document.getElementsByTagName("input");
			for (var element=0; element < elements.length; element++) {
			  if (elements[element].type == "text" || elements[element].type == "password") {
			    elements[element].value = "";
			  }
			  else if(elements[element].type == "radio" && elements[element].value == "true")
			  	elements[element].checked = true;
			}
    		responseMesssage(responseString);
    	},
	});
}

function responseMesssage(responseString)
{
	var messageDiv = document.getElementById("message");
	var msg = document.getElementById("msg");
	if(msg!=null)
	msg.parentNode.removeChild(msg);

	var message = document.createElement("P");
	message.setAttribute('id', 'msg');
	message.innerHTML = responseString;
	messageDiv.appendChild(message);
}	