<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>ContactUs</title>
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
     <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/ContactUs.css" />" rel="stylesheet">
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
 		<div class="row">
	 		<div class="col-sm-9 col-xs-9 col-md-9 paragraph jumbotron">
	 				<p><strong>About Us:</strong></p>	
	 				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
	 				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	 				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
	 				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
	 				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
	 				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	 				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
	 				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	 				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
	 				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
	 				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
	 				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	 				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
	 				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	 				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
	 				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
	 				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
	 				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	 				Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
	 				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
	 				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
	 				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
	 				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
	 				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	 				</p>
	 		</div>
	 		<div class="row col-sm-3 col-xs-3 col-md-3">
		 		<div class="contact col-sm-12 col-xs-12 col-md-12">
			 		<p class="contactHeader"><strong>Contact Details:</strong></p>
			 		<div class="contactDetails">
			 			<p><strong>Name:</strong> Kinshuk Srivastava</p>
			 			<p><strong>Phone:</strong> +9186588658</p>
			 			<p><strong>Email:</strong> kinshuk26@abcef.com</p>
			 		</div>
		 		</div>
	 		</div>
	 		<div class="row col-sm-3 col-xs-3 col-md-3">
		 		<div class="sidePara col-sm-12 col-xs-12 col-md-12">
			 		<p><strong>Manage Your Requests with ease:</strong> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			 		tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			 		quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			 		consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
			 		cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			 		proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
		 		</div>
	 		</div>
	 		<div class="row col-sm-3 col-xs-3 col-md-3">
		 		<div class="sidePara col-sm-12 col-xs-12 col-md-12">
			 		<p><strong>Everything in one place:</strong> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
			 		tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
			 		quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
			 		consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
			 		cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			 		proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
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
<script src="<c:url value= "resources/ContactUs.js" />" crossorigin="anonymous"></script>
</body>
</html>