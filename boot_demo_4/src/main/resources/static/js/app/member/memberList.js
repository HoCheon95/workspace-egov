
function makeTr(member) {
    const tr = document.createElement("tr");
    const td1 = document.createElement("td");
    td1.innerHTML = member.rnum;
    const td2 = document.createElement("td");
    td2.innerHTML = member.memId;
    const td3 = document.createElement("td");
    td3.innerHTML = member.memName;
    const td4 = document.createElement("td");
    td4.innerHTML = member.memHp;
    const td5 = document.createElement("td");
    td5.innerHTML = member.memMail;
    const td6 = document.createElement("td");
    td6.innerHTML = member.memAdd1;
    const td7 = document.createElement("td");
    td7.innerHTML = member.memRoles;
    tr.append(td1, td2, td3, td4, td5, td6, td7);
    return tr;
}
document.addEventListener("DOMContentLoaded", async () => {
    const searchForm = document.getElementById("searchForm1");
    searchForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const params = new URLSearchParams(formData);
        const url = "/admin/memberList/type-async";
        const resp = await fetch(`${url}?${params}`, {
        headers: { 
                Accept: "application/json" 
            }
        });
        const {dataList, pagingHtml} = await resp.json();
        let trs = null;
        if (dataList && dataList.length > 0) {
                trs = dataList.map(makeTr);
        } else {
            const tr = document.createElement("tr");
            const td = document.createElement("td");
            td.colSpan = 7;
            td.innerHTML = "회원 없음";
            tr.append(td);
            trs = [tr];
        }
        const listBody = document.getElementById("list-body");
        const pagingArea = document.getElementById("paging-area");
        listBody.replaceChildren();
        listBody.append(...trs);
        pagingArea.innerHTML = pagingHtml;
        searchForm.page.value = "";

    });

    searchForm.requestSubmit();
});
