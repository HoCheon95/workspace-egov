async function fetchData() {
    const resp = await fetch("/files", {
        headers: {
            accept: "application/json",
        },
    });
    return await resp.json();
}

/**
 * @param {Array} filesJson 파일명 문자열 배열
 * @param {HTMLElement} parentEl 파일이 렌더링될 부모 요소 (ul)
 */
function generateUi(filesJson, parentEl) {
    // 기존 파일 목록 초기화
    parentEl.replaceChildren();

    // 파일이 없을 경우 안내 문구 출력
    if (filesJson.length === 0) {
        const li = document.createElement("li");
        li.className = "list-group-item text-muted text-center";
        li.innerText = "업로드된 파일이 없습니다.";

        parentEl.append(li);
        return;
    }

    const lis = filesJson.map(fn => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";

        // 왼쪽 영역: 아이콘 + 파일명
        const leftSpan = document.createElement("span");

        const icon = document.createElement("i");
        icon.className = "bi bi-file-earmark-code me-2";

        const fileNameText = document.createTextNode(fn);

        // 다운로드 버튼
        const btn = document.createElement("a");
        btn.href=`/files/${fn}`;
        // btn.download=fn; // inline/attatchment, 저장명
        btn.type = "button";
        btn.className = "btn btn-sm btn-primary ms-2 download-btn";
        btn.dataset.filename = fn;
        btn.innerText = "다운로드";

        leftSpan.append(icon, fileNameText, btn);

        // 오른쪽 영역: File 배지
        const badge = document.createElement("span");
        badge.className = "badge bg-secondary rounded-pill";
        badge.innerText = "File";

        li.append(leftSpan, badge);

        return li;
    });

    // 404 체크용 더미
    const dummyLi = document.createElement("li");
    dummyLi.className = "list-group-item d-flex justify-content-between align-items-center";

    const dummyBtn = document.createElement("a");
    dummyBtn.href = `/files/dummy`;
    dummyBtn.className = "btn btn-sm btn-danger ms-2 download-btn";
    dummyBtn.innerText = "다운로드(404 테스트)";

    dummyLi.append(dummyBtn);
    parentEl.append(...lis, dummyLi);
}

document.addEventListener("DOMContentLoaded", async () => {
    const filesArea = document.getElementById("files-area");
    const uploadForm = document.getElementById("upload-form");
    const filesJosn = await fetchData();
    try {
        // 서버에서 현재 파일 목록 데이터를 가져온다
        const filesJson = await fetchData();
        console.log(filesJson);
        // 가져온 파일 목록 데이터를 화면에 출력한다
        generateUi(filesJson, filesArea);
        // uploadForm에 sumbit 이벤트 등록
        uploadForm.addEventListener("submit", async (event) => {
            event.preventDefault();
            const form = event.target;
            const url = form.action;
            const method = form.method;
            const headers = {accept:"application/json"}
            const body = new FormData(form);
            const resp = await fetch(url, {method, headers, body});
            const filesJosn = await resp.json();
            form.reset();
            generateUi(filesJosn, filesArea);
        });

        // filesArea.addEventListener("click", (evnet)=>{
        //     console.log(event.target, event.target.classList.contains("download-btn"));
        //     if(!event.target.classList.contains("download-btn")) {
        //         return false;
        //     }

        //     const filename = event.target.dataset.filename;
        //     console.log(filename);
        // });
    } catch (error) {
        console.error("데이터를 불러오는 중 오류가 발생했습니다:", error);
        filesArea.innerHTML = '<li class="list-group-item list-group-item-danger">데이터를 불러오지 못했습니다.</li>';
    }
});
