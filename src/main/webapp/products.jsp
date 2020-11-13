<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="https://github.com/SergiaS/SalesList" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <section>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>Products</h2>
        <form method="get" action="products">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dt>From Date (inclusive):</dt>
                <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
            </dl>
            <dl>
                <dt>To Date (inclusive):</dt>
                <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
            </dl>
            <dl>
                <dt>From Time (inclusive):</dt>
                <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
            </dl>
            <dl>
                <dt>To Time (exclusive):</dt>
                <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
            </dl>
            <button type="submit">Filter</button>
        </form>

        <hr>
        <a href="products?action=create">Add Product</a>
        <br><br>
        <table border="1" cellspacing="2" cellpadding="4">
            <thead>
            <tr>
                <th>№</th>
                <th>DateTime</th>
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
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <%--            stats block--%>
            <%--            <tr>--%>
            <%--                <jsp:useBean id="stats" class="org.mySells.util.Stats"/>--%>
            <%--                <th></th>--%>
            <%--                <th></th>--%>
            <%--                <th>--%>
            <%--                    Total Sold Items: ${stats.numberOfSoldItems}<br>--%>
            <%--                    Sold Cooperation Items: ${stats.numberOfCooperationItems}<br>--%>
            <%--                    Sold Own Items: ${stats.numberOfMyItems}--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <c:forEach var="entry" items="${stats.marketPlaceCounterMap}">--%>
            <%--                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>--%>
            <%--                    </c:forEach>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <c:forEach var="entry" items="${stats.deliveryCounterMap}">--%>
            <%--                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>--%>
            <%--                    </c:forEach>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <c:forEach var="entry" items="${stats.paymentMethodCounterMap}">--%>
            <%--                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>--%>
            <%--                    </c:forEach>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <c:forEach var="entry" items="${stats.statusOrderCounterMap}">--%>
            <%--                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>--%>
            <%--                    </c:forEach>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"--%>
            <%--                                      value="${stats.amountOfExpenses}"/>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"--%>
            <%--                                      value="${stats.amountAtSoldPrice}"/>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"--%>
            <%--                                      value="${stats.amountOfPayouts}"/>--%>
            <%--                </th>--%>
            <%--                <th>--%>
            <%--                    <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"--%>
            <%--                                      value="${stats.amountOfProfit}"/>--%>
            <%--                </th>--%>
            <%--                <th></th>--%>
            <%--                <th></th>--%>
            <%--            </tr>--%>

            <%--            <c:set var="count" value="0" scope="page" />--%>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" type="org.mySells.to.ProductTo"/>
                <tr data-payoutPercentage="${product.payoutPercentage > 0}">
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <c:out value="${count}"/>
                    </td>
                    <td style="white-space: nowrap">${product.dateTime.toLocalDate()} ${product.dateTime.toLocalTime()}</td>
                    <td>${product.title}</td>
                    <td>${product.marketPlace}</td>
                    <td>${product.deliveryService}</td>
                    <td>${product.paymentMethod}</td>
                    <td>${product.orderStatus}</td>
                    <td>${product.soldAtPrice}</td>
                    <td>${product.spent}</td>
                    <td style="white-space: nowrap">
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
                    <td>${product.notes}</td>
                    <td><a href="products?action=update&id=${product.id}">✏️</a></td>
                    <td><a href="products?action=delete&id=${product.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <hr>
    <a href="products?action=create">Add Product</a>
</body>
</html>
