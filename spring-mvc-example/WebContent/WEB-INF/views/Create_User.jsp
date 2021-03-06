<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create-User</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
     <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/CreateUser.css" />" rel="stylesheet">
    <script src="<c:url value= "resources/CreateUser.js" />" crossorigin="anonymous"></script>
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

    	<div class= "row">
	   	 	<div class ="row forms">
	   	 			<div class= "col-lg-12 col-md-12 col-sm-12 col-xs-12 heading">
	   	 				<h2>Create A New User</h2>	
	   	 			</div>
	                <div class= "col-lg-12 col-md-12 col-sm-12 col-xs-12">
					    <form>
	                    	<div class="form-group">
	                            <label class="label" for="employeeName">EmployeeName</label>
	                            <input name="emp_Name" type= "text" class="form-control" id ="employeeName" placeholder =" EmployeeName" required/>
	                        </div>
	                        <div class="form-group">
	                            <label class="label" for="employeeID">EmployeeID</label>
	                            <input name="emp_ID" type= "text" class="form-control" id ="employeeID" placeholder =" EmployeeID" required/>
	                        </div>
	                        <div class="form-group">
	                            <label class="label" for="password">Password</label>
	                            <input name="password" type= "password" class="form-control" id ="password" placeholder =" Password" required/>
	                        </div>
	                        <div class="form-group">
	                            <label class="label" for="confirmPassword">ConfirmPassword</label>
	                            <input name="confirmPassword" type= "password" class="form-control" id ="confirmPassword" placeholder ="Confirm Password" required/>
	                        </div>
	                        <div class="radiobutton">
	                        	<p>Is User an administrator ?</p>
			                  	<div class="radio buttons">
									  <label><input id="Yes" value="true" type="radio" name="isAdmin"checked>Yes</label>
									  <label><input id="No" value="false" type="radio" name="isAdmin">No</label>
								</div>
							</div>
		                </form>
	                    <button type="submit" class="btn btn-lg btn-primary button" onclick="eventListener()">Submit</button>	
	           		</div>
		    </div>  
		    <div class="row" id="message">
		   	</div>
    	</div>
<!--<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>-->
<script src="<c:url value= "resources/dist/jQuery.js"/>" crossorigin="anonymous"></script>
<script src="<c:url value= "resources/dist/bootstrap-3.3.7-dist/js/bootstrap.js"/>" crossorigin="anonymous"></script>
</body>
</html>