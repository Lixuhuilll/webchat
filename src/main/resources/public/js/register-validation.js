const registerForm = document.getElementById("register-form");
const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirm-password");
const submitButton = document.querySelector('button[type="submit"]');
const passwordError = document.getElementById("password-error");
const passwordNoFit = document.getElementById("password-no-fit");
submitButton.disabled = true;
registerForm.addEventListener("input", () => {
    // 获取密码和确认密码输入框的值
    const passwordValue = passwordInput.value;
    const confirmPasswordValue = confirmPasswordInput != null ? confirmPasswordInput.value : null;

    // 校验密码长度和包含数字和字母
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.{6,})/;
    const isValidPassword = passwordRegex.test(passwordValue);
    if (passwordValue != null && passwordValue !== "" && !isValidPassword) {
        submitButton.disabled = true;
        passwordError.innerText = "密码至少6个字符且必须包含数字和字母";
    }
    // 校验确认密码和密码是否相同
    else if (confirmPasswordValue != null && confirmPasswordValue !== "" && confirmPasswordValue !== passwordValue) {
        submitButton.disabled = true;
        passwordNoFit.innerText = "密码不一致";
    }
    else {
        if(isValidPassword) {
            submitButton.disabled = false;
        }
        passwordError.innerText = "";
        if (passwordNoFit != null) {
            passwordNoFit.innerText = "";
        }
    }
});
