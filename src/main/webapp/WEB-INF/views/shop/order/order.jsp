<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
         trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문서 확인</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>주문서 확인</h2>

    <!-- 주문자 정보 -->
    <div class="card mb-4">
        <div class="card-header">
            주문자 정보
        </div>
        <div class="card-body">
            <p>이름: ${order.customerName}</p>
            <p>이메일: ${order.customerEmail}</p>
            <p>전화번호: ${order.customerPhone}</p>
            <!-- 기타 주문자 정보 추가 -->
        </div>
    </div>

    <!-- 주문 상품 목록 -->
    <div class="card">
        <div class="card-header">
            주문 상품 목록
        </div>
        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">상품명</th>
                    <th scope="col">수량</th>
                    <th scope="col">가격</th>
                    <!-- 추가적인 상품 정보 표시 -->
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${order.orderItems}">
                    <tr>
                        <td>${item.productName}</td>
                        <td>${item.quantity}</td>
                        <td>${item.price}</td>
                        <!-- 추가적인 상품 정보 표시 -->
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 주문하기 버튼 -->
    <div class="text-center mt-3">
        <button class="btn btn-primary">주문하기</button>
    </div>
</div>

</body>
</html>
