<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Manjari:400,700,100&display=swap" rel="stylesheet">
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">-->
    <link rel="stylesheet" href="<c:url value= "resources/dist/bootstrap-3.3.7-dist/css/bootstrap.css"/>" crossorigin="anonymous">
    <link href="<c:url value= "resources/login.css" />" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class = "row titleclass">
        <div class="formclass">
            <div class = "row headerclass">
               <div class = "col-lg-12">
                    <h1>SIGN IN</h1>
                </div>
            </div>
            <div class ="row forms">
                <div class= "col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <form method="POST" action="login_user">
                        <div class="form-group">
                            <label for="employeeID"></label>
                            <input name="emp_ID" type= "text" class="form-control" id ="employeeID" placeholder =" EmployeeID" required/>
                        </div>
                        <div class="form-group">
                            <label for="password"></label>
                            <input name="password" type= "password" class="form-control" id ="password" placeholder =" Password" required/>
                        </div>
                        <button type="submit" class="btn btn-lg btn-primary button">LOGIN</button>
                    </form>
                </div>
            </div>
        </div>
        <div class ="errormsg" id="message">
            <h1>${message}</h1>
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