<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
         trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원 서비스</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<div class="container mt-5 text-center">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1>마이 페이지</h1>
            <h3 style="padding-bottom: 40px">${sessionScope.USER_NAME_SESSION} 님, 즐거운 쇼핑 되세요!</h3>

            <!-- 버튼 그룹 -->
            <div class="btn-group-vertical">
                <!-- 회원 정보 수정 -->
                <a href="/user/mypage/info.do" class="btn btn-primary mb-2">회원 정보 수정</a>

                <!-- 주문 명세 조회 -->
                <a href="/user/mypage/orders.do" class="btn btn-primary mb-2">주문 명세 조회</a>

                <!-- 포인트 사용내역 조회 -->
                <a href="/user/mypage/points.do" class="btn btn-primary mb-2">포인트 사용내역 조회</a>

                <!-- 주소 관리 -->
                <a href="/user/mypage/address.do" class="btn btn-primary mb-2">주소 관리</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
