<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Naruči - ${proizvod.naziv}</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="partials/nav.jsp" />

<div class="container mt-5">
    <h3>Naruci proizvod: ${proizvod.naziv}</h3>

    <form method="post" action="/proizvodi/${proizvod.id}/dodaj-u-korpu">
        <div class="mb-3">
            <label for="kolicina" class="form-label">Kolicina:</label>
            <input type="number" name="kolicina" id="kolicina" class="form-control" value="1" min="1" required>
        </div>
        <button type="submit" class="btn btn-success">Dodaj u korpu</button>
    </form>
</div>

</body>
</html>
