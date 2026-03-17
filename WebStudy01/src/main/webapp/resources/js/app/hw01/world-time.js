/**
 *
 */
// document.addEventListener("DOMContentLoaded", () => {
//   const fetchBtn = document.getElementById("fetch-btn");
//   const resultArea = document.querySelector("#result");
//   fetchBtn.addEventListener("click", () => {
//     // 1. 비동기 요청 전송 "/hw01/wroldtime/json"
//     // 2. json 응답 수신
//     // 3. json 으로부터 html 을 생성하고 DOM을 완성함.
//     fetch("../../../hw01/worldtime/json")
//       .then(resp => resp.json())
//       .then(({ now }) => {
//             resultArea.innerHTML = `<h1>${now}</h1>`
//       });
//   });
// });


document.addEventListener("DOMContentLoaded", () => {
    const response = (() => {
        fetch("/hw01/worldtime/options")
            .then(resp => {
                console.log("전체 응답 객체(Response):", resp);
                return resp.json();
            })
            .then(data => {
                if (data.locales){
                    document.getElementById('locale').innerHTML = Object.entries(data.locales).map(([key, value])=>
                        `<option value="${key}">${value}</option>`
                    ).join('');
                }
                
                if (data.timeZones) {
                    document.getElementById('timezone').innerHTML = Object.entries(data.timeZones).map(([key, value])=> `<option value="${key}">${value}</option>`
                ).join('');
            }
        })
    });

    response();

    // 2. 버튼 클릭 시: 선택된 로케일/타임존으로 /hw01/worldtime/json 에 fetch
        //    - 응답 JSON 데이터로 result 영역에 시간 정보 UI 생성
    const fetchBtn = document.getElementById('fetchBtn');
    const resultArea = document.querySelector("#result");
    fetchBtn.addEventListener('click', () => {
        const locale = document.getElementById('locale').value;
        const timezone = document.getElementById('timezone').value;

        const url = `/hw01/worldtime/json?locale=${locale}&timezone=${timezone}`;

        fetch(url)
            .then(resp => {
                return resp.json();
            })
            .then(({now}) => {
                resultArea.innerHTML = `<h3>현재 시간 : ${now}</h3>`;
            })
    });
});