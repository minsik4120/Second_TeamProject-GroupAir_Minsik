var sortState = {}; // 열의 정렬 상태를 추적하는 객체

function sortTable(columnIndex) {
    var table, tbody, rows, switching, i, x, y, shouldSwitch, dir;
    table = document.getElementById("myTable");
    tbody = table.querySelector("tbody");
    switching = true;
    dir = "asc"; // 정렬 방향 초기값은 오름차순

    // 해당 열의 정렬 상태를 확인하고 업데이트
    if (!sortState[columnIndex]) {
        sortState[columnIndex] = "asc";
    } else {
        sortState[columnIndex] = sortState[columnIndex] === "asc" ? "desc" : "asc";
        dir = sortState[columnIndex]; // 열의 정렬 방향 업데이트
    }

    /* Make a loop that will continue until no switching has been done: */
    while (switching) {
        switching = false;
        rows = tbody.rows;
        /* Loop through all table rows (except the first, which contains table headers): */
        for (i = 0; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[columnIndex];
            y = rows[i + 1].getElementsByTagName("td")[columnIndex];
            if (dir === "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}
