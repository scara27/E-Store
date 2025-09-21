package com.example.demo.controller.Web;

import com.example.demo.exception.KorisnikNotFoundException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Porudzbina;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/uredi")
    public String prikaziFormu(Model model, Principal principal) {
        String username = principal.getName();
        Korisnik korisnik = korisnikService.findByUsername(username)
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronadjen"));

        model.addAttribute("korisnik", korisnik);
        return "uredi";
    }

    @PostMapping("/uredi")
    public String sacuvajIzmene(
            @RequestParam String ime,
            @RequestParam String prezime,
            @RequestParam String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datumRodjenja,
            @RequestParam(required = false) String novaLozinka,
            @RequestParam(required = false) String potvrdaLozinke,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        String username = principal.getName();
        Korisnik korisnik = korisnikService.findByUsername(username)
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronađen"));

        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setEmail(email);
        korisnik.setDatumRodjenja(datumRodjenja);

        // Provera i postavljanje lozinke ako je uneta
        if (novaLozinka != null && !novaLozinka.isBlank()) {
            if (!novaLozinka.equals(potvrdaLozinke)) {
                redirectAttributes.addFlashAttribute("poruka", "Lozinke se ne poklapaju.");
                return "redirect:/profil/uredi";
            }

            String encodedPassword = passwordEncoder.encode(novaLozinka);
            korisnik.setSifra(encodedPassword);
        }

        try {
            korisnikService.save(korisnik);
            redirectAttributes.addFlashAttribute("poruka", "Uspešno ažuriran profil.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("poruka", "Greška pri ažuriranju profila.");
        }

        return "redirect:/profil/uredi";
    }


    @GetMapping("/porudzbine")
    public String mojePorudzbine(Model model, Principal principal) {
        Korisnik korisnik = korisnikService.findByUsername(principal.getName())
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronadjen"));

        List<Porudzbina> porudzbine = porudzbinaRepository.findAll().stream()
                .filter(p -> p.getKorisnik().getId().equals(korisnik.getId()))
                .collect(Collectors.toList());

        model.addAttribute("porudzbine", porudzbine);
        return "porudzbine";
    }
}
