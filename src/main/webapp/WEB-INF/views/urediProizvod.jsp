<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uredi proizvod</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Uredi proizvod</h2>

    <c:if test="${not empty poruka}">
        <div class="alert alert-info mt-3">${poruka}</div>
    </c:if>

    <form action="/proizvodi/uredi" method="post">
        <input type="hidden" name="id" value="${proizvod.id}"/>

        <div class="mb-3">
            <label class="form-label">Naziv</label>
            <input type="text" name="naziv" class="form-control" value="${proizvod.naziv}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Cena</label>
            <input type="number" name="cena" class="form-control" value="${proizvod.cena}" step="0.01" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Slika (URL)</label>
            <input type="text" name="slika" class="form-control" value="${proizvod.slika}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Proizvodjac</label>
            <input type="text" name="proizvodjac" class="form-control" value="${proizvod.proizvodjac}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Kolicina</label>
            <input type="number" name="kolicina" class="form-control" value="${proizvod.kolicina}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Opis</label>
            <textarea name="opis" class="form-control" required>${proizvod.opis}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Kategorija</label>
            <select name="kategorijaId" class="form-select" required>
                <c:forEach items="${kategorije}" var="kat">
                    <option value="${kat.id}" ${kat.id == proizvod.kategorija.id ? 'selected' : ''}>${kat.naziv}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Sacuvaj izmene</button>
    </form>

    <form action="/proizvodi/obrisi/${proizvod.id}" method="post" class="mt-3">
        <button type="submit" class="btn btn-danger">Obrisi</button>
    </form>
</div>

</body>
</html>
