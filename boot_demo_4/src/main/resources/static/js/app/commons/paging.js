/**
 * 페이징과 검색시 공통 컴포넌트 활용 방법
 * 1. PageinationRenderer 에서 생성되는 a 태그에는 data-page 속성이 필요함.
 * 2. 페이징 처리에 사용되는 a 태그는 부모 엘리먼트 내에 있어야 하고, 해당 엘리먼트는 data-pg-target 속성이 필요함.
 * 3. 검색 조건 입력 UI 와 전송 UI 분리.
 * 4. 검색 조건 입력 UI는 하나의 엘리먼트로 구성하고, 해당 엘리먼트는 data-pg-form속성이 필요함.
 * 5. data-pg-target 속성과 data-pg-form 속성은 전송 ui 로 사용되는 form 을 지칭함.
 */

document.addEventListener("DOMContentLoaded", () => {
  const aTagParents = document.querySelectorAll("[data-pg-target]");
  aTagParents.forEach(atp => 
    atp.addEventListener("click", (event) => {
      if(!event.target.dataset.page) return false;
        const formSelector =atp.dataset['pgTarget'];
        const searchForm = document.querySelector(formSelector);
        searchForm.page.value = event.target.dataset.page;
        searchForm.requestSubmit();
  }));

  const searchUis = document.querySelectorAll("[data-pg-form]");
  searchUis.forEach((sui) =>
    sui.addEventListener("click", (event) => {
      if (!event.target.classList.contains("searchBtn")) return false;

      const formSelector = sui.dataset["pgForm"];
      const searchForm = document.querySelector(formSelector);

      sui.querySelectorAll("[name]").forEach((input) => {
        searchForm[input.name].value = input.value;
      });
      searchForm.requestSubmit();
    }),
  );
});
