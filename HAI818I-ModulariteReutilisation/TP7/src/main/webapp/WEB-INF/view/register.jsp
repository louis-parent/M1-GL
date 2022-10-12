<%--
  Created by IntelliJ IDEA.
  User: tibermacin
  Date: 24/02/2022
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Formulaire d'inscription</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container" style="width: 50%">
<h2 style="text-align: center;">Formulaire d'inscription</h2>
<div role="form">
<form:form action="" method="post" modelAttribute="registration">
    <div class="form-group">
        <label for="nom"><spring:message code="email" /> :</label>
        <form:input path="email" id="email" name="email" placeholder="Saisir votre email" class="form-control"/>
    </div>
    <label for="prenom"><spring:message code="firstname" /> :</label>
    <form:input path="prenom" id="prenom" name="prenom" placeholder="Saisir votre prénom" class="form-control"/>
    </div>
    <div class="form-group">
    <label for="nom"><spring:message code="lastname" /> :</label>
    <form:input path="nom" id="nom" name="nom" placeholder="Saisir votre nom" class="form-control"/>
    </div>
    <div class="form-group">
        <button class="btn btn-primary float-end mt-3" type="submit">
            <spring:message code="register" />
        </button>
    </div>
</form:form>
</div>
</div>
</body>
</html>
