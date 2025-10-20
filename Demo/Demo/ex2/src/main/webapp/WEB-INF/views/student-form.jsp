<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh viên</title>
    <style>
        .message-success { color: green; font-weight: bold; margin-bottom: 10px; }
        .pagination a, .pagination span { padding: 5px 10px; margin: 0 2px; border: 1px solid #ccc; text-decoration: none; }
        .pagination .active { background-color: #007bff; color: white; border: 1px solid #007bff; }
        .table-container { margin-top: 15px; }
    </style>
</head>
<body>
<h1>Quản lý Sinh viên</h1>

<c:if test="${not empty message}">
    <div class="message-success">${message}</div>
</c:if>

<div class="toolbar">
    <p><a href="<c:url value="/students/add"/>">Thêm sinh viên mới</a></p>

    <form action="<c:url value="/students"/>" method="GET">
        <input type="text" name="q" placeholder="Tìm kiếm theo Mã số/Họ tên" value="${q}">

        <label for="sort">Sắp xếp theo:</label>
        <select name="sort" id="sort">
            <option value="id" <c:if test="${sort eq 'id'}">selected</c:if>>Mã số</option>
            <option value="name" <c:if test="${sort eq 'name'}">selected</c:if>>Họ tên</option>
            <option value="gpa" <c:if test="${sort eq 'gpa'}">selected</c:if>>Điểm tổng kết</option>
        </select>

        <select name="dir" id="dir">
            <option value="asc" <c:if test="${dir eq 'asc'}">selected</c:if>>Tăng dần (ASC)</option>
            <option value="desc" <c:if test="${dir eq 'desc'}">selected</c:if>>Giảm dần (DESC)</option>
        </select>

        <button type="submit">Tìm kiếm & Sắp xếp</button>
        <input type="hidden" name="page" value="${page}">
        <input type="hidden" name="size" value="${size}">
    </form>
</div>

<div class="table-container">
    <c:choose>
        <c:when test="${not empty students}">
            <table border="1" cellpadding="5" cellspacing="0">
                <thead>
                <tr>
                    <th>Mã số</th>
                    <th>Họ tên</th>
                    <th>Điểm tổng kết</th>
                    <th>Hạng</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><c:out value="${student.id}"/></td>
                        <td><c:out value="${student.name}"/></td>
                        <td><fmt:formatNumber value="${student.gpa}" pattern="0.0"/></td>
                        <td><c:out value="${student.rank}"/></td>
                        <td>
                            <a href="<c:url value="/students/${student.id}"/>">Chi tiết</a> |
                            <a href="<c:url value="/students/${student.id}/edit"/>">Sửa</a> |
                            <form action="<c:url value="/students/${student.id}/delete"/>" method="POST" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa sinh viên ${student.id} không?');">
                                <button type="submit" style="color: red; background: none; border: none; cursor: pointer; padding: 0;">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Không tìm thấy sinh viên nào.</p>
        </c:otherwise>
    </c:choose>
</div>

<div class="pagination">
    <p>
        Đang hiển thị trang **${page}** trên **${totalPages}** trang (${totalItems} mục).
    </p>

    <c:if test="${totalPages > 1}">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:url var="pageLink" value="/students">
                <c:param name="q" value="${q}"/>
                <c:param name="sort" value="${sort}"/>
                <c:param name="dir" value="${dir}"/>
                <c:param name="page" value="${i}"/>
                <c:param name="size" value="${size}"/>
            </c:url>

            <c:choose>
                <c:when test="${i == page}">
                    <span class="active">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageLink}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</div>
</body>
</html>