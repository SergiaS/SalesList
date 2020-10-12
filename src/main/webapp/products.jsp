<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>Products list</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <p>All products in db.</p>

    <section>
        <h3>> <a href="/">Home</a></h3>
        <hr/>
        <h2>Products</h2>
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
                <th>Price</th>
                <th>Payout %</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
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
                    <td>${product.soldAtPrice}</td>
                    <td>${product.payoutPercentage}</td>
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
