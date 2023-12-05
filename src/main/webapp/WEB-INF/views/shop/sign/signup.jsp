<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"
         session="true" %>

<script> function validateForm() {
  var selectedMonth = document.getElementById("user_birth_month").value;
  if (selectedMonth === "월") {
    alert("월을 선택해 주세요.");
    return false;
  }
  return true;
}
</script>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signupAction.do" onsubmit="return validateForm();">

            <h1 class="h3 mb-3 fw-normal">NHN SHOPPING</h1>
            <h2 class="h3 mb-3 fw-normal">회원가입을 환영합니다</h2>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id"
                       placeholder="아이디" required>
                <label for="user_id">아이디</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name"
                       placeholder="이름" required>
                <label for="user_id">이름</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password"
                       placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="bir_wrap">
                <label class="form-label mt-4">생년월일</label>
                <div class="bir_yy">
                		<span class="ps_box">
                			<input type="text" class="form-control" id="user_birth_year"
                                   name="user_birth_year" placeholder="년(4자)" maxlength="4"
                                   required>
                		</span>
                </div>
                <div class="bir_mm">
                		<span class="ps_box focus">
			                <select class="form-select" id="user_birth_month"
                                    name="user_birth_month" required>
						        <option disabled selected>월</option>
						        <option value="01">1월</option>
						        <option value="02">2월</option>
						        <option value="03">3월</option>
						        <option value="04">4월</option>
						        <option value="05">5월</option>
						        <option value="06">6월</option>
						        <option value="07">7월</option>
						        <option value="08">8월</option>
						        <option value="09">9월</option>
						        <option value="10">10월</option>
						        <option value="11">11월</option>
						        <option value="12">12월</option>
						     </select>
                		</span>
                </div>
                <div class="bir_dd">
                		<span class="ps_box">
                			<input type="text" class="form-control" id="user_birth_day"
                                   name="user_birth_day"
                                   placeholder="일" maxlength="2" required>
                		</span>
                </div>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">회원가입</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>