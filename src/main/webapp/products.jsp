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
        <h2>> <a href="/">Home</a></h2>
        <hr/>
        <h3>Products from db</h3>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Title</th>
                <th>Market Place</th>
                <th>Delivery Service</th>
                <th>Payment Method</th>
                <th>Notes</th>
                <th>Order Status</th>
                <th>Profit<br>Price</th>
                <th>Payout</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tr>
                <jsp:useBean id="stats" class="org.saleslist.jdbc.util.Stats"/>
                <td></td>
                <td>Total products = ${stats.totalPositions}</td>
                <td>
                    <c:forEach var="entry" items="${stats.marketPlaceCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="entry" items="${stats.deliveryCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="entry" items="${stats.paymentMethodCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </td>
                <td></td>
                <td>
                    <c:forEach var="entry" items="${stats.statusOrderCounterMap}">
                        <c:out value="${entry.key}"/>=<c:out value="${entry.value}"/><br>
                    </c:forEach>
                </td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.totalProfit}"/>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.totalPrice}"/>
                </td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${stats.totalPayouts}"/>
                </td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" type="org.saleslist.jdbc.model.Product"/>
                <tr data-payoutPercentage="${product.payoutPercentage > 0}">
                    <td>${product.dateTime.toLocalDate()}, ${product.dateTime.toLocalTime()}</td>
                    <td>${product.title}</td>
                    <td>${product.marketPlace}</td>
                    <td>${product.deliveryService}</td>
                    <td>${product.paymentMethod}</td>
                    <td>${product.notes}</td>
                    <td>${product.orderStatus}</td>
                    <td>
                        <c:choose>
                            <c:when test="${product.payoutPercentage > 0}">
                                <fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${product.soldAtPrice - product.soldAtPrice * (product.payoutPercentage / 100.0)}"/><br>
                            </c:when>
                        </c:choose>
                        <fmt:formatNumber type="number" maxFractionDigits="2"
                                          value="${product.soldAtPrice}"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${product.payoutPercentage > 0}">
                                <fmt:formatNumber type="number" maxFractionDigits="2"
                                                  value="${product.soldAtPrice * (product.payoutPercentage / 100.0)}"/>
                                (${product.payoutPercentage}%)
                            </c:when>
                            <c:otherwise>
                                ${product.payoutPercentage}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="products?action=update&id=${product.id}">✏️</a></td>
                    <td><a href="products?action=delete&id=${product.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <hr>
    <a href="products?action=create">Add product</a>
</body>
</html>
