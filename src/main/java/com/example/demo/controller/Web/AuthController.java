package com.example.demo.controller.Web;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.model.TipKorisnika;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.TipKorisnikaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

@Controller
public class AuthController {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private TipKorisnikaRepository tipRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("korisnik", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("korisnik") @Valid RegisterDTO dto,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirect) {

        // Ako ima grešaka iz @Valid validacije:
        if (bindingResult.hasErrors()) {
            return "register"; // Vraća se na formu sa greškama
        }

        // Ručna validacija jedinstvenosti
        if (korisnikRepository.existsByKorisnickoIme(dto.getKorisnickoIme())) {
            bindingResult.rejectValue("korisnickoIme", null, "Korisničko ime već postoji.");
            return "register";
        }

        if (korisnikRepository.existsByEmail(dto.getEmail())) {
            bindingResult.rejectValue("email", null, "Email adresa je već u upotrebi.");
            return "register";
        }

        // Ako je sve validno, nastavi sa registracijom
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(dto.getKorisnickoIme());
        k.setSifra(passwordEncoder.encode(dto.getSifra()));
        System.out.println("Encoded password : " + k.getSifra());
        k.setIme(dto.getIme());
        k.setPrezime(dto.getPrezime());
        k.setEmail(dto.getEmail());
        k.setDatumRodjenja(java.time.LocalDate.parse(dto.getDatumRodjenja()));

        TipKorisnika kupac = tipRepo.findByNaziv("ROLE_KUPAC").get();
        k.setTipKorisnika(kupac);

        korisnikRepository.save(k);

        redirect.addFlashAttribute("success", "Uspešno ste se registrovali.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/"; // već ulogovan, redirektuj
        }

        return "login";
    }
}
