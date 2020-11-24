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
        <hr>
        <form method="post" action="users">
            <b>Products from db of </b>
            <select name="userId">
                <option value="100" ${userId == 100 ? "selected" : ""}>ADMIN</option>
                <option value="101" ${userId == 101 ? "selected" : ""}>JAG63</option>
                <option value="102" ${userId == 102 ? "selected" : ""}>CAT66</option>
                <option value="103" ${userId == 103 ? "selected" : ""}>JUV91</option>
                <option value="104" ${userId == 104 ? "selected" : ""}>SK88</option>
            </select>
            <button type="submit">Show</button>
        </form>
        <c:choose>
            <c:when test="${mode != 'admin'}">
                <a href="products?action=create"><img src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png" width="30" height="30" alt="add"></a>
            </c:when>
        </c:choose>
        <table>
            <thead>
            <tr>
                <th>№</th>
                <c:choose>
                    <c:when test="${mode == 'admin'}">
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
                    <c:when test="${mode != 'admin'}">
                        <th>Edit</th>
                    </c:when>
                </c:choose>
                <th>Delete</th>
            </tr>
            </thead>

            <tr>
                <%--                <jsp:useBean id="stats" class="org.saleslist.util.Stats"/>--%>
                <th></th>
                <c:choose>
                    <c:when test="${mode == 'admin'}">
                        <th></th>
                    </c:when>
                </c:choose>
                <th></th>
                <th>
                    Total Sold Items: ${stats.numberOfSoldItems}<br>
                    Sold Cooperation Items: ${stats.numberOfCooperationItems}<br>
                    Sold Own Items: ${stats.numberOfMyItems}
                </th>
                <th>
                    <c:forEach var="entry" items="${stats.marketPlaceCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </th>
                <th>
                    <c:forEach var="entry" items="${stats.deliveryCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </th>
                <th>
                    <c:forEach var="entry" items="${stats.paymentMethodCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </th>
                <th>
                    <c:forEach var="entry" items="${stats.statusOrderCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </th>
                <th>${stats.amountAtSoldPrice}</th>
                <th>${stats.amountOfExpenses}</th>
                <th>${stats.amountOfPayouts}</th>
                <th>${stats.amountOfProfit}</th>
                <th></th>
                <c:choose>
                    <c:when test="${mode != 'admin'}">
                        <th></th>
                    </c:when>
                </c:choose>
                <th></th>
            </tr>


            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" type="org.saleslist.model.Product"/>
                <tr data-payoutPercentage="${product.payoutPercentage > 0}">
                        <%--                <tr>--%>
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <c:out value="${count}"/>
                    </td>
                    <c:choose>
                        <c:when test="${mode == 'admin'}">
                            <td>
                                <c:out value="${owners[count-1]}"/>
                            </td>
                        </c:when>
                    </c:choose>
                    <td style="white-space: nowrap">${product.dateTime.toLocalDate()}, ${product.dateTime.toLocalTime()}</td>
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
                                <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"
                                                  value="${product.payoutCurrency}"/>
                                (${product.payoutPercentage}%)
                            </c:when>
                            <c:otherwise>
                                ${product.payoutPercentage}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"
                                          value="${product.profit}"/>
                    </td>
                    <td>${product.notes}</td>
                    <c:choose>
                        <c:when test="${mode != 'admin'}">
                            <td><a href="products?action=update&id=${product.id}">✏️</a></td>
                        </c:when>
                    </c:choose>
                    <td><a href="products?action=delete&id=${product.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>

    <c:choose>
        <c:when test="${mode != 'admin'}">
            <a href="products?action=create"><img src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png" width="30" height="30" alt="add"></a>
        </c:when>
    </c:choose>
</body>
</html>
