var json;
var requestJson =  sessionStorage.getItem("requestJson");	
requestJson = JSON.parse(requestJson);
var authColors=["blue"];
document.addEventListener('DOMContentLoaded',function()
{
	var username;
	var empId;
	var reviewerID = requestJson.assignedReviewerID;
	var creatorID = requestJson.requestCreatorID;
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
				 url : "asyncBusinessVerticalDetails",
				 type: "POST",	
				 dataType: "text",
				 success:function(responseJson)
				 {
					generateBusinessVertical(responseJson);
					fillEmployeeDetails(requestJson);
				 },
				 error: function (response) 
				 {
          			console.log(response);
            	 }
				 });	

	if(empId == reviewerID)
	{	
		//create a text box here
		authColors.push("red");
		authColors.push("green");
		authColors.push("lightBlue");
	}

	if(empId == creatorID)
	{
		authColors.push("yellow");
	}

	authColors.forEach(function(color)
		{
			switch(color)
			{
				case "green":
							var buttonDiv = document.getElementById("buttonDiv");
							var button =  document.createElement("button");
							button.className="approve btn btn-lg btn-success button col-xs-3";
							button.innerHTML="Approve";
							button.id="approve";
							button.onclick = function(){Verify("approve");}
							buttonDiv.appendChild(button);
							var comments = document.getElementById("comments");
							comments.disabled=false;
							 break;
				case "red":
							var buttonDiv = document.getElementById("buttonDiv");
							var button =  document.createElement("button");
							button.className="reject btn btn-lg btn-danger button col-xs-3";
							button.innerHTML="Reject";
							button.id="reject";
							button.onclick = function(){Verify("reject");}
							buttonDiv.appendChild(button);
							 break;
				case "blue":
							var inboxForm = document.createElement("form");
							var buttonDiv = document.getElementById("buttonDiv");
							var button =  document.createElement("button");
							button.className="back btn btn-lg btn-primary button col-xs-3";
							button.innerHTML="Back";
							button.id="back";
							inboxForm.setAttribute('method',"get");
							inboxForm.setAttribute('action',document.referrer.substring(document.referrer.lastIndexOf("/")+1));
							inboxForm.appendChild(button);
							buttonDiv.appendChild(inboxForm);
							 break;
				case "yellow":
							var buttonDiv = document.getElementById("buttonDiv");
							var button =  document.createElement("button");
							button.className="edit btn btn-lg btn-warning button col-xs-3";
							button.innerHTML="Edit";
							button.id="edit";
							button.onclick = Edit;
							buttonDiv.appendChild(button);
							 break;	
				case "lightBlue":
							var buttonDiv = document.getElementById("buttonDiv");
							var button =  document.createElement("button");
							button.className="edit btn btn-lg btn-info button col-xs-3";
							button.innerHTML="SendForCorrection";
							button.id="correction";
							button.onclick = function(){Verify("correct");}
							buttonDiv.appendChild(button);
							 break;			 						 							 							 	
			}
		});
	toggleButtons();

});

function toggleButtons()
{
	if(requestJson.status == "APPROVED" || requestJson.status == "REJECTED" || requestJson.status == "PENDING_CORRECTION")
	{
		var buttons = document.getElementsByClassName("button");
		for(var button in buttons)
		{
			if(buttons[button].id != "back")
				buttons[button].style.visibility ='hidden';
			if(requestJson.status == "PENDING_CORRECTION" && buttons[button].id == "edit")	
			{
				buttons[button].style.visibility ='visible';
			}
		}
		var comments = document.getElementById("comments");
			comments.disabled=true;
	}
	if(requestJson.status == "PENDING_APPROVAL")
	{
		var buttons = document.getElementsByClassName("button");
		for(var button in buttons)
		{
			buttons[button].style.visibility ='visible';
			if(buttons[button].id =="edit")
				buttons[button].style.visibility ='hidden';
		}
	}
}

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
	
function Verify(value)
{
	if(value=="correct")
	{
		var comments =  document.getElementById("comments");
		if(comments.value!="")
		{	
			storeUpdatedStatus(value,comments.value);
			requestJson.status = "PENDING_CORRECTION";
			toggleButtons();
		}
		else
		{
			alert("Comments cannot be empty");
		}
	}
	else
	{  
		var comment = value == "approve" ? "APPROVED" : "REJECTED";
		requestJson.status = value == "approve" ?"APPROVED": "REJECTED";
		storeUpdatedStatus(value,comment);
		toggleButtons();
	}	
}

function storeUpdatedStatus(value,comments)
{

		$.ajax({
				 url : "asyncVerifyRequest",
				 type: "GET",
				 data: {status:value,requestID:requestJson.requestID,comments:comments},	
				 dataType: "text",
				 success:function(responseJson)
				 {
				 	console.log(responseJson);
				 },
				 error: function (response) 
				 {
          			console.log(response);
            	 }
				 });
}

function fillEmployeeDetails(requestJson)
{
	var feilds = document.getElementsByClassName("form-control"); 
	for(var feild in feilds)
		{
			switch(feilds[feild].id)
			{
				case "status" : feilds[feild].value=requestJson.status;
						break;
				case "requestID" :  feilds[feild].value=requestJson.requestID;
						 break;
				case "employeeID" : feilds[feild].value=requestJson.employeeDetails.employeeID;
						 break;
				case "employeeName" : feilds[feild].value=requestJson.employeeDetails.employeeName;
						 break;
				case "companyName" : feilds[feild].value=requestJson.employeeDetails.companyName;
						 break;
				case "businessVertical" :  	selectDropDown(requestJson);
						 break;
				case "dateOfSubmission" : feilds[feild].value= convert(requestJson.dateOfSubmission);
						 break;		 
				case "emailID" : feilds[feild].value=requestJson.employeeDetails.emailID;
						 break;
				case "supportingDocumentLocation" : feilds[feild].value=requestJson.fileLocation;
						 break;	
				case "assignedReviewer" : feilds[feild].value=requestJson.assignedReviewer;
						 break;	
				case "comments" : 
									if(requestJson.status=="PENDING_APPROVAL")
									{
										if(authColors.indexOf("green") !=-1)
											feilds[feild].value="";	
										else
											feilds[feild].value="Reviewer comments unavailable as review is pending for this request";				
									}
									else	
									feilds[feild].value=requestJson.comments;
						 break;			 	 	 		 		 		 		 		 		 
			}
		}
}

function convert(str) {
  var date = new Date(str),
    mnth = ("0" + (date.getMonth() + 1)).slice(-2),
    day = ("0" + date.getDate()).slice(-2);
  return [date.getFullYear(), mnth, day].join("-");
}

function selectDropDown(requestJson)
{
	var businessVerticalFeild= document.getElementById("businessVertical");
	var accountName= document.getElementById("accountName");
	setDropDownVal(businessVerticalFeild,requestJson.businessVertical);
	generateAccountName(requestJson.businessVertical);
	setDropDownVal(accountName,requestJson.accountName);
}

function setDropDownVal(feild,name)
{
	var feildChild =  feild.childNodes;
	feildChild.forEach(function(child)
		{
			if(child.id == name)
			child.selected = "selected";
		});
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


function Edit()
{
	var editButton =  document.getElementById("edit");
	editButton.style.visibility ='hidden';
	var buttonDiv = document.getElementById("buttonDiv");
	var form =  document.getElementById("requestForm");

	var submitbutton = document.createElement("button");
	submitbutton.className="submit btn btn-lg btn-primary button col-xs-3";
	submitbutton.innerHTML="Submit";
	submitbutton.id="submit";
	submitbutton.setAttribute("form",form.id);
	buttonDiv.appendChild(submitbutton);

	var feilds = document.getElementsByClassName("form-control"); 
	
	for(var feild in feilds)
		{
			if(feilds[feild].id=="employeeID" || feilds[feild].id=="businessVertical" || feilds[feild].id=="accountName" || feilds[feild].id=="supportingDocumentLocation")
			{
				feilds[feild].disabled = false;
			}
			
		}
}

function SubmitForm()
{
	var form = document.getElementById("requestForm");
	var requestCreator =  document.getElementById("requestCreator");
	var requestCreatorID =  document.getElementById("requestCreatorID");
	var assignedReviewerID =  document.getElementById("assignedReviewerID");
	var comments =  document.getElementById("comments");
	comments.value="";
	requestCreator.value=requestJson.requestCreator;
	requestCreatorID.value=requestJson.requestCreatorID;
	assignedReviewerID.value=requestJson.assignedReviewerID;
	var feilds = document.getElementsByClassName("form-control"); 
	for(var feild in feilds)
		{
			feilds[feild].disabled = false;
		}
	form.submit();
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
				 	if(responseJson != "")
						fillDetails(JSON.parse(responseJson));
					 else
						alert("incorrect Employee ID");		
				 },	
				 error: function (responseJson) 
				 {
          			console.log(responseJson);
            	 }
				 });
}

function fillDetails(responseJson)
{
	var employeeName = document.getElementById("employeeName");
	var companyName = document.getElementById("companyName");
	var emailID = document.getElementById("emailID");
	var employeeJson = responseJson["JsonTab 1"];

	employeeName.value = employeeJson.employeeName;
	companyName.value = employeeJson.companyName;
	emailID.value = employeeJson.emailID;
}