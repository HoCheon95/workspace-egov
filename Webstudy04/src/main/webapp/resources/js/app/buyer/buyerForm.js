/**
 * 판매처 등록 폼에서 사용할 스크립트
 */
document.addEventListener("DOMContentLoaded", async () => {
  const select = document.getElementById("lprodGu");
  const CONTEXTPATH = document.body.dataset["cpath"];
  const resp = await fetch(`${CONTEXTPATH}/lprod/list`);
  if (resp.ok) {
    const lprodList = await resp.json();
    select.innerHTML = lprodList.map(lprod=>`<option value="${lprod.lprodGu}">${lprod.lprodName}</option>`)
                                .join("\n");
  }
});
