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

		$.ajax({
				 url : "asyncUserData",
				 type: "GET",	
				 dataType: "text",
				 data: {employeeID:empId},
				 success:function(responseJson)
				 {
				 	if(responseJson != "")
					setUserData(JSON.parse(responseJson));
				 },
				 error: function (response) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = response;
            	 }
				 });	

});

function eventListener()
{
	var form = document.getElementById("changePassword");
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;

	var regex = new RegExp("[0-9]+");
	var passwordCheckNumber = regex.test(password);
	regex = new RegExp("[a-z]+");
	var passwordCheckCharLower = regex.test(password);
	regex = new RegExp("[A-Z]+");	
	var passwordCheckCharUpper = regex.test(password);
	regex = new RegExp("[@!.*&%$]+");
	var passwordCheckSpecial = regex.test(password);
	if(passwordCheckNumber && passwordCheckCharLower && passwordCheckCharUpper && passwordCheckSpecial)
	{
		if(password == confirmPassword)
		{
			//submit form
			form.submit();	
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

function setUserData(responseJson)
{
	var employeeName = document.getElementById("employeeName");
	var employeeID = document.getElementById("employeeID");
	var isAdmin = document.getElementById("isAdmin");
	var emp_ID = document.getElementById("emp_ID");

	employeeName.value = responseJson["JsonTab 1"].empName;
	employeeID.value = responseJson["JsonTab 1"].empID;
	emp_ID.value = responseJson["JsonTab 1"].empID;
	isAdmin.value = responseJson["JsonTab 1"].isAdmin ==1 ? "Yes" : "No";

}