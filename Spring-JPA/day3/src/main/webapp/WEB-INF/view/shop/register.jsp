<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"
         session="true" %>

<script src="<c:url value="/main/webapp/resources/js/signup-listener.js"/> "></script>
<head>
    <title>Sign Up</title>
</head>

<div style="margin: auto; width: 400px;" id="signup">
    <div class="p-2">
        <form method="post" action="/register">

            <h1 class="h3 mb-3 fw-normal">NHN SHOPPING</h1>
            <h2 class="h3 mb-3 fw-normal">회원가입을 환영합니다</h2>

            <div class="form-floating" id="form-id">
                <input type="text" name="user_id" class="form-control" id="user_id"
                       placeholder="아이디">
                <label for="user_id">아이디</label>
            </div>
            <label for="form-id" style="color: red" id="validate-text">4-12자 이내의 아이디를 입력해 주세요</label>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name"
                       placeholder="이름">
                <label for="user_id">이름</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password"
                       placeholder="비밀번호">
                <label for="user_password">비밀번호</label>
            </div>

            <div class="bir_wrap">
                <label for="user_birth" class="form-label">생일</label>
                <input type="date" class="form-control" id="user_birth" name="user_birth"
                       >
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">회원가입</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>