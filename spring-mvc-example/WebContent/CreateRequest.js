var json = "";
var reviewrs ="";
var listReviwers="";
var currentReviewer="";
var currentUser;
document.addEventListener('DOMContentLoaded',function()
	{
		var username;
		var cookieString = document.cookie;
		var cookies = cookieString.split(';');
		cookies.map(function(currentcookie){
	 			if(currentcookie.includes('EMPNAME'))
	 				uname = currentcookie;
				});

		var name_position = uname.indexOf("=");
		var name = uname.substring(name_position+1);
		currentUser = name;
	 	username = name.charAt(0).toUpperCase() + name.slice(1);

	 	var logoutTab  = document.getElementById("username");
		logoutTab.innerHTML = "Welcome "+username;

		setRequestCreator();
		$.ajax({
				 url : "asyncBusinessVerticalDetails",
				 type: "POST",	
				 dataType: "text",
				 success:function(responseJson)
				 {
					generateBusinessVertical(responseJson);
				 },
				 error: function (response) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = response;
            	 }
				 });

		$.ajax({
				 url : "asyncEmployeeData",
				 type: "GET",	
				 dataType: "text",
				 success:function(responseJson)
				 {
				 	reviewrs = JSON.parse(responseJson);
					generateReviewers(reviewrs);
				 },
				 error: function (response) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = response;
            	 }
				 });

	});

function generateBusinessVertical(responseJson)
{
	responseJson = JSON.parse(responseJson);
	json = responseJson;
	var dropDownContainer = document.getElementById("businessVertical");
	for(var jsonObject in responseJson)
	{
		var option = document.createElement("OPTION");
		var name = responseJson[jsonObject].verticalName;

		option.innerHTML = name;
		option.setAttribute('id',name);
		option.value = name;

		dropDownContainer.appendChild(option);
	}	
}


function generateReviewers(responseJson)
{
	var listOfReviewers = document.getElementById("listOfReviewers");
	if(listOfReviewers.children.length!=0)
		{	
			var li = listOfReviewers.lastElementChild;  
	       	while (li) 
	       	{ 
		    listOfReviewers.removeChild(li); 
		    li = listOfReviewers.lastElementChild; 
	        } 
        }
	for(var reviewer in responseJson)
	{
		if(responseJson[reviewer].empName != currentUser)
		{
		var li = document.createElement("LI");
		li.innerHTML = responseJson[reviewer].empName;
		li.setAttribute("id",responseJson[reviewer].empID);
		li.setAttribute("class","addToCurrent");
		li.onclick = changeReviwer;
		listOfReviewers.appendChild(li);
		}
	}
}

function changeReviwer(event)
{
	currentReviewer = event.target;
	operation = currentReviewer.className == "addToCurrent"? "delete" : "add";
	generateCurrentReviwer(currentReviewer);
	listReviwers = updateJson(reviewrs,event.target.id,operation);
	generateReviewers(listReviwers);
}


function updateJson(review,id,operation)
{
	var String = JSON.stringify(review);
	var json = JSON.parse(String);
	if(operation == "delete")
	{
		for(var data in json)
		{
			if(json[data].empID == id)
			{
				//delete data
				delete json[data];
			}
		}
		return json;
	}
	else if(operation == "add")	
	{
		var currentReviewer = document.getElementById("currentReviewer");
		if(currentReviewer.children.length!=0)
		{	
			var li = currentReviewer.lastElementChild;  
	       	while (li) 
	       	{ 
		    currentReviewer.removeChild(li); 
		    li = currentReviewer.lastElementChild; 
	        } 
        }
		return json;
	}
}

function generateCurrentReviwer(reviewer)
{
	//clear the data
	var currentReviewer = document.getElementById("currentReviewer");
	var assignedReviewer = document.getElementById("assignedReviewer");
	var assignedReviewerID = document.getElementById("assignedReviewerID");

	assignedReviewerID.value = reviewer.getAttribute("id");
	assignedReviewer.value = reviewer.innerHTML;
	if(currentReviewer.children.length!=0)
		{	
			var li = currentReviewer.lastElementChild;  
	       	while (li) 
	       	{ 
		    currentReviewer.removeChild(li); 
		    li = currentReviewer.lastElementChild; 
	        } 
        }
    reviewer.className = "RemoveFromReviewer";    
    reviewer.onclick =changeReviwer;
	currentReviewer.appendChild(reviewer);
}

function generateAccountName(businessVerticalName)
{
	var accdropDownContainer = document.getElementById("accountName");
	var verticalJson;
	try
	{
		for(var jsonObject in json)
		{
			if(businessVerticalName == json[jsonObject].verticalName)
			{
				verticalJson = json[jsonObject].accountName;
				 break;
			}
		}
		if(accdropDownContainer.children.length!=0)
			{	
				var verticalOptions = accdropDownContainer.lastElementChild;  
	       		while (verticalOptions) 
	       		{ 
		            accdropDownContainer.removeChild(verticalOptions); 
		            verticalOptions = accdropDownContainer.lastElementChild; 
	        	} 
        	}
		for(var jsonObject in verticalJson)
		{
			var option = document.createElement("OPTION");
			var name = verticalJson[jsonObject].accountName;

			option.innerHTML = name;
			option.setAttribute('class','vertical');
			option.setAttribute('id',name);
			option.value = name;

			accdropDownContainer.appendChild(option);
		}	
	}
	catch(err)
	{
		console.log(err);
	}
}


function fetchEmployeeDetails(event) 
{
	var employeeID = event;	
	$.ajax({
				 url : "asyncEmployeeDetails",
				 type: "get",
				 data: {requestJson:employeeID},
				 dataType: 'text',
				 success:function(responseJson)
				 {
				 	console.log(responseJson);
				 	if(responseJson != "")
					fillEmployeeDetails(responseJson);
					else
					alert("incorrect Employee ID");		
				 },
				 error: function (responseJson) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = responseJson;
            	 }
				 });
}

function fillEmployeeDetails(responseJson)
{
	responseJson = JSON.parse(responseJson);

	var employeeName = document.getElementById("employeeName");
	var companyName = document.getElementById("companyName");
	var emailID = document.getElementById("emailID");
	var employeeJson = responseJson["JsonTab 1"];

	employeeName.value = employeeJson.employeeName;
	companyName.value = employeeJson.companyName;
	emailID.value = employeeJson.emailID;
}

function setRequestCreator()
{
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

 	var requestCreator = document.getElementById("requestCreator");
 	var requestCreatorID = document.getElementById("requestCreatorID");

 	requestCreator.value = name;
 	requestCreatorID.value = employeeID;
 }

function checkDate()
{
 	var selectedDate = new Date(document.getElementById("dateOfSubmission").value);
 	selectedDate = selectedDate.getFullYear()+"-"+selectedDate.getMonth()+"-"+selectedDate.getDate();
 	var form = document.getElementById("requestForm");
 	var currentDate = new Date();
	currentDate = currentDate.getFullYear()+"-"+currentDate.getMonth()+"-"+currentDate.getDate();
	if(currentDate != selectedDate)
	{
		alert("Please enter the current date as the DateOfSubmission");
		return false;
	}
	else
	{
		return true;
	}
}