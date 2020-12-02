let isAdminPage = document.getElementsByTagName("select").userId[0].getAttribute("selected") != null ? 1 : 0;

// amount calculation
calculateAmount();

function calculateAmount() {
    let needToPay = 0;
    let payed = 0;

    let rowNumber = 2;
    let arrLength = document.getElementById("stats").rows.length;
    for (let i = rowNumber; i < arrLength; i++) {
        let tmpVal = +(document.getElementById("stats").rows[i].cells[2 + isAdminPage].innerHTML);
        if (tmpVal > 0) {
            needToPay += tmpVal;
        } else {
            payed += tmpVal;
        }
    }

    document.getElementById("stats-amount").innerHTML =
        "Cooperation: " + needToPay.toFixed(2)+ "<br>" +
        "Payed: " + payed.toFixed(2) + "<br>" +
        "Left: " + (payed + needToPay).toFixed(2);
}
