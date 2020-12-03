<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Payout Form</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="index.jsp">Home</a></h2>
        <hr>
        <h1 style="text-align: center">${param.action == 'create' ? 'Make new payout' : 'Edit'}</h1>
        <jsp:useBean id="payout" type="org.saleslist.model.Payout" scope="request"/>
        <form method="post" action="payouts">
            <input type="hidden" name="id" value="${payout.id}">
<%--            <input type="hidden" name="productId" value="${payout.userId}">--%>
            <dl>
                <dt>DateTime:</dt>
                <dd>
                    <label>
                        <input type="datetime-local" value="${payout.dateTime}" name="dateTime" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Amount:</dt>
                <dd>
                    <label>
                        <input type="text" value="${payout.amount}" name="amount" required>
                    </label>
                    <span class="asterisk">*</span>
                </dd>
            </dl>
            <dl>
                <dt>Notes:</dt>
                <dd>
                    <label>
                        <textarea cols=40 name="notes" autocomplete="on" required> <c:out
                                value="${param.action == 'create' ? 'Выплата' : payout.notes}"/> </textarea>
                    </label>
                </dd>
            </dl>
            <button type="submit">Save ✔️</button>
            <button onclick="window.history.back()" type="button">Cancel ❌</button>
        </form>
    </section>
</body>
</html>
