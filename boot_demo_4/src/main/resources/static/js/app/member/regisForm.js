// document.getElementById('idCheckBtn').addEventListener('click', function () {
//     const checkId = document.getElementById('memId').value.trim();
//     const resultSpan = document.getElementById('idCheckResult');

//     if (!checkId) {
//         resultSpan.textContent = '아이디를 입력하세요.';
//         resultSpan.className = 'ms-2 small text-warning';
//         return;
//     }

//     const formData = new URLSearchParams();
//     formData.append('checkId', checkId);

//     fetch('/member/idCheck', {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
//         body: formData
//     })
//     .then(res => res.json())
//     .then(data => {
//         if (data.duplicated) {
//             resultSpan.textContent = '이미 사용 중인 아이디입니다.';
//             resultSpan.className = 'ms-2 small text-danger';
//         } else {
//             resultSpan.textContent = '사용 가능한 아이디입니다.';
//             resultSpan.className = 'ms-2 small text-success';
//         }
//     });
// });


document.addEventListener("DOMContentLoaded", () => {
    const idTag = document.getElementById("memId");
    // const resultSpan = document.getElementById('idCheckResult');

    // if(!idTag) {
    //     resultSpan.textContent = '아이디를 입력하세요.';
    //     resultSpan.className = 'ms-2 small text-warning';
    //     return;
    // }

    const validateMemId = async (event) => {
        // FormData(multipart/form-data), URLSearchParams(application/x-www-form-urlencoded) 로 body 데이터를 표현함.
        const params = new URLSearchParams();
        params.append("checkId", event.target.value);
        // <form:form> 태그가 CSRF 토큰을 자동으로 hidden input으로 넣어주는데, fetch 요청에서 미포함
        const csrfToken = document.querySelector('input[name="_csrf"]').value;
        const resp = await fetch("/member/idCheck", {
            method:"post",
            headers: {
                accept: "application/json",
                "X-CSRF-TOKEN": csrfToken
            },
            body: params
        });

        if(resp.ok){
            const {duplicated} = await resp.json();
            if (duplicated) {
                idTag.setCustomValidity("이미 사용 중인 아이디입니다.");
                idTag.reportValidity();
                // resultSpan.className = 'ms-2 small text-danger';
            } else {
                idTag.setCustomValidity("");
            }
        } else {
            idTag.setCustomValidity("중복 체크를 위한 요청 처리 실패");
            idTag.reportValidity();
            // resultSpan.className = 'ms-2 small text-danger';
        }   

        
        if(pw1 && pw1 === pw2){
            retypePassword.setCustomValidity("");
        } else {
            retypePassword.setCustomValidity("비밀번호 재입력 확인");
            retypePassword.reportValidity();
        }
    }
    idTag.addEventListener("change", validateMemId);
});