<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inbox</title>
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/Inbox.css" />" rel="stylesheet">
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
 		<div class="tableContainer">
 			<div class="heading">
 				<h3>Inbox</h3>	
 			</div>	
			<div class="row" id="created" hidden>
				<label class="label" for="created">SubmittedByYou</label>	
				<table class="table" id="submitted">
				  <thead>
				    <tr class = "headRow">
				      <th scope="col">RequestID</th>
				      <th scope="col">EmployeeID</th>
				      <th scope="col">EmployeeName</th>
				      <th scope="col">ReviewerName</th>
				    </tr>
				  </thead>
				  <tbody>
				  </tbody>
				</table>
			</div>
			<div class="row " id="assigned">
				<div class="row" hidden>
					<label class="label" for="pendingForReview">PendingForReview</label>	
					<table class="table" id="pendingForReview">
					  <thead>
					    <tr class = "headRow">
					      <th scope="col">RequestID</th>
					      <th scope="col">EmployeeID</th>
					      <th scope="col">EmployeeName</th>
					      <th scope="col">RequestCreator</th>
					    </tr>
					</thead>
					  <tbody>
					  </tbody>
					</table>
				</div>
				<div class="row" hidden>
					<label class="label" for="approved">Approved</label>	
					<table class="table" id="approved">
					  <thead>
					    <tr class = "headRow">
					      <th scope="col">RequestID</th>
					      <th scope="col">EmployeeID</th>
					      <th scope="col">EmployeeName</th>
					      <th scope="col">RequestCreator</th>
					    </tr>
					  </thead>
					  <tbody>
					  </tbody>
					</table>
				</div>
				<div class="row" hidden>
					<label class="label" for="rejected">Rejected</label>	
					<table class="table" id="rejected">
					  <thead>
					    <tr class = "headRow">
					      <th scope="col">RequestID</th>
					      <th scope="col">EmployeeID</th>
					      <th scope="col">EmployeeName</th>
					      <th scope="col">RequestCreator</th>
					    </tr>
					  </thead>
					  <tbody>
					  </tbody>
					</table>
				</div>
				<div class="row" hidden>
					<label class="label" for="pendingForCorrection">PendingForCorrection</label>
					<table class="table" id="pendingForCorrection">
					  <thead>
					    <tr class = "headRow">
					      <th scope="col">RequestID</th>
					      <th scope="col">EmployeeID</th>
					      <th scope="col">EmployeeName</th>
					      <th scope="col">RequestCreator</th>
					    </tr>
					  </thead>
					  <tbody>
					  </tbody>
					</table>
				</div>
			</div>
			<div class="row" id="message">
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
<script src="<c:url value= "resources/Inbox.js" />" crossorigin="anonymous"></script>
</body>
</html>