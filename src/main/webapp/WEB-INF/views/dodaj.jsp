<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj proizvod</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Dodaj proizvod</h2>

    <c:if test="${not empty poruka}">
        <div class="alert alert-info mt-3">${poruka}</div>
    </c:if>

    <form:form modelAttribute="proizvodDTO" method="post" action="/proizvodi/dodaj">
        <div class="mb-3">
            <label class="form-label" for="naziv">Naziv</label>
            <form:input path="naziv" cssClass="form-control" id="naziv" />
            <form:errors path="naziv" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="cena">Cena</label>
            <form:input path="cena" cssClass="form-control" id="cena" type="number" step="0.01" />
            <form:errors path="cena" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="slika">Slika (URL)</label>
            <form:input path="slika" cssClass="form-control" id="slika" />
            <form:errors path="slika" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="proizvodjac">Proizvođač</label>
            <form:input path="proizvodjac" cssClass="form-control" id="proizvodjac" />
            <form:errors path="proizvodjac" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="kolicina">Količina</label>
            <form:input path="kolicina" cssClass="form-control" id="kolicina" type="number" min="1" />
            <form:errors path="kolicina" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="opis">Opis</label>
            <form:textarea path="opis" cssClass="form-control" id="opis" rows="3" />
            <form:errors path="opis" cssClass="text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label" for="kategorijaId">Kategorija</label>
            <select name="kategorijaId" id="kategorijaId" class="form-select" required>
                <c:forEach items="${kategorije}" var="kat">
                    <option value="${kat.id}">${kat.naziv}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Dodaj</button>
    </form:form>
</div>

</body>
</html>
