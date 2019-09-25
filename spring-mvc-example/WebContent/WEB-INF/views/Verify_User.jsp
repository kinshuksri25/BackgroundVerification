<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify-User</title>
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/VerifyRequest.css" />" rel="stylesheet">
    <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<!--NavBar-->
			<nav class="navbar navbar-default" role="navigation">
		  <!-- Brand and toggle get grouped for better mobile display -->
		  <div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		      <span class="sr-only">Toggle navigation</span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="menu">BGV</a>
		  </div>

		  <!-- Collect the nav links, forms, and other content for toggling -->
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href="contact-us">Contact Us</a></li>
			  <li class="dropdown">
	          <a href="#" class="dropdown-toggle" id="username" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="logout">Logout</a></li>
	          </ul>
	        </li>
		    </ul>
		  </div><!-- /.navbar-collapse -->
		</nav>
 		<!--NavBar-->

 		<div class="formContainer">
 			<div class="heading">
 				<h3>Verify Request</h3>	
 			</div>	
		     <form method="POST" action="createBGVRequest" enctype="multipart/form-data" id="requestForm" onsubmit="SubmitForm()">
	              <div class="form-group">	
	                  <label class="label" for="requestID">RequestID</label>
	                  <input type="text" name="requestID" id="requestID" class="form-control" disabled/>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="status">Status</label>
	                  <input type="text" name="status" id="status" class="form-control" disabled/>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="employeeID">EmployeeID</label>
	                  <input type="text" name="employeeDetails.employeeID" id="employeeID" class="form-control" placeholder="EmployeeID" disabled onchange="fetchEmployeeDetails(this.value)" required/>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="employeeName">EmployeeName</label>
	                  <input type="text" name="employeeDetails.employeeName" id="employeeName" class="form-control employeeDetailFill" placeholder="EmployeeName" disabled="true"/>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="companyName">CompanyName</label>
	                  <input type="text" name="employeeDetails.companyName" id="companyName" class="form-control employeeDetailFill" placeholder="CompanyName" disabled="true"/>
	              </div>
	               <div class="form-group">	
	                  <label class="label" for="businessVertical">BusinessVertical</label>
						<select id="businessVertical" name="businessVertical" onClick="generateAccountName(this.value)" class="form-control" disabled required> 
						</select>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="accountName">AccountName</label>
						<select id="accountName" name="accountName" class="form-control" disabled required>
						</select>
	              </div>
	               <div class="form-group">	
	                  <label class="label" for="dateOfSubmission">DateOfSubmission</label>
	                  <input type="date" name="dateOfSubmission" id="dateOfSubmission" class="form-control" placeholder="DateOfSubmission" disabled/>
	              </div>
	              <div class="form-group">	
	                  <label class="label" for="emailID">Email</label>
	                  <input type="text" name="employeeDetails.emailID" id="emailID" class="form-control" placeholder="Email" disabled="true"/>
	              </div>
	              <div class="form-group">
	            	 <label class="label" for="supportingDocumentLocation">DocumentLocation</label>
	                 <input type = "text" class="form-control" name="fileLocation" id="supportingDocumentLocation" disabled required/>
	          	  </div>
	              <div class="form-group">	
	                  <label class="label" for="assignedReviewer">AssignedReviewer</label>
	                  <input type = "text" class="form-control" name="assignedReviewer" id="assignedReviewer" disabled/>
	              </div>
	              <div class="form-group" id="commentsDiv">
	              	   <label class="label" for="comments">ReviewerComments</label>
	              	   <textarea id="comments" class="comments form-control" name="comments" rows="5" disabled="true"></textarea>
	              </div>
	                <input type = "text" name="requestCreator" id="requestCreator" hidden/>
	              	<input type = "text" name="requestCreatorID" id="requestCreatorID" hidden/>
	              	<input type = "text" name="assignedReviewerID" id="assignedReviewerID" hidden/>
            </form>	
             <div class="row">
		              <div class="form-group" id="buttonDiv">	
		              </div>
	         </div>
	    </div>     	
 	</div>
<!--<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>-->
<script src="<c:url value= "resources/dist/jQuery.js"/>" crossorigin="anonymous"></script>
<script src="<c:url value= "resources/dist/bootstrap-3.3.7-dist/js/bootstrap.js"/>" crossorigin="anonymous"></script>
<script src="<c:url value= "resources/VerifyRequest.js" />" crossorigin="anonymous"></script>
</body>
</html>