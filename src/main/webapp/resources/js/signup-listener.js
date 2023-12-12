window.addEventListener("DOMContentLoaded", function () {
  const signupForm = document.getElementById("signup");

  const validationForm = function (form) {
    if(form['user_id'].value.trim() === '') {
      alert("user id is empty");
      return false;
    } else if (form['user_name'].value.trim() === '') {
      alert("user name is empty");
      return false;
    } else if (form['user_password'].value.trim() === '') {
      alert("user password is empty");
      return false;
    } else if (form['user_birth'].value.trim() === '') {
      alert("user birth is empty");
      return false;
    }
    return true;
  }

  signupForm.addEventListener("submit", function (event) {
    if(!validationForm(event.target)) {
      event.preventDefault();
      return;
    }
  });

  const userId = document.getElementById("user_id");
  const message = document.getElementById("validate-text");
  userId.addEventListener("input", function (event) {
    if(checkValidateLength(event.target.value)) {
      checkValidateExist(event.target.value, function (validate) {
        console.log("validate: " + validate);
        if (validate) {
          message.innerText = "사용할 수 있는 아이디";
        } else {
          message.innerText = "이미 존재하는 아이디 입니다";
        }
      })
    } else {
      message.innerText = "아이디를 4자 이상, 12자 이하로 입력해 주세요";
    }
    console.log("input");
  })
});

function checkValidateExist(userId, validateExist) {
  console.log("checkValidateExist call");
  const xhr = new XMLHttpRequest();
  const url = "/validate";
  console.log(url);

  xhr.addEventListener("load", function () {
    if(this.status===200) {
      console.log("resp: " + this.response);
      validateExist(this.response['validation']);
    }
  });

  xhr.open("GET", url);
  xhr.setRequestHeader("content-type", "application/json");
  xhr.setRequestHeader("user-id", userId);
  xhr.responseType = "json";
  xhr.send('');
}

function checkValidateLength(userId, validateLength) {
  console.log("checkValidateLength call");

  return userId.length >= 4 && userId.length <= 12;
}