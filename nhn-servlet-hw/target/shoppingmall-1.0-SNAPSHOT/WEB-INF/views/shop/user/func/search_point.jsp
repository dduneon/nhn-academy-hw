<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>포인트 사용 내역</h2>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">사용/적립 포인트</th>
            <th scope="col">적립일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.RECORD_LIST}">
            <tr>
                <td>${item.getAmount()}</td>
                <td>${item.getRecordDate()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS 및 Popper.js (필요한 경우) -->
</body>
</html>
