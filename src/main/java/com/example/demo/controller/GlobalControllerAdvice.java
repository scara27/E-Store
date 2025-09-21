package com.example.demo.controller;

import com.example.demo.exception.KorisnikNotFoundException;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private final KorisnikService korisnikService;

    public GlobalControllerAdvice(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @ModelAttribute
    public void dodajUlogovanogKorisnika(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String korisnickoIme = auth.getName();

            korisnikService.findByUsername(korisnickoIme).ifPresent(korisnik -> {
                model.addAttribute("ulogovaniKorisnik", korisnik);
                model.addAttribute("ulogovaniTip", korisnik.getTipKorisnika().getNaziv());
            });
        }
    }

//    @ExceptionHandler(KorisnikNotFoundException.class)
//    public String handleKorisnikNotFoundException(KorisnikNotFoundException ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "error/korisnik-nije-pronadjen"; // JSP stranica za grešku
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleGeneralException(Exception ex, Model model) {
//        model.addAttribute("errorMessage", "Došlo je do greške. Molimo pokušajte kasnije.");
//        return "error/greska";
//    }
}
