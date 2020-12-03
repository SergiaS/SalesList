<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Products list</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="index.jsp">Home</a></h2>
        <h3>> <a href="/payouts">Show all payouts</a></h3>
        <hr>
        <form method="post" action="users">
            <b>Products from db of </b>
            <select onchange="this.form.submit()" name="userId">
                <option value="100" ${userId == 100 ? "selected" : ""}>ADMIN</option>
                <option value="101" ${userId == 101 ? "selected" : ""}>JAG63</option>
                <option value="102" ${userId == 102 ? "selected" : ""}>CAT66</option>
                <option value="103" ${userId == 103 ? "selected" : ""}>JUV91</option>
                <option value="104" ${userId == 104 ? "selected" : ""}>SK88</option>
            </select>
        </form>
        <c:choose>
            <c:when test="${userId != 100}">
                <a href="products?action=create"><img
                        src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png"
                        width="30" height="30" alt="add"></a>
            </c:when>
        </c:choose>
        <table id="stats">
            <thead>
                <tr>
                    <th>№</th>
                    <c:choose>
                        <c:when test="${userId == 100}">
                            <th>Owner</th>
                        </c:when>
                    </c:choose>
                    <th>Date</th>
                    <th>Title</th>
                    <th>Market Place</th>
                    <th>Delivery Service</th>
                    <th>Payment Method</th>
                    <th>Order Status</th>
                    <th>Sold At Price</th>
                    <th>Spent</th>
                    <th>Payout</th>
                    <th>Profit</th>
                    <th>Notes</th>
                    <c:choose>
                        <c:when test="${userId != 100}">
                            <th>Edit</th>
                        </c:when>
                    </c:choose>
                    <th>Delete</th>
                </tr>
            </thead>
            <tr>
                <th>${products.size()}</th>
                <c:choose>
                    <c:when test="${userId == 100}">
                        <th id="stats-owners"></th>
                    </c:when>
                </c:choose>
                <th></th>
                <th id="stats-total">
<%--                    Total Items Sold: ${products.size()}<br>--%>
<%--                    Sold Own Items: ${stats.numberOfMyItems}<br>--%>
<%--                    Sold Cooperation Items: ${stats.numberOfCooperationItems}--%>
                </th>
                <th id="stats-marketplace"></th>
                <th id="stats-delivery"></th>
                <th id="stats-paymentMethod"></th>
                <th id="stats-orderStatus"></th>
                <th id="stats-amountAtSoldPrice"></th>
                <th id="stats-amountOfExpenses"></th>
                <th id="stats-amountOfPayouts"></th>
                <th id="stats-amountOfProfit"></th>
                <th></th>
                <c:choose>
                    <c:when test="${userId != 100}">
                        <th></th>
                    </c:when>
                </c:choose>
                <th></th>
            </tr>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" type="org.saleslist.model.Product"/>
                <tr data-payoutPercentage="${product.payoutPercentage > 0}">
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <c:out value="${count}"/>
                    </td>
                    <c:choose>
                        <c:when test="${userId == 100}">
                            <td>
                                <c:out value="${owners[count-1]}"/>
                            </td>
                        </c:when>
                    </c:choose>
                    <td>${product.dateTime.toLocalDate()}, ${product.dateTime.toLocalTime()}</td>
                    <td class="long-names">${product.title}</td>
                    <td>${product.marketPlace}</td>
                    <td>${product.deliveryService}</td>
                    <td>${product.paymentMethod}</td>
                    <td>${product.orderStatus}</td>
                    <td>${product.soldAtPrice}</td>
                    <td>${product.spent}</td>
                    <td>
                        <c:choose>
                            <c:when test="${product.payoutPercentage > 0}">
                                ${product.payoutCurrency}
                                (${product.payoutPercentage}%)
                            </c:when>
                            <c:otherwise>
                                ${product.payoutPercentage}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${product.profit}</td>
                    <td style="white-space: pre">${product.notes}</td>
                    <c:choose>
                        <c:when test="${userId != 100}">
                            <td><a href="products?action=update&id=${product.id}">✏️</a></td>
                        </c:when>
                    </c:choose>
                    <td><a href="products?action=delete&id=${product.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <c:choose>
        <c:when test="${userId != 100}">
            <a href="products?action=create"><img
                    src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png"
                    width="30" height="30" alt="add"></a>
        </c:when>
    </c:choose>
    <script src="resources/js/stats-products.js"></script>
</body>
</html>
