let isAdminPage = document.getElementsByTagName("select").userId[0].getAttribute("selected") != null;


start();
function start() {
    let columnsForCalculatingOfValues = isAdminPage ? new Map([
        [1, "stats-owners"],
        [4, "stats-marketplace"],
        [5, "stats-delivery"],
        [6, "stats-paymentMethod"],
        [7, "stats-orderStatus"],
    ]) : new Map([
        [3, "stats-marketplace"],
        [4, "stats-delivery"],
        [5, "stats-paymentMethod"],
        [6, "stats-orderStatus"],
    ]);

    columnsForCalculatingOfValues.forEach((value, key, map) => {
        sumValuesInRows("rows", `${key}`, `${value}`);
    });


    let columnsForCalculatingSumOfNumbers = isAdminPage ? new Map([
        [8, "stats-amountAtSoldPrice"],
        [9, "stats-amountOfExpenses"],
        [10, "stats-amountOfPayouts"],
        [11, "stats-amountOfProfit"],
    ]) : new Map([
        [7, "stats-amountAtSoldPrice"],
        [8, "stats-amountOfExpenses"],
        [9, "stats-amountOfPayouts"],
        [10, "stats-amountOfProfit"],
    ]);

    columnsForCalculatingSumOfNumbers.forEach((value, key, map) => {
        sumValuesInRows("numbers", `${key}`, `${value}`);
    });


    function sumValuesInRows(calculateRowsOrNumbers = "rows", cellNumber = 0, outputId = "", rowNumber = 2, calculateInIdName = "stats") {

        let checkedArray = document.getElementById(calculateInIdName).rows;

        if (calculateRowsOrNumbers === "rows") {
            let tmpMap = new Map();
            for (let i = rowNumber; i < checkedArray.length; i++) {
                let currentValue = checkedArray[i].cells[cellNumber].innerHTML.trim();
                if (tmpMap.has(currentValue)) {
                    tmpMap.set(currentValue, tmpMap.get(currentValue) + 1);
                } else {
                    tmpMap.set(currentValue, 1);
                }
            }

            let sortedMapByValue = new Map([...tmpMap.entries()].sort((a, b) => b[1] - a[1]));

            let tmpAttach = "";
            sortedMapByValue.forEach((value, key, map) => {
                tmpAttach += `${key}: ${value}` + "<br>";
            });
            document.getElementById(outputId).innerHTML = tmpAttach;
        } else if (calculateRowsOrNumbers === "numbers") {
            let amount = +0;
            for (let i = rowNumber; i < checkedArray.length; i++) {
                let currentValue = parseFloat(checkedArray[i].cells[cellNumber].innerHTML.trim());
                amount += currentValue;
            }

            // "+" before amount deletes 0 at the end of number, x: 2.80 -> 2.8, 4.00 -> 4
            document.getElementById(outputId).innerHTML = +amount.toFixed(2);
        }
    }
}
