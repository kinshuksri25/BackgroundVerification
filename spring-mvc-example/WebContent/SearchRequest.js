var jsonResponse; 
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
	 	username = name.charAt(0).toUpperCase() + name.slice(1);

	 	var logoutTab  = document.getElementById("username");
		logoutTab.innerHTML = "Welcome "+username;
	});

function searchRequest()
{
	var searchField = document.getElementById("searchField").value;
	var searchCriteria = document.getElementById("searchCriteria").value;
	if(searchField != "")
	{
		if(searchCriteria != "Title")
		$.ajax({
			    type: 'get',
			    dataType: 'text',
			    url: 'asyncSearchRequest',
			    data:{searchFeildValue:searchField,
			    	 criteria : searchCriteria},
			    success: function(responseString) 
			    	{
			    		buildElement(responseString);
			    	},
				});	
		else
			alert("Search Criteria should not be empty");
	}
	else
		alert("Search Request field should not be empty");
}

function buildElement(responseString)
{
	var content = document.getElementById("content");
	if(content.children.length!=0)
			{	
				var li = content.lastElementChild;  
		       	while (li) 
		       	{ 
				    content.removeChild(li); 
				    li = content.lastElementChild; 
		        } 
	        }
	if(responseString != "")
	{
		buildTable(responseString,content);
	}
	else
		buildError(content);	
}

function buildError(content)
{
	var paragraph = document.createElement("P");
	paragraph.innerHTML = "No Data Found";
	paragraph.setAttribute("id","errorMsg");
	content.appendChild(paragraph);
}

function buildTable(responseString,content)
{
	var table =  document.createElement("table");
	var thead = document.createElement("thead");
	var tbody = document.createElement("tbody");
    var headRow = document.createElement("tr");

    table.className="table table-dark";
    thead.className="thead-dark";
    ["RequestID","EmployeeID","EmployeeName","ReviewerName","RequestCreator"].forEach(function(el) {
      var th=document.createElement("th");
      th.scope="col";
      th.appendChild(document.createTextNode(el));
      headRow.appendChild(th);
    });
    headRow.className="headRow";
    thead.appendChild(headRow);
    table.appendChild(thead); 

   jsonResponse = JSON.parse(responseString);
   for(var jsonObject in jsonResponse)
   {
   		var jsonRow = jsonResponse[jsonObject];
   		var tr = document.createElement("tr");
   		tr.className="rowTile"
   		tr.onclick = function (event)
					{
						var id = event.target.id;
						for(var jsonObject in jsonResponse)
						{
							var jsn = jsonResponse[jsonObject];
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
   		tr.id = jsonRow.requestID;
		[jsonRow.requestID,jsonRow.employeeDetails.employeeID,jsonRow.employeeDetails.employeeName,jsonRow.assignedReviewer,jsonRow.requestCreator].forEach(function(el) {
      	var td=document.createElement("td");
      	td.id = jsonRow.requestID;
      	td.appendChild(document.createTextNode(el));
      	tr.appendChild(td);
    	});
    	tbody.appendChild(tr);

   }
   table.appendChild(tbody);
   content.appendChild(table);
}

function redirect(urlMapping)
{
	var currentLocation =  window.location.href;
	var location = currentLocation.lastIndexOf('/');
	currentLocation = currentLocation.substring(0,location+1);	

	window.location = currentLocation+urlMapping;
}



