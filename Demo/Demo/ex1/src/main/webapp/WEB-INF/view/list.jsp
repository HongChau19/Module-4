<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách Sinh viên</title>
</head>
<body>
<h1>Danh sách Sinh viên</h1>

<p>
  <a href="<c:url value="/students/add"/>">Thêm sinh viên</a>
</p>

<c:choose>
  <c:when test="${not empty studentList}">
    <table border="1" cellpadding="5" cellspacing="0">
      <thead>
      <tr>
        <th>Mã số</th>
        <th>Họ tên</th>
        <th>Điểm tổng kết (GPA)</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="sv" items="${studentList}">
        <tr>
          <td><c:out value="${sv.studentId}"/></td>
          <td><c:out value="${sv.name}"/></td>
          <td><c:out value="${sv.gpa}"/></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:when>
  <c:otherwise>
    <p>Danh sách sinh viên trống.</p>
  </c:otherwise>
</c:choose>
</body>
</html>