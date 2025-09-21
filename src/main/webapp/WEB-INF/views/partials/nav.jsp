<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Moja Prodavnica</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/">Pocetna</a></li>

                <!-- SAMO ZA PRODAVCA -->
                <sec:authorize access="hasRole('PRODAVAC')">
                    <li class="nav-item"><a class="nav-link" href="/proizvodi/dodaj">Dodaj proizvod</a></li>
                </sec:authorize>

                <!-- SAMO ZA REGISTROVANE KORISNIKE -->
                <sec:authorize access="hasAnyRole('KUPAC', 'PRODAVAC')">
                    <li class="nav-item"><a class="nav-link" href="/korpa">Korpa</a></li>
                </sec:authorize>

                <!-- ULOGOVAN KORISNIK -->
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item"><a class="nav-link" href="/profil/porudzbine">Moje porudzbine</a></li>
                    <li class="nav-item"><a class="nav-link" href="/profil/uredi">${ulogovaniKorisnik.ime} ${ulogovaniKorisnik.prezime}</a></li>
                </sec:authorize>

                <!-- NEULOGOVAN KORISNIK -->
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item"><a class="nav-link" href="/register">Registracija</a></li>
                    <li class="nav-item"><a class="nav-link" href="/login">Prijava</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
