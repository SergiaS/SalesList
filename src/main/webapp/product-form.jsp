<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product Form</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h3>> <a href="/">Home</a></h3>
        <hr>
        <h1 style="text-align: center">${param.action == 'create' ? 'Add new product' : 'Edit product'}</h1>
        <hr>
        <jsp:useBean id="product" type="org.saleslist.jdbc.model.Product" scope="request"/>

        <form method="post" action="products">
            <input type="hidden" name="id" value="${product.id}">
            <table class="product-table">
                <tr>
                    <th>DateTime:</th>
                    <td><input type="datetime-local" value="${product.dateTime}" name="dateTime" required></td>
                </tr>
                <tr>
                    <th>Title:</th>
                    <td>
                        <textarea cols=40 name="title" required> <c:out value="${product.title}"/> </textarea>
                    </td>
                </tr>
                <tr>
                    <th>Market Place:</th>
                    <td>
                        <select name="marketPlace" required>
                            <c:if test="${product.marketPlace == null}"> <option selected hidden value=""></option> </c:if>
                            <c:forEach items="${marketPlace}" var="market">
                                <option ${market == product.marketPlace ? "selected" : ""} value="${market}">${market}</option>
                            </c:forEach>
                        </select>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Delivery Service:</th>
                    <td>
                        <select name="deliveryService" required>
                            <c:if test="${product.deliveryService == null}"> <option selected hidden value=""></option> </c:if>
                            <c:forEach items="${deliveryService}" var="delivery">
                                <option ${delivery == product.deliveryService ? "selected" : ""} value="${delivery}">${delivery}</option>
                            </c:forEach>
                        </select>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Payment Method:</th>
                    <td>
                        <select name="paymentMethod" required>
                            <c:if test="${product.paymentMethod == null}"> <option selected hidden value=""></option> </c:if>
                            <c:forEach items="${paymentMethod}" var="payment">
                                <option ${payment == product.paymentMethod ? "selected" : ""} value="${payment}">${payment}</option>
                            </c:forEach>
                        </select>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Notes:</th>
                    <td>
                        <textarea cols=40 name="notes"> <c:out value="${product.notes}"/> </textarea>
                    </td>
                </tr>
                <tr>
                    <th>Order Status:</th>
                    <td>
                        <select name="orderStatus" required>
                            <c:if test="${product.orderStatus == null}"> <option selected hidden value=""></option> </c:if>
                            <c:forEach items="${orderStatus}" var="status">
                                <option ${status == product.orderStatus ? "selected" : ""} value="${status}">${status}</option>
                            </c:forEach>
                        </select>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Sold At Price, UAH:</th>
                    <td>
                        <input type="text" value="${product.soldAtPrice == 0 ? "" : product.soldAtPrice}" name="price" placeholder="Price" size="5" required>
                        <span class="asterisk">*</span>
                    </td>
                </tr>
                <tr>
                    <th>Payout Percentage, %:</th>
                    <td>
                        <input type="text" value="${product.payoutPercentage}" name="payout" placeholder="Payout Percentage" size="5" required>
                        <span class="asterisk">*</span>
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
