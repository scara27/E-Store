<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prijava</title>
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
    <div class="card mx-auto" style="max-width: 500px;">
        <div class="card-body">
            <h4 class="card-title text-center mb-4">Prijava</h4>

<%--            <c:if test="${not empty success}">--%>
<%--                <div class="alert alert-success text-center">${success}</div>--%>
<%--            </c:if>--%>

            <form method="post" action="/perform-login">
                <div class="mb-3">
                    <label class="form-label">Korisničko ime</label>
                    <input type="text" name="username" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Lozinka</label>
                    <input type="password" name="password" class="form-control"/>
                </div>

                <button type="submit" class="btn btn-primary w-100">Prijavi se</button>
            </form>

            <div class="text-center mt-3">
                <a href="/register">Nemate nalog? Registrujte se</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="partials/footer.jsp" />

</body>
</html>
