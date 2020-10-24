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
        <h2>> <a href="/">Home</a></h2>
        <hr>
        <span>Payouts from payouts db.</span><br>
        <a href="payouts?action=create">Make payout</a>
        <hr>
        <table border="1" cellpadding="4" cellspacing="0">
            <tr>
                <th>№</th>
                <th>Date</th>
                <th>Amount</th>
                <th>Notes</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <tr>
                <jsp:useBean id="payoutsStats" scope="request" type="org.saleslist.jdbc.util.PayoutsStats"/>
                <td></td>
                <td></td>
                <td>
                    <span style="color: blue"><b>Cooperations: <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                                 groupingUsed="false"
                                                                                 value="${payoutsStats.cooperationsAmount}"/></b></span><br>
                    <span style="color: green"><b>Payouts: <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                             groupingUsed="false"
                                                                             value="${payoutsStats.payoutsAmount}"/></b></span><br>
                    <span style=${payoutsStats.totalAmount < 0 ? '"color: red"' : '"color: purple"'}><b>Pay: <fmt:formatNumber
                            type="number" maxFractionDigits="2" groupingUsed="false"
                            value="${payoutsStats.totalAmount}"/></b></span>
                </td>
                <td>
                    <span style="color: blue"><b>Qty Cooperations: ${payoutsStats.qtyCooperations}</b></span><br>
                    <span style="color: green"><b>Qty Payouts: ${payoutsStats.qtyPayouts}</b></span><br>
                    <span><b>Total Qty: ${payoutsStats.qtyCooperations + payoutsStats.qtyPayouts}</b></span>
                </td>
                </b>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${payouts}" var="payout">
                <jsp:useBean id="payout" type="org.saleslist.jdbc.model.Payout"/>
                <tr data-payoutPercentage="${payout.amount < 0}">
                    <td>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <c:out value="${count}"/>
                    </td>
                    <td>${payout.dateTime.toLocalDate()}, ${payout.dateTime.toLocalTime()}</td>
                    <td>
                        <fmt:formatNumber type="number" maxFractionDigits="2" groupingUsed="false"
                                          value="${payout.amount}"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${payout.amount < 0}">
                                <a href="products?action=update&id=${payout.productId}">${payout.notes}</a>
                            </c:when>
                            <c:otherwise>
                                ${payout.notes}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="payouts?action=update&id=${payout.id}">✏️</a></td>
                    <td><a href="payouts?action=delete&id=${payout.id}">❌</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
    <hr>
    <a href="payouts?action=create">Make payout</a>
</body>
</html>
