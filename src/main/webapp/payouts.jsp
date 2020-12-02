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
                <a href="payouts?action=create"><img src="https://icons.veryicon.com/png/o/commerce-shopping/merchant-product-icon-library/add-55.png" width="30" height="30" alt="add"></a>
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
            <tr>
                <th>${payouts.size()}</th>
                <c:choose>
                    <c:when test="${userId == 100}">
                        <th></th>
                    </c:when>
                </c:choose>
                <th></th>
                <th id="stats-amount"></th>
                <th></th>
                <c:choose>
                    <c:when test="${userId != 100}">
                        <th></th>
                    </c:when>
                </c:choose>
                <th></th>
            </tr>
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
                    <td>${payout.dateTime.toLocalDate()}, ${payout.dateTime.toLocalTime()}</td>
                    <td>${payout.amount}</td>
                    <td class="long-names">${payout.notes}
                    </td>
                    <c:choose>
                        <c:when test="${userId != 100}">
                            <td><a href="payouts?action=update&id=${payout.id}">✏️</a></td>
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
    <script src="resources/js/stats-payouts.js"></script>
</body>
</html>
