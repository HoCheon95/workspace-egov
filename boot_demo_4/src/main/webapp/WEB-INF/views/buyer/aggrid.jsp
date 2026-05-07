<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ag grid</title>
    <script src="https://cdn.jsdelivr.net/npm/ag-grid-community/dist/ag-grid-community.min.js"></script>

</head>
<body>
    <div id="myGrid" style="height: 500px"></div>


<script>
// Grid Options: Contains all of the Data Grid configurations
const gridOptions = {
    // Row Data: The data to be displayed.
    rowData: [
        { make: "Tesla", model: "Model Y", price: 64950, electric: true },
        { make: "Ford", model: "F-Series", price: 33850, electric: false },
        { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    ],
    // Column Definitions: Defines the columns to be displayed.
    columnDefs: [
        { field: "buyerName" },
        { field: "lprod.lprodName" },
        { field: "buyerAdd1" },
        { field: "buyerCharger" },
        { field: "buyerMail" }
    ],
    paginationAutoPageSize: true,
    pagination: true,
};


// Create Grid: Create new grid within the #myGrid div, using the Grid Options object
let gridApi = agGrid.createGrid(document.querySelector("#myGrid"), gridOptions);

// Fetch Remote Data
fetch("/buyer/list", {
    headers: {
        "Accept": "application/json"
    }   
})
  .then((response) => response.json())
  .then((data) => gridApi.setGridOption("rowData", data));
</script>
</body>
</html>