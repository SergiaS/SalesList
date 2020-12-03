<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Form</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <section>
        <h2>> <a href="index.jsp">Home</a></h2>
        <hr>
        <h1 style="text-align: center">${param.action == 'create' ? 'Add new product' : 'Edit product'}</h1>
        <jsp:useBean id="product" type="org.saleslist.model.Product" scope="request"/>
        <form method="post" action="products">
            <input type="hidden" name="id" value="${product.id}">
            <dl>
                <dt>DateTime:</dt>
                <dd>
                    <label>
                        <input type="datetime-local" value="${product.dateTime}" name="dateTime" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Title:</dt>
                <dd>
                    <label>
                        <input type="text" value="${product.title}" name="title" size="150" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>MarketPlace:</dt>
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
                <dt>DeliveryService:</dt>
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
                <dt>PaymentMethod:</dt>
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
                <dt>OrderStatus:</dt>
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
                <dt>SoldAtPrice:</dt>
                <dd>
                    <label>
                        <input type="text" value="${product.soldAtPrice > 0 ? product.soldAtPrice : 0}" name="soldAtPrice" size="5" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Spent:</dt>
                <dd>
                    <label>
                        <input type="text" value="${product.spent > 0 ? product.spent : 0}" name="spent" size="5" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Payout, %:</dt>
                <dd>
                    <label>
                        <input type="number" value="${product.payoutPercentage > 0 ? product.payoutPercentage : 0}" name="payoutPercentage" required>
                    </label>
                </dd>
            </dl>
            <dl>
                <dt>Notes:</dt>
                <dd>
                    <label>
                        <textarea cols=40 name="notes" autocomplete="on"> <c:out value="${product.notes}"/></textarea>
                    </label>
                </dd>
            </dl>
            <button type="submit">Save ✔️</button>
            <button onclick="window.history.back()" type="button">Cancel ❌</button>
        </form>
    </section>
</body>
</html>
