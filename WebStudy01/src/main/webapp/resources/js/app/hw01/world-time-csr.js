/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
  const localeSel = document.getElementById("locale");
  const timeZoneSel = document.getElementById("timezone");

  fetch("../../../hw01/worldtime/options")
    .then((resp) => resp.json())
    .then(({ locales, timeZones }) => {
      const options = [];
      for (let code in locales) {
        // dot notation : a,b
        // associative array : a["b"]
        // console.log(code, locales[code]);
        const option = document.createElement("option");
        option.value = code;
        option.label = locales[code];
        options.push(option);
      }
      localeSel.append(...options);

      const tzOptions = Object.entries(timeZones).map((array) => {
        const option = document.createElement("option");
        option.value = array[0];
        option.label = array[1];
        return option;
      });
      timeZoneSel.append(...tzOptions);
    });
});