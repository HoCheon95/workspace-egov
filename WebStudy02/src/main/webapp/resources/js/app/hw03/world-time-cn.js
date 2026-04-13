/**
 *
 */
function fnMapToOptions(map) {
  return Object.entries(map).map((array) => {
    const option = document.createElement("option");
    option.value = array[0];
    option.label = array[1];
    return option;
  });
}
document.addEventListener("DOMContentLoaded", () => {
  const localeSel = document.getElementById("locale");
  const timeZoneSel = document.getElementById("timezone");

  fetch("./options", {
    headers: {
      accept: "application/json",
    },
  })
    .then((resp) => resp.json())
    .then(({ localeMap, zoneMap }) => {
      const options = fnMapToOptions(localeMap);
      localeSel.append(...options);

      const tzOptions = fnMapToOptions(zoneMap);
      timeZoneSel.append(...tzOptions);
    });

  const asyncform = document.getElementById("async-form");
  const resultArea = document.querySelector("#result");
  asyncform.addEventListener("submit", (e) => {
    // 1. submit 이벤트의 기본 동작을 막음.
    e.preventDefault();
    const fd = new FormData(asyncform);
    const params = new URLSearchParams(fd);
    fetch(`${asyncform.action}?${params.toString()}`,{
        headers:{
            accept:"application/json"
        }
    })
      .then((resp) => resp.json())
      .then(({ now }) => {
        resultArea.innerHTML = `<h1>${now}</h1>`;
      });
  });
});
