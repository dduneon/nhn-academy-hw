<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<script>
  window.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login_form");
    loginForm.addEventListener("submit", function (event) {
      const userId = document.getElementById("user_id");
      const userPassword = document.getElementById("user_password");
      if(userId.value.length==0) {
        alert("userId is empty");
        userId.focus();
        event.preventDefault();
      } else if (userPassword.value.length==0) {
        alert("userPW is empty");
        userPassword.focus();
        event.preventDefault();
      }
    })
  })
</script>
<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/login" id="login_form">

            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디" >
                <label for="user_id">회원아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" >
                <label for="user_password">비밀번호</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>