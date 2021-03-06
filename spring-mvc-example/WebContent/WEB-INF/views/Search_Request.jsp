<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search-Request</title>
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/SearchRequest.css" />" rel="stylesheet">
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
 		<div class=" forms row">
 			<h2 class="heading">Search For A Request</h2>
 			<div class="form-group">	
 				<div class="formSection">
	 				<label class="label" for="searchCriteria">Search Criteria</label>
					<select id="searchCriteria" class="form-control">
						<option value="Title">Select Search Criteria</option>
						<option value="EmployeeID">EmployeeID</option>
						<option value="ReviewerID">ReviewerID</option>
						<option value="RequestID">RequestID</option>
					</select>
				</div>
				<div class="formSection">	
			       	<label class="label" for="searchField">Search Request</label>
		 			<input class="form-control" type="text" id="searchField" placeholder="Search"/>
	 			</div>
	 		</div>
	 		<!--remove submit button-->
	 		<input type="submit" class="btn btn-lg btn-primary button" onclick="searchRequest()" />
 		</div>

 		<div class="row">
 			<div id="content">
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
<script src="<c:url value= "resources/SearchRequest.js" />" crossorigin="anonymous"></script>
</body>
</html> 		