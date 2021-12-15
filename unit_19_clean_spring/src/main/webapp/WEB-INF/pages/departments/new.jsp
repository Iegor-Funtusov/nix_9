<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action = "${pageContext.request.contextPath}/departments/new" method = "post" modelAttribute="dep">
    name: <input type = "text" name = "name" id="name">
    <br />
    type: <form:select items="${types}" var="type" path="departmentType" />
    <input type = "submit" value = "Submit" />
</form:form>
</body>
</html>
