function runPagination(page, size, pageOperator) {
    let pageData = document.getElementById('pageData');
    const sort = pageData.getAttribute('data-sort');
    const order = pageData.getAttribute('data-order');
    submitRequest(sort, order, page + pageOperator, size);
}

function runSort(sort, order) {
    if (order === 'desc') {
        order = 'asc';
    } else {
        order = 'desc';
    }
    let pageData = document.getElementById('pageData');
    const page = pageData.getAttribute('data-page');
    const size = pageData.getAttribute('data-size');
    submitRequest(sort, order, page, size);
}

function submitRequest(sort, order, page, size) {
    let personalSearchSubmit = document.getElementById('personalSearchSubmit');
    if (personalSearchSubmit !== null) {
        let personalSearch = document.getElementById('personalSearch');
        if (personalSearch !== null) {
            let input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "sort");
            input.setAttribute("value", sort);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "order");
            input.setAttribute("value", order);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "page");
            input.setAttribute("value", page);
            personalSearch.appendChild(input);
            input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "size");
            input.setAttribute("value", size);
            personalSearch.appendChild(input);
            personalSearchSubmit.click();
        }
    }
}
