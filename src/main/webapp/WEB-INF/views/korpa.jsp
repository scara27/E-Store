<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Vaša korpa</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Vaša korpa</h2>

    <c:if test="${not empty poruka}">
        <div class="alert alert-success mt-3">
                ${poruka}
        </div>
    </c:if>

    <c:if test="${not empty grupisanaKorpa}">
        <table class="table table-bordered table-striped">
            <thead class="table-light">
            <tr>
                <th>Naziv</th>
                <th>Kolicina</th>
                <th>Cena po komadu</th>
                <th>Ukupno</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${grupisanaKorpa}" var="entry">
                <tr>
                    <td>${entry.key.naziv}</td>
                    <td>${entry.value}</td>
                    <td><fmt:formatNumber value="${entry.key.cena}" type="number" minFractionDigits="2" maxFractionDigits="2"/> RSD</td>
                    <td><fmt:formatNumber value="${entry.key.cena * entry.value}" type="number" minFractionDigits="2" maxFractionDigits="2"/> RSD</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p class="fw-bold">Ukupna cena: <fmt:formatNumber value="${ukupnaCena}" type="number" minFractionDigits="2" maxFractionDigits="2"/> RSD</p>

        <form method="post" action="/korpa/naruci" style="display: inline-block;">
            <button type="submit" class="btn btn-primary">Naruci</button>
        </form>

        <form method="post" action="/korpa/isprazni" style="display: inline-block; margin-left: 10px;">
            <button type="submit" class="btn btn-danger">Isprazni korpu</button>
        </form>

    </c:if>

    <c:if test="${empty grupisanaKorpa}">
        <div class="alert alert-info">Vasa korpa je prazna.</div>
    </c:if>
</div>

</body>
</html>
