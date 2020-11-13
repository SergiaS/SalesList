<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Payout Form</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="/">Home</a></h2>
        <hr>
        <h1 style="text-align: center">${param.action == 'create' ? 'Make new payout' : 'Edit'}</h1>
        <hr>
        <jsp:useBean id="payout" type="org.mySells.jdbc.model.Payout" scope="request"/>
        <form method="post" action="payouts">
            <input type="hidden" name="id" value="${payout.id}">
            <input type="hidden" name="productId" value="${payout.productId}">
            <table class="forms">
                <tr>
                    <th>DateTime:</th>
                    <td>
                        <label><input type="datetime-local" value="${payout.dateTime}" name="dateTime" required></label>
                    </td>
                </tr>
                <tr>
                    <th>Amount:</th>
                    <td>
                        <label><input type="text" value="${payout.amount}" name="amount" required></label>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Notes:</th>
                    <td>
                        <label>
                            <textarea cols=40 name="notes" autocomplete="on" required> <c:out
                                    value="${param.action == 'create' ? 'Выплата' : payout.notes}"/> </textarea>
                        </label>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button type="submit">Save</button>
                        <button onclick="window.history.back()" type="button">Cancel</button>
                    </td>
                </tr>
            </table>
        </form>
    </section>
</body>
</html>
