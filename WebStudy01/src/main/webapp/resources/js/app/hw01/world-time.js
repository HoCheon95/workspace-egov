/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
  const fetchBtn = document.getElementById("fetch-btn");
  const resultArea = document.querySelector("#result");
  const localeSel = document.getElementById("locale");
  const timezoneSele = document.getElementById("timezone");

  fetchBtn.addEventListener("click", () => {
    // 1. 비동기 요청 전송 "/hw01/wroldtime/json"
    // 2. json 응답 수신
    // 3. json 으로부터 html 을 생성하고 DOM을 완성함.
    fetch(`../../../hw01/worldtime/json?locale=${localeSel.value}&timezone=${timezoneSele.value}`)
      .then((resp) => resp.json())
      .then(({ now }) => {
        resultArea.innerHTML = `<h1>${now}</h1>`;
      });
  });
});
