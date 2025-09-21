<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Početna</title>
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/> -->

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
        }

        .main-content {
            flex: 1;
        }
    </style>
</head>
<body class="bg-light">

<jsp:include page="partials/nav.jsp" />



<!-- Glavni sadržaj -->
<div class="container mt-5 main-content">
    <h2 class="text-center mb-4">Svi proizvodi</h2>

    <div class="row">
        <h2 class="text-center mb-4">Pretraga proizvoda</h2>

        <c:if test="${not empty poruka}">
            <div class="container mt-3">
                <div class="alert alert-info">${poruka}</div>
            </div>
        </c:if>

        <form method="get" action="/proizvodi/prikazProizvoda" class="row g-3 mb-4">
            <div class="col-md-4">
                <label for="kategorija" class="form-label">Kategorija</label>
                <select id="kategorija" name="kategorija" class="form-select">
                    <option value="">Sve kategorije</option>
                    <c:forEach items="${kategorije}" var="kat">
                        <option value="${kat.naziv}"
                                <c:if test="${kat.naziv == odabranaKategorija}">selected</c:if>>
                                ${kat.naziv}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-4">
                <label for="cenaDo" class="form-label">Cena do</label>
                <input type="number" id="cenaDo" name="cenaDo" class="form-control"
                       placeholder="npr. 5000" value="${odabranaCenaDo != null ? odabranaCenaDo : ''}" />
            </div>

            <div class="col-md-4">
                <label for="sort" class="form-label">Sortiraj</label>
                <select id="sort" name="sort" class="form-select">
                    <option value="" <c:if test="${odabraniSort == '' || odabraniSort == null}">selected</c:if>>Bez sortiranja</option>
                    <option value="cena_rastuce" <c:if test="${odabraniSort == 'cena_rastuce'}">selected</c:if>>Cena (rastuće)</option>
                    <option value="cena_opadajuce" <c:if test="${odabraniSort == 'cena_opadajuce'}">selected</c:if>>Cena (opadajuće)</option>
                    <option value="naziv_az" <c:if test="${odabraniSort == 'naziv_az'}">selected</c:if>>Naziv A-Z</option>
                    <option value="naziv_za" <c:if test="${odabraniSort == 'naziv_za'}">selected</c:if>>Naziv Z-A</option>
                </select>
            </div>

            <div class="col-md-12 text-end">
                <button type="submit" class="btn btn-primary">Filtriraj</button>
            </div>
        </form>
    </div>

    <!-- Lista proizvoda -->
    <div class="row" id="proizvodiContainer">
        <c:forEach items="${proizvodi}" var="proizvod">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <img src="${proizvod.slika}" class="card-img-top" alt="${proizvod.naziv}" style="max-height: 200px; object-fit: contain;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">
                            <a href="/proizvodi/${proizvod.id}" class="text-decoration-none">
                                    ${proizvod.naziv}
                            </a>
                        </h5>
                        <p class="card-text">
                            <strong>Cena:</strong>
                            <fmt:formatNumber value="${proizvod.cena}" type="currency" currencySymbol="" groupingUsed="true" maxFractionDigits="2" /> RSD<br/>
                            <strong>Proizvođač:</strong> ${proizvod.proizvodjac}<br/>
                            <strong>Kategorija:</strong> ${proizvod.kategorija}<br/>
                        </p>

                        <!-- Prikaz dugmeta "Uredi" samo za prodavca -->
                        <c:if test="${ulogovaniTip == 'ROLE_PRODAVAC'}">
                            <a href="/proizvodi/uredi/${proizvod.id}" class="btn btn-sm btn-outline-primary mt-auto">
                                Uredi
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>
