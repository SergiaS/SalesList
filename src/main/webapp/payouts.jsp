<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Payouts list</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="index.jsp">Home</a></h2>
        <hr>
        <form method="post" action="users">
            <b>Payouts from db of </b>
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
            <c:when test="${userId != 100}">
                <a href="payouts?action=create"><img src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png" width="30" height="30" alt="add"></a>
            </c:when>
        </c:choose>
        <table>
            <thead>
                <tr>
                    <th>№</th>
                    <c:choose>
                        <c:when test="${userId == 100}">
                            <th>Owner</th>
                        </c:when>
                    </c:choose>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Notes</th>
                    <c:choose>
                        <c:when test="${userId != 100}">
                            <th>Edit</th>
                        </c:when>
                    </c:choose>
                    <th>Delete</th>
                </tr>
            </thead>
<%--            <tr>--%>
<%--                <jsp:useBean id="payoutsStats" scope="request" type="org.saleslist.util.PayoutsStats"/>--%>
<%--                <th></th>--%>
<%--                <th></th>--%>
<%--                <th>--%>
<%--                    <span style="color: blue"><b>Cooperations: <fmt:formatNumber type="number" maxFractionDigits="2"--%>
<%--                                                                                 groupingUsed="false"--%>
<%--                                                                                 value="${payoutsStats.cooperationsAmount}"/></b></span><br>--%>
<%--                    <span style="color: green"><b>Payouts: <fmt:formatNumber type="number" maxFractionDigits="2"--%>
<%--                                                                             groupingUsed="false"--%>
<%--                                                                             value="${payoutsStats.payoutsAmount}"/></b></span><br>--%>
<%--                    <span style=${payoutsStats.totalAmount < 0 ? '"color: red"' : '"color: purple"'}><b>Pay: <fmt:formatNumber--%>
<%--                            type="number" maxFractionDigits="2" groupingUsed="false"--%>
<%--                            value="${payoutsStats.totalAmount}"/></b></span>--%>
<%--                </th>--%>
<%--                <th>--%>
<%--                    <span style="color: blue"><b>Qty Cooperations: ${payoutsStats.qtyCooperations}</b></span><br>--%>
<%--                    <span style="color: green"><b>Qty Payouts: ${payoutsStats.qtyPayouts}</b></span><br>--%>
<%--                    <span><b>Total Qty: ${payoutsStats.qtyCooperations + payoutsStats.qtyPayouts}</b></span>--%>
<%--                </th>--%>
<%--                <th></th>--%>
<%--                <th></th>--%>
<%--            </tr>--%>
            <c:forEach items="${payouts}" var="payout">
                <jsp:useBean id="payout" type="org.saleslist.model.Payout"/>
                <tr data-payoutPercentage="${payout.amount < 0}">
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
                    <td style="white-space: nowrap">${payout.dateTime.toLocalDate()}, ${payout.dateTime.toLocalTime()}</td>
                    <td>${payout.amount}</td>
                    <td>${payout.notes}
<%--                        <c:choose>--%>
<%--                            <c:when test="${payout.amount < 0}">--%>
<%--                                <a href="products?action=update&id=${payout.productId}">${payout.notes}</a>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                                ${payout.notes}--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
                    </td>
                    <c:choose>
                        <c:when test="${userId != 100}">
                            <td><a href="payouts?action=update&id=${product.id}">✏️</a></td>
                        </c:when>
                    </c:choose>
                    <td><a href="payouts?action=delete&id=${payout.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <c:choose>
        <c:when test="${userId != 100}">
            <a href="payouts?action=create"><img src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png" width="30" height="30" alt="add"></a>
        </c:when>
    </c:choose>
</body>
</html>
