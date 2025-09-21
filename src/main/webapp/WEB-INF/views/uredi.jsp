<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uredi profil</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h3>Uredi profil</h3>

    <c:if test="${not empty poruka}">
        <div class="alert alert-info">${poruka}</div>
    </c:if>

    <form method="post" action="/profil/uredi">
        <div class="mb-3">
            <label class="form-label">Ime:</label>
            <input type="text" name="ime" class="form-control" value="${korisnik.ime}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Prezime:</label>
            <input type="text" name="prezime" class="form-control" value="${korisnik.prezime}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" value="${korisnik.email}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Datum rođenja:</label>
            <input type="date" name="datumRodjenja" class="form-control" value="${korisnik.datumRodjenja}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Nova lozinka:</label>
            <input type="password" name="novaLozinka" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Potvrdi novu lozinku:</label>
            <input type="password" name="potvrdaLozinke" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary me-2">Potvrdi</button>
    </form>
</div>

</body>
</html>
