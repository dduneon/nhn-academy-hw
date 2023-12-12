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
    <h2>주문 명세 조회</h2>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">주문 번호</th>
            <th scope="col">판매자 번호</th>
            <th scope="col">주문일</th>
            <th scope="col">예상 도착일</th>
            <th scope="col">배송지</th>
            <th scope="col">주문 상세정보</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.ORDER_LIST}">
            <tr>
                <td>${item.getOrderId()}</td>
                <td>${item.getCustomerId()}</td>
                <td>${item.getOrderDate()}</td>
                <td>${item.getShipDate()}</td>
                <td>${item.getShipAddress()}</td>
                <td>
                    <div class="justify-content-center">
                        <!-- todo detail page -->
                        <form method="post" action="">
                            <button class="btn btn-primary" type="submit" name="item" id="item"
                                    value="${item.getOrderId()}">보기
                            </button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS 및 Popper.js (필요한 경우) -->
</body>
</html>
