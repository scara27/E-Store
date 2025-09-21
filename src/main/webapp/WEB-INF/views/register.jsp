<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registracija</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style>
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }

            main {
                flex: 1;
            }
        </style>
</head>
<body class="bg-light">

<jsp:include page="partials/nav.jsp" />

<main class="container mt-5">
    <div class="card mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h4 class="card-title text-center mb-4">Registracija</h4>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form:form action="/register" method="post" modelAttribute="korisnik">
                <div class="mb-3">
                    <form:label path="korisnickoIme" cssClass="form-label">Korisnicko ime</form:label>
                    <form:input path="korisnickoIme" cssClass="form-control"/>
                    <form:errors path="korisnickoIme" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <form:label path="sifra" cssClass="form-label">Lozinka</form:label>
                    <form:password path="sifra" cssClass="form-control"/>
                    <form:errors path="sifra" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <form:label path="ime" cssClass="form-label">Ime</form:label>
                    <form:input path="ime" cssClass="form-control"/>
                    <form:errors path="ime" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <form:label path="prezime" cssClass="form-label">Prezime</form:label>
                    <form:input path="prezime" cssClass="form-control"/>
                    <form:errors path="prezime" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <form:label path="email" cssClass="form-label">Email</form:label>
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>

                <div class="mb-3">
                    <form:label path="datumRodjenja" cssClass="form-label">Datum rođenja</form:label>
                    <form:input path="datumRodjenja" cssClass="form-control" type="date"/>
                    <form:errors path="datumRodjenja" cssClass="text-danger"/>
                </div>

                <button type="submit" class="btn btn-primary w-100">Registruj se</button>
            </form:form>

            <div class="text-center mt-3">
                <a href="/login">Vec imate nalog? Prijavite se</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="partials/footer.jsp" />

</body>
</html>
