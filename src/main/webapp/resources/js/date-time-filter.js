function stepBack() {
    let date = getExistDate();

    let from = new Date(Date.UTC(date.getFullYear(), date.getMonth() - 1, 1, 0, 0, 0, 0));
    console.log(from.toISOString().slice(0, 10));

    let to = new Date(Date.UTC(date.getFullYear(), date.getMonth(), 0, 0, 0, 0, 0));
    console.log(to.toISOString().slice(0, 10));

    document.getElementById('startDateId').value = from.toISOString().slice(0, 10);
    document.getElementById('endDateId').value = to.toISOString().slice(0, 10);
}

function stepForward() {
    let date = getExistDate();

    let from = new Date(Date.UTC(date.getFullYear(), date.getMonth() + 1, 1, 0, 0, 0, 0));
    console.log(from.toISOString().slice(0, 10));

    let to = new Date(Date.UTC(date.getFullYear(), date.getMonth() + 2, 0, 0, 0, 0, 0));
    console.log(to.toISOString().slice(0, 10));

    document.getElementById('startDateId').value = from.toISOString().slice(0, 10);
    document.getElementById('endDateId').value = to.toISOString().slice(0, 10);
}

function getExistDate() {
    let isAdminPage = document.getElementsByTagName("select").userId[0].getAttribute("selected") != null ? 1 : 0;
    let queryStr = window.location.search;
    let urlParams = new URLSearchParams(queryStr);
    return new Date(urlParams.get('startDate') !== null
        ? urlParams.get('startDate')
        : document.getElementById("stats").rows[2].cells[1+isAdminPage].innerHTML);
}
