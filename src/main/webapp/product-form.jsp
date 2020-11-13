<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Form</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="index.html">Home</a></h2>
        <hr>
        <h1>${param.action == 'create' ? 'Add Product' : 'Edit Product'}</h1>
        <hr>
        <jsp:useBean id="product" type="org.mySells.model.Product" scope="request"/>
        <form method="post" action="products">
            <input type="hidden" name="id" value="${product.id}">
            <dl>
                <dt>Date Time:</dt>
                <dd><input type="datetime-local" name="dateTime" value="${product.dateTime}" required></dd>
            </dl>
            <dl>
                <dt>Title:</dt>
                <dd><input type="text" name="title" size="100" value="${product.title}"></dd>
            </dl>
            <dl>
                <dt>Market Place:</dt>
                <dd>
                    <label>
                        <select name="marketPlace" required>
                            <c:if test="${product.marketPlace == null}">
                                <option selected hidden value=""></option>
                            </c:if>
                            <c:forEach items="${marketPlace}" var="market">
                                <option ${market == product.marketPlace ? "selected" : ""}
                                        value="${market}">${market}</option>
                            </c:forEach>
                        </select>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Delivery Service:</dt>
                <dd>
                    <label>
                        <select name="deliveryService" required>
                            <c:if test="${product.deliveryService == null}">
                                <option selected hidden value=""></option>
                            </c:if>
                            <c:forEach items="${deliveryService}" var="delivery">
                                <option ${delivery == product.deliveryService ? "selected" : ""}
                                        value="${delivery}">${delivery}</option>
                            </c:forEach>
                        </select>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Payment Method:</dt>
                <dd>
                    <label>
                        <select name="paymentMethod" required>
                            <c:if test="${product.paymentMethod == null}">
                                <option selected hidden value=""></option>
                            </c:if>
                            <c:forEach items="${paymentMethod}" var="payment">
                                <option ${payment == product.paymentMethod ? "selected" : ""}
                                        value="${payment}">${payment}</option>
                            </c:forEach>
                        </select>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Order Status:</dt>
                <dd>
                    <label>
                        <select name="orderStatus" required>
                            <c:if test="${product.orderStatus == null}">
                                <option selected hidden value=""></option>
                            </c:if>
                            <c:forEach items="${orderStatus}" var="status">
                                <option ${status == product.orderStatus ? "selected" : ""}
                                        value="${status}">${status}</option>
                            </c:forEach>
                        </select>
                    </label>
                </dd>
            </dl>
                <dl>
                    <dt>Sold At Price:</dt>
                    <dd>
                        <label>
                            <input type="text" value="${product.soldAtPrice > 0 ? product.soldAtPrice : 0}" name="price"
                                   placeholder="How much you got for it?" size="5" required>
                        </label>
                    </dd>
                </dl>
                <dl>
                    <dt>Spend:</dt>
                    <dd>
                        <label>
                            <input type="text" value="${product.spent > 0 ? product.spent : 0}" name="spent"
                                   placeholder="How much did it cost?"
                                   size="5" required>
                        </label>
                    </dd>
                </dl>
                <dl>
                    <dt>Payout Percentage, %:</dt>
                    <dd>
                        <label>
                            <input type="text" value="${product.payoutPercentage > 0 ? product.payoutPercentage : 0}"
                                   name="payoutPercentage" placeholder="Payout" size="5" required>
                        </label>
                        <c:choose>
                            <c:when test="${param.action != 'create'}">
                                <label>= ₴${product.payoutCurrency}</label>
                            </c:when>
                        </c:choose>
                    </dd>
                </dl>
                <dl>
                    <dt>Notes:</dt>
                    <dd><input type="text" name="notes" size="50" value="${product.notes}"></dd>
                </dl>
                <button type="submit">Save</button>
                <button onclick="window.history.back()" type="button">Cancel</button>
            </form>


<%--            <table class="forms">--%>
<%--                <tr>--%>
<%--                    <th>Spent, UAH:</th>--%>
<%--                    <td>--%>
<%--                        <label>--%>

<%--                        </label>--%>
<%--                        <span class="asterisk">*</span>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>Sold At Price, UAH:</th>--%>
<%--                    <td>--%>
<%--                        <label>--%>

<%--                        </label>--%>
<%--                        <span class="asterisk">*</span>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>Payout Percentage, %:</th>--%>
<%--                    <td>--%>
<%--                        <label>--%>

<%--                        </label>--%>
<%--                        <span class="asterisk">*</span>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>Notes:</th>--%>
<%--                    <td>--%>
<%--                        <label>--%>
<%--                            <textarea cols=40 name="notes" autocomplete="on"> <c:out--%>
<%--                                    value="${product.notes}"/> </textarea>--%>
<%--                        </label>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th></th>--%>
<%--                    <td>--%>
<%--                        <button type="submit">Save</button>--%>
<%--                        <button onclick="window.history.back()" type="button">Cancel</button>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
    </section>
</body>
</html>
