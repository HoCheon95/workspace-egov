document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("pw-form");
    const newPassword = form.newPassword;
    const retypePassword = form.retypePassword;

    const validatePassword = () => {
        const pw1 = newPassword.value;
        const pw2 = retypePassword.value;
        if(pw1 && pw1 === pw2){
            retypePassword.setCustomValidity("");
        } else {
            retypePassword.setCustomValidity("비밀번호 재입력 확인");
            retypePassword.reportValidity();
        }
    }
    newPassword.addEventListener("change", validatePassword);
    retypePassword.addEventListener("input", validatePassword);
});