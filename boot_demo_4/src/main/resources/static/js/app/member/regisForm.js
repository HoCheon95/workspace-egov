document.getElementById('idCheckBtn').addEventListener('click', function () {
    const checkId = document.getElementById('memId').value.trim();
    const resultSpan = document.getElementById('idCheckResult');

    if (!checkId) {
        resultSpan.textContent = '아이디를 입력하세요.';
        resultSpan.className = 'ms-2 small text-warning';
        return;
    }

    const formData = new URLSearchParams();
    formData.append('checkId', checkId);

    fetch('/member/idCheck', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: formData
    })
    .then(res => res.json())
    .then(data => {
        if (data.duplicated) {
            resultSpan.textContent = '이미 사용 중인 아이디입니다.';
            resultSpan.className = 'ms-2 small text-danger';
        } else {
            resultSpan.textContent = '사용 가능한 아이디입니다.';
            resultSpan.className = 'ms-2 small text-success';
        }
    });
});
