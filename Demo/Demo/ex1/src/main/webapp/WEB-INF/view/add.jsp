<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sinh viên</title>
</head>
<body>
<h1>Thêm Sinh viên mới</h1>

<form method="POST" action="<c:url value="/students/add"/>">
    <label for="studentId">Mã số:</label><br>
    <input type="text" id="studentId" name="studentId" required><br><br>

    <label for="name">Họ tên:</label><br>
    <input type="text" id="name" name="name" required><br><br>

    <label for="gpa">Điểm tổng kết (float):</label><br>
    <input type="number" step="0.01" id="gpa" name="gpa" min="0" max="10" required><br><br>

    <button type="submit">Thêm Sinh viên</button>
</form>

<p>
    <a href="<c:url value="/students"/>">← Quay lại Danh sách</a>
</p>
</body>
</html>