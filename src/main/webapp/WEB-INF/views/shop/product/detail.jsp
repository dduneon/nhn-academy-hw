<%--
  Created by IntelliJ IDEA.
  User: dduneon
  Date: 12/6/23
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 text-center">
            <!-- 상품 이미지 -->
            <img src="${PRODUCT.getProductImage()}" alt="${PRODUCT.getProductId()}"
                 class="img-fluid" width="80%" height="200" role="img">
        </div>
        <div class="col-md-6">
            <!-- 상품 정보 -->
            <!-- todo 카테고리 수정 -->
            <p style="color: rgb(128,128,128)">아우터 > 패딩</p>
            <h2 class="mb-3">${PRODUCT.getModelName()}</h2>
            <p style="padding-bottom: 10px">${PRODUCT.getDescription()}</p>
            <p>상품명 : ${PRODUCT.getModelNumber()}</p>
            <p>
            <h3 class="fw-bold"><fmt:formatNumber value="${PRODUCT.getUnitCost()}"
                                                  type="number"
                                                  maxFractionDigits="3"/>원
            </h3>
            </p>
            <!-- 장바구니 담기 버튼 -->
            <form action="/user/additem.do" method="post">
                <input type="hidden" name="productId" value="${PRODUCT.getProductId()}">
                <button type="submit" class="btn btn-primary">장바구니 담기</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 Popper.js (필요한 경우) -->
</body>
</html>

