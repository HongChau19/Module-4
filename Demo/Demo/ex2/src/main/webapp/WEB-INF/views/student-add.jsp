<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sinh viên</title>
    <style>
        .error { color: red; font-size: 0.9em; }
    </style>
</head>
<body>
<h1>Thêm Sinh viên mới</h1>

<form:form action="${pageContext.request.contextPath}/students/add" method="POST" modelAttribute="studentForm">

    <p>
        <label for="id">Mã số:</label>
        <form:input path="id" id="id"/>
        <form:errors path="id" cssClass="error"/>
    </p>

    <p>
        <label for="name">Họ tên:</label>
        <form:input path="name" id="name"/>
        <form:errors path="name" cssClass="error"/>
    </p>

    <p>
        <label for="gpa">Điểm tổng kết (0.0 - 10.0):</label>
        <form:input path="gpa" id="gpa" type="number" step="0.1"/>
        <form:errors path="gpa" cssClass="error"/>
    </p>

    <button type="submit">Thêm Sinh viên</button>
    <a href="<c:url value="/students"/>">Quay lại danh sách</a>

</form:form>
</body>
</html>