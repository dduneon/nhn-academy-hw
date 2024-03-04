<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"
         trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원 정보 수정</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="mb-4">회원 정보 수정</h2>

            <!-- 회원 정보 수정 폼 -->
            <form action="/mypage/update" method="post">
                <!-- 이름 입력 필드 -->
                <div class="mb-3">
                    <label for="id" class="form-label">아이디</label>
                    <input type="text" class="form-control" id="id" name="id"
                           value="${requestScope.USER_REQ.getUserId()}" disabled required>
                </div>

                <!-- 이름 입력 필드 -->
                <div class="mb-3">
                    <label for="name" class="form-label">변경할 이름</label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="${requestScope.USER_REQ.getUserName()}" required>
                </div>

                <!-- 현재 비밀번호 입력 필드 -->
                <div class="mb-3">
                    <label for="past_password" class="form-label">현재 비밀번호</label>
                    <input type="password" class="form-control" id="past_password"
                           name="past_password"
                           required>
                </div>

                <!-- 변경할 비밀번호 입력 필드 -->
                <div class="mb-3">
                    <label for="new_password" class="form-label">변경할 비밀번호</label>
                    <input type="password" class="form-control" id="new_password"
                           name="new_password"
                           required>
                </div>

                <!-- 생일 입력 필드 -->
                <div class="mb-3">
                    <label for="birthday" class="form-label">변경할 생일</label>
                    <!-- todo 이거 좋은데 signup에서도 쓸수 있도록 -->
                    <!-- fn:replace - to "" -->
                    <input type="date" class="form-control" id="birthday" name="birthday"
                           value="${requestScope.USER_REQ.getUserBirthToDateFormat()}"
                           required>
                </div>

                <!-- 수정 버튼 -->
                <button type="submit" class="btn btn-primary">수정</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 Popper.js (필요한 경우) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
