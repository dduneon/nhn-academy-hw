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
            <form action="/updateMember" method="post">
                <!-- 이름 입력 필드 -->
                <div class="mb-3">
                    <label for="name" class="form-label">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>

                <!-- 비밀번호 입력 필드 -->
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password"
                           required>
                </div>

                <!-- 생일 입력 필드 -->
                <div class="mb-3">
                    <label for="birthday" class="form-label">생일</label>
                    <!-- todo 이거 좋은데 signup에서도 쓸수 있도록 -->
                    <input type="date" class="form-control" id="birthday" name="birthday" required>
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
