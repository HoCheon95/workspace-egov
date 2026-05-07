document.addEventListener("DOMContentLoaded", async () => {
  const lprodGuSelect = document.getElementById("lprodGu");
  const buyerSelect = document.getElementById("buyerId");
  const selectedLprodGu = lprodGuSelect.dataset.selected || "";
  const selectedBuyerId = buyerSelect.dataset.selected || "";

  const loadBuyerOptions = async (lprodGu, selectedId = "") => {
    buyerSelect.innerHTML =
      '<option value="" disabled>제조사를 선택하세요</option>';
    if (!lprodGu) {
      buyerSelect.value = "";
      return;
    }

    const buyerResponse = await fetch(`/buyer/list/json?id=${lprodGu}`);
    const buyerList = await buyerResponse.json();
    buyerList.forEach((buyer) => {
      const option = document.createElement("option");
      option.value = buyer.buyerId;
      option.textContent = `${buyer.buyerId} - ${buyer.buyerName}`;
      buyerSelect.appendChild(option);
    });

    if (selectedId) {
      buyerSelect.value = selectedId;
    } else {
      buyerSelect.value = "";
    }
  };

  const lprodResponse = await fetch("/rest/lprods");
  const lprodList = await lprodResponse.json();
  lprodList.forEach((lprod) => {
    const option = document.createElement("option");
    option.value = lprod.lprodGu;
    option.textContent = `${lprod.lprodGu} - ${lprod.lprodName}`;
    lprodGuSelect.appendChild(option);
  });

  if (selectedLprodGu) {
    lprodGuSelect.value = selectedLprodGu;
    await loadBuyerOptions(selectedLprodGu, selectedBuyerId);
  } else {
    await loadBuyerOptions("");
  }

  lprodGuSelect.addEventListener("change", async () => {
    await loadBuyerOptions(lprodGuSelect.value);
  });
});
