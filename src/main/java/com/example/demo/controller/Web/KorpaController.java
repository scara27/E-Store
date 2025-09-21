package com.example.demo.controller.Web;

import com.example.demo.exception.KorisnikNotFoundException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Korpa;
import com.example.demo.model.Porudzbina;
import com.example.demo.model.Proizvod;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.KorpaService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/korpa")
public class KorpaController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping
    public String prikaziKorpu(Model model, Principal principal) {
        String username = principal.getName();
        Korisnik korisnik = korisnikService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen"));

        List<Korpa> stavke = korpaService.findByKorisnik(korisnik);

        Map<Proizvod, Integer> grupisano = new HashMap<>();
        for (Korpa k : stavke) {
            grupisano.merge(k.getProizvod(), k.getKolicina(), Integer::sum);
        }

        double ukupno = grupisano.entrySet().stream()
                .mapToDouble(e -> e.getKey().getCena() * e.getValue())
                .sum();

        model.addAttribute("grupisanaKorpa", grupisano);
        model.addAttribute("ukupnaCena", ukupno);

        return "korpa";
    }

    @PostMapping("/naruci")
    public String naruci(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();
        Korisnik korisnik = korisnikService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen"));

        try {
            List<Korpa> stavke = korpaService.findByKorisnik(korisnik);
            porudzbinaService.create(korisnik, stavke);
            korpaService.deleteByKorisnik(korisnik);
            redirectAttributes.addFlashAttribute("poruka", "Uspesno ste narucili proizvod.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("poruka", "Doslo je do greske prilikom narucivanja. "+e.getMessage());
        }

        return "redirect:/profil/porudzbine";
    }

    @PostMapping("/isprazni")
    public String isprazni(Principal principal){
        String username = principal.getName();
        Korisnik korisnik = korisnikService.findByUsername(username)
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronadjen"));

        korpaService.isprazniKorpu(korisnik.getId());

        return "redirect:/korpa";
    }
}
