<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết Sinh viên</title>
</head>
<body>
<h1>Chi tiết Sinh viên: ${student.id}</h1>

<p><strong>Mã số:</strong> <c:out value="${student.id}"/></p>
<p><strong>Họ tên:</strong> <c:out value="${student.name}"/></p>
<p><strong>Điểm tổng kết:</strong> <fmt:formatNumber value="${student.gpa}" pattern="0.0"/></p>
<p><strong>Hạng:</strong> <c:out value="${student.rank}"/></p>

<p>
    <a href="<c:url value="/students/${student.id}/edit"/>">Sửa</a> |

<form action="<c:url value="/students/${student.id}/delete"/>" method="POST" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa sinh viên ${student.id} không?');">
    <button type="submit" style="color: red; background: none; border: none; cursor: pointer; padding: 0;">Xóa</button>
</form>
|
<a href="<c:url value="/students"/>">Quay lại danh sách</a>
</p>

</body>
</html>