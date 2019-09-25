var username;
var empId;
document.addEventListener('DOMContentLoaded',function()
	{
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
		currentUser = name;
	 	username = name.charAt(0).toUpperCase() + name.slice(1);

	 	var logoutTab  = document.getElementById("username");
		logoutTab.innerHTML = "Welcome "+username;

		$.ajax({
				 url : "asyncSearchRequest",
				 type: "GET",	
				 dataType: "text",
				 data:{searchFeildValue:empId,
			    	 criteria : "CreatorID"},
				 success:function(responseJson)
				 {
					responseJson = responseJson != "" ?JSON.parse(responseJson): "";
				 	populateTable(responseJson,"created");
				 },
				 error: function (responseJson) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = responseJson;
            	 }
				 });

		$.ajax({
				 url : "asyncSearchRequest",
				 type: "GET",	
				 dataType: "text",
				 data:{searchFeildValue:empId,
			    	 criteria : "ReviewerID"},
				 success:function(responseJson)
				 {
				 	responseJson = responseJson != "" ?JSON.parse(responseJson): "";
				 	populateTable(responseJson,"assigned");
				 },
				 error: function (responseJson) 
				 {
          			var message = document.getElementById("message");
          			message.innerHTML = responseJson;
            	 }
				 });

	}
	);


function populateTable(responseJson,criteria)
{
	switch(criteria)
	{
		case "created": populateTableCreated(responseJson);
			break;
		case "assigned": populateTableAssigned(responseJson);
			break;
	}
}


function populateTableCreated(responseJson)
{
	if(responseJson !="")
	{
		var createdTable = document.getElementById("created");
		var creatorTable = document.getElementById("submitted");
		var childNodes = creatorTable.childNodes;
		var creatorTableBody=childNodes[3];
		for(var response in responseJson)
		{
			var creatorTableBodyRow = document.createElement("tr");	
			creatorTableBodyRow.className="rowTile"
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			var td4 = document.createElement("td");
			td4.id=td3.id=td2.id=td1.id=creatorTableBodyRow.id = responseJson[response].requestID;
			td1.innerHTML = responseJson[response].requestID;
			td2.innerHTML = responseJson[response].employeeDetails.employeeID;
			td3.innerHTML = responseJson[response].employeeDetails.employeeName;
			td4.innerHTML = responseJson[response].assignedReviewer;
			creatorTableBodyRow.onclick = function (event)
					{
						var id = event.target.id;
						for(var response in responseJson)
						{
							var jsn = responseJson[response];
							if(id == jsn.requestID)
							{
								sessionStorage.clear();
								sessionStorage.setItem('requestJson', JSON.stringify(jsn));
								console.log("data persisted");
								var urlMapping = "verify-user"
								redirect(urlMapping);
							}
						}	
					}
			creatorTableBodyRow.appendChild(td1);
			creatorTableBodyRow.appendChild(td2);
			creatorTableBodyRow.appendChild(td3);
			creatorTableBodyRow.appendChild(td4);
			creatorTableBody.appendChild(creatorTableBodyRow);

		}
		createdTable.hidden = false;
	}
}

function populateTableAssigned(responseJson)
{
	if(responseJson !="")
	{
		for(var response in responseJson)
		{
			var selectedTable;

			switch(responseJson[response].status)
			{
				case "PENDING_APPROVAL":
						selectedTable = document.getElementById("pendingForReview");	
						break;
				case "APPROVED":
						selectedTable = document.getElementById("approved");
						break;	
				case "PENDING_CORRECTION":
						selectedTable = document.getElementById("pendingForCorrection");
						break;	
				case "REJECTED":
						selectedTable = document.getElementById("rejected");
						break;
			}
			var childNodes = selectedTable.childNodes;
			var creatorTableBody=childNodes[3];
			var creatorTableBodyRow = document.createElement("tr");	
			creatorTableBodyRow.className="rowTile"
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				td4.id=td3.id=td2.id=td1.id=creatorTableBodyRow.id = responseJson[response].requestID;
				td1.innerHTML = responseJson[response].requestID;
				td2.innerHTML = responseJson[response].employeeDetails.employeeID;
				td3.innerHTML = responseJson[response].employeeDetails.employeeName;
				td4.innerHTML = responseJson[response].requestCreator;
				creatorTableBodyRow.onclick = function (event)
						{
							var id = event.target.id;
							for(var response in responseJson)
							{
								var jsn = responseJson[response];
								if(id == jsn.requestID)
								{
									sessionStorage.clear();
									sessionStorage.setItem('requestJson', JSON.stringify(jsn));
									console.log("data persisted");
									var urlMapping = "verify-user"
									redirect(urlMapping);
								}
							}	
						}
				creatorTableBodyRow.appendChild(td1);
				creatorTableBodyRow.appendChild(td2);
				creatorTableBodyRow.appendChild(td3);
				creatorTableBodyRow.appendChild(td4);
				creatorTableBody.appendChild(creatorTableBodyRow);
				selectedTable.parentElement.hidden=false;
		}
	}
}

function redirect(urlMapping)
{
	var currentLocation =  window.location.href;
	var location = currentLocation.lastIndexOf('/');
	currentLocation = currentLocation.substring(0,location+1);	

	window.location = currentLocation+urlMapping;
}
