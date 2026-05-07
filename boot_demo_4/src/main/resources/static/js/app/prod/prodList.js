const searchForm = document.getElementById("searchForm1");

const fnPaging = (page) => {
    searchForm.page.value = page;
    searchForm.requestSubmit();
}