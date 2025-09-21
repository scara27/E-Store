<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>${proizvod.naziv}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <img src="${proizvod.slika}" class="img-fluid" alt="${proizvod.naziv}"
                 style="max-height: 550px; object-fit: contain;">
        </div>
        <div class="col-md-6">
            <h2>${proizvod.naziv}</h2>
            <p><strong>Cena:</strong> <fmt:formatNumber value="${proizvod.cena}" groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" /> RSD</p>
            <p><strong>Kategorija:</strong> ${proizvod.kategorija}</p>
            <p><strong>Proizvođac:</strong> ${proizvod.proizvodjac}</p>
            <p>${proizvod.opis}</p>

            <c:if test="${ulogovan}">
                <a href="/proizvodi/${proizvod.id}/naruci" class="btn btn-primary">Naruci</a>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
