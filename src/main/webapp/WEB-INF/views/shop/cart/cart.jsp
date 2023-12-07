<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <h2>Shopping Cart</h2>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">이미지</th>
            <th scope="col">제품명</th>
            <th scope="col">가격</th>
            <th scope="col">수량</th>
            <th scope="col">총 가격</th>
            <th scope="col">삭제</th>
        </tr>
        </thead>
        <tbody>
        <!-- 각 상품에 대한 정보 반복 -->
        <!-- todo product_image, product_name, product_price, quantity, product_cost * quantity-->
        <c:forEach var="item" items="${requestScope.CART_PRODUCTS}">
            <tr>
                <td><img src="${item.getProductImage()}" alt="${item.getModelName()}" width="50"
                         height="50"></td>
                <td>${item.getModelName()}</td>
                <td><fmt:formatNumber value="${item.getUnitCost()}"
                                      type="number"
                                      maxFractionDigits="3"/> 원
                </td>
                <td>${item.getQuantity()}</td>
                <td><fmt:formatNumber value="${item.getUnitCost() * item.getQuantity()}"
                                      type="number"
                                      maxFractionDigits="3"/> 원
                </td>
                <td>
                    <div class="justify-content-center">
                        <form method="post" action="/user/deleteitem.do">
                            <button class="btn btn-danger" type="submit" name="item" id="item"
                                    value="${item.getProductId()}">삭제
                            </button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 총 상품 개수와 총 가격 표시 -->
    <div class="row">
        <!-- 첫 번째 행 -->
        <div class="row justify-content-end">
            <div class="col text-end">
                <p>총 상품 개수: ${requestScope.CART_PRODUCTS.size()}</p>
            </div>
        </div>

        <!-- 두 번째 행 -->
        <div class="row justify-content-end">
            <!-- 총 가격 계산 로직 작성 -->
            <div class="col text-end">
                <h5>총 가격: <fmt:formatNumber value="${requestScope.TOTAL_PRICE}"
                                            type="number"
                                            maxFractionDigits="3"/> 원</h5>
            </div>
        </div>
    </div>


    <!-- 주문하기 버튼 중앙 정렬 -->
    <form action="/user/order.do" method="get">
        <div class="row justify-content-center mb-1">
            <div class="row justify-content-center">
                <button class="btn btn-primary" style="width: 30%" type="submit">주문하기</button>
            </div>
        </div>
    </form>

</div>

<!-- Bootstrap JS 및 Popper.js (필요한 경우) -->
</body>
</html>
