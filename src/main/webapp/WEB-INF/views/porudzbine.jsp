<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Moje porudžbine</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Moje porudzine</h2>

    <c:if test="${!empty poruka}">
        <div class="alert alert-info">${poruka}</div>
    </c:if>

    <c:if test="${not empty porudzbine}">
        <table class="table table-bordered table-striped">
            <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Datum</th>
                <th>Status</th>
                <th>Ukupna cena</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${porudzbine}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.datum}</td>
                    <td>${p.status}</td>
                    <td><fmt:formatNumber value="${p.ukupnaCena}" type="number" minFractionDigits="2" maxFractionDigits="2"/> RSD</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty porudzbine}">
        <div class="alert alert-info">Nemate nijednu porudžbinu.</div>
    </c:if>
</div>

</body>
</html>
