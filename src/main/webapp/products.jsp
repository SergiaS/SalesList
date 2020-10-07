<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>Products list</title>
<%--    <jsp:include page="resources/css/styles.css"/>--%>
    <link rel="stylesheet" type="text/css" href="resources/css/styles.css">
</head>
<body>
    <p>All products in db.</p>

    <section>
        <h3>>>> <a href="/">Home</a></h3>
        <hr/>
        <h2>Products</h2>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Title</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" type="org.saleslist.jdbc.model.Product"/>
                <tr data-payoutPercentage="${product.payoutPercentage > 0}">
<%--                <tr class="${product.payoutPercentage > 0 ? 'con' : 'my'}">--%>
                    <td>
                        ${product.dateTime}
<%--                            ${product.dateTime.toLocalDate()} ${product.dateTime.toLocalTime()}--%>
<%--                            <%=TimeUtil.toString(product.getDateTime())%>--%>
<%--                            ${fn:replace(product.dateTime, 'T', ' ')}--%>
<%--                            ${fn:formatDateTime(product.dateTime)}--%>
                    </td>
                    <td>${product.title}</td>
                    <td>${product.soldAtPrice}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
</body>
</html>
