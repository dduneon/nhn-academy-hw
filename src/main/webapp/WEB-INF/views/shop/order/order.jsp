<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<script>
  function selectAddress() {
    // 선택된 주소 가져오기
    var selectedAddress = document.getElementById('savedAddress').value;

    // 주소 입력란에 선택된 주소 설정
    document.getElementById('inputAddress').value = selectedAddress;
  }
</script>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문서 확인</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<form action="/user/order/orderAction.do" method="post">
    <form class="container mt-5">
        <h2>주문서 확인</h2>

        <!-- 주문자 정보 -->
        <div class="card mb-4">
            <div class="card-header">
                주문자 정보
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="username" class="form-label">이름</label>
                    <input type="text" class="form-control" id="username" name="username" value="${USER.getUserName()}" required>
                </div>
                <!-- 기타 주문자 정보 추가 -->
                <div class="mb-3">
                    <label for="savedAddress" class="form-label">저장된 주소 목록</label>
                    <select class="form-select" id="savedAddress" name="savedAddress" onchange="selectAddress()">
                        <option selected disabled>저장된 주소에서 선택하기</option>
                        <c:forEach var="address" items="${USER_ADDRESSES}">
                            <option value="${address}">${address}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="inputAddress" class="form-label">주소 입력</label>
                    <input type="text" class="form-control" id="inputAddress" name="inputAddress" value="" required>
                </div>
            </div>
        </div>

        <!-- 주문 상품 목록 -->
        <div class="card mb-4">
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
                        <th scope="col">합계</th>
                        <!-- 추가적인 상품 정보 표시 -->
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${ORDER_LIST}">
                        <tr>
                            <td>${item.getModelName()}</td>
                            <td>${item.getQuantity()}</td>
                            <td><fmt:formatNumber value="${item.getUnitCost()}"
                                              type="number"
                                              maxFractionDigits="3"/></td>
                            <td><fmt:formatNumber value="${item.getUnitCost() * item.getQuantity()}"
                                                  type="number"
                                                  maxFractionDigits="3"/></td>
                            <!-- 추가적인 상품 정보 표시 -->
                        </tr>
                    </c:forEach>
                    <c:set var="ORDER_LIST_SESSION" scope="session" value="${ORDER_LIST}"/>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 결제 정보 목록 -->
        <div class="card mb-4">
            <div class="card-header">
                결제 정보
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="currentPoints" class="form-label">현재 보유 포인트</label>
                    <input type="text" class="form-control" id="currentPoints" name="currentPoints" value=<fmt:formatNumber value="${USER.getUserPoint()}"
                            type="number"
                            maxFractionDigits="3"/> readonly>
                </div>
                <div class="mb-3">
                    <label for="purchasePoints" class="form-label">구매 금액</label>
                    <input type="text" class="form-control" id="purchasePoints" name="purchasePoints" value=<fmt:formatNumber value="${TOTAL_PRICE}"
                            type="number"
                            maxFractionDigits="3"/> readonly>
                </div>
                <div class="mb-3">
                    <label for="remainingPoints" class="form-label">예상 잔여 포인트</label>
                    <c:set var="remain" value="${USER.getUserPoint() - TOTAL_PRICE}" scope="page"/>
                    <input type="text" class="form-control" id="remainingPoints" name="remainingPoints" value=<fmt:formatNumber value="${remain}"
                            type="number"
                            maxFractionDigits="3"/> readonly>
                </div>
            </div>
        </div>
        <!-- 주문하기 버튼 -->
        <div class="text-center mt-3">
            <c:choose>
                <c:when test="${remain gt 0}">
                    <button class="btn btn-primary">주문 하기</button>
                </c:when>
                <c:otherwise>
                    <label for="orderAction" class="form-label">예상 잔여 포인트</label>
                    <button class="btn btn-primary" id="orderAction" disabled>주문 하기</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</form>
</body>
</html>
