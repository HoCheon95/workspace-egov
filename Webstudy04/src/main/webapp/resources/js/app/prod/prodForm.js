/**
 * 제품 등록/수정 폼에서 사용할 스크립트
 * - 분류코드 목록 조회
 * - 거래처코드 목록 조회
 * - 수정 화면에서는 기존 lprodGu, buyerId 자동 선택
 */
document.addEventListener("DOMContentLoaded", async () => {
  const lprodSelect = document.getElementById("lprodGu");
  const buyerSelect = document.getElementById("buyerId");
  const CONTEXTPATH = document.body.dataset["cpath"];

  const selectedLprodGu = lprodSelect.dataset.selected;
  const selectedBuyerId = buyerSelect.dataset.selected;

  const lprodResp = await fetch(`${CONTEXTPATH}/lprod/list`);

  if (lprodResp.ok) {
    const lprodList = await lprodResp.json();

    lprodSelect.innerHTML =
      `<option value="">분류를 선택하세요</option>` +
      lprodList
        .map((lprod) => {
          const selected = lprod.lprodGu === selectedLprodGu ? "selected" : "";
          return `<option value="${lprod.lprodGu}" ${selected}>
                    ${lprod.lprodName} (${lprod.lprodGu})
                  </option>`;
        })
        .join("\n");
  }

  const buyerResp = await fetch(`${CONTEXTPATH}/buyer/list/json`);

  if (buyerResp.ok) {
    const buyerList = await buyerResp.json();

    buyerSelect.innerHTML =
      `<option value="">거래처를 선택하세요</option>` +
      buyerList
        .map((buyer) => {
          const selected = buyer.buyerId === selectedBuyerId ? "selected" : "";
          return `<option value="${buyer.buyerId}" ${selected}>
                    ${buyer.buyerName} (${buyer.buyerId})
                  </option>`;
        })
        .join("\n");
  }
});