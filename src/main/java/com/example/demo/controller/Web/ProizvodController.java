package com.example.demo.controller.Web;

import com.example.demo.dto.ProizvodDTO;
import com.example.demo.model.KategorijaProizvoda;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Proizvod;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.KategorijaProizvodaService;
import com.example.demo.service.KorpaService;
import com.example.demo.service.ProizvodService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proizvodi")
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private KategorijaProizvodaService kategorijaService;

    @GetMapping("/prikazProizvoda")
    public String prikaziProizvode(
            @RequestParam(required = false) String kategorija,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Double cenaDo,
            Model model) {

        List<Proizvod> proizvodiEntiteti = proizvodService.findFiltered(kategorija, sort, cenaDo);

        List<ProizvodDTO> proizvodi = proizvodiEntiteti.stream()
                .map(p -> new ProizvodDTO(
                        p.getId(),
                        p.getNaziv(),
                        p.getCena(),
                        p.getSlika(),
                        p.getProizvodjac(),
                        p.getKolicina(),
                        p.getOpis(),
                        p.getKategorija() != null ? p.getKategorija().getNaziv() : null
                ))
                .toList();

        List<KategorijaProizvoda> kategorije = kategorijaService.findAll();

        model.addAttribute("proizvodi", proizvodi);
        model.addAttribute("kategorije", kategorije);

        model.addAttribute("odabranaKategorija", kategorija);
        model.addAttribute("odabraniSort", sort);
        model.addAttribute("odabranaCenaDo", cenaDo);

        return "home";
    }

    @GetMapping("/{id}")
    public String prikaziProizvod(@PathVariable Long id, Model model, Principal principal) {
        Optional<Proizvod> optionalProizvod = proizvodService.findById(id);

        if (optionalProizvod.isEmpty()) {
            return "redirect:/error";
        }

        Proizvod proizvod = optionalProizvod.get();

        ProizvodDTO dto = new ProizvodDTO(
                proizvod.getId(),
                proizvod.getNaziv(),
                proizvod.getCena(),
                proizvod.getSlika(),
                proizvod.getProizvodjac(),
                proizvod.getKolicina(),
                proizvod.getOpis(),
                proizvod.getKategorija().getNaziv()
        );

        model.addAttribute("proizvod", dto);
        model.addAttribute("ulogovan", principal != null);

        return "proizvod";
    }

    @GetMapping("/{id}/naruci")
    public String naruciProizvod(@PathVariable Long id, Model model, Principal principal) {
        Optional<Proizvod> optionalProizvod = proizvodService.findById(id);
        if (optionalProizvod.isEmpty()) return "redirect:/error";

        Proizvod proizvod = optionalProizvod.get();
        ProizvodDTO dto = new ProizvodDTO(
                proizvod.getId(),
                proizvod.getNaziv(),
                proizvod.getCena(),
                proizvod.getSlika(),
                proizvod.getProizvodjac(),
                proizvod.getKolicina(),
                proizvod.getOpis(),
                proizvod.getKategorija().getNaziv()
        );

        model.addAttribute("proizvod", dto);
        return "naruci";
    }

    @PostMapping("/{id}/dodaj-u-korpu")
    public String dodajUKorpu(
            @PathVariable Long id,
            @RequestParam int kolicina,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Proizvod> proizvodOpt = proizvodService.findById(id);
        if (proizvodOpt.isEmpty()) return "redirect:/error";

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen"));

        korpaService.dodajUKorpu(korisnik.getId(), id, kolicina);

        redirectAttributes.addFlashAttribute("poruka", "Proizvod je uspešno dodat u korpu.");

        return "redirect:/korpa";
    }

    // dodavanje
    @GetMapping("/dodaj")
    public String prikaziFormuZaDodavanje(Model model) {
        model.addAttribute("proizvodDTO", new ProizvodDTO());
        model.addAttribute("kategorije", kategorijaService.findAll());
        return "dodaj";
    }

    @PostMapping("/dodaj")
    public String dodajProizvod(
            @Valid @ModelAttribute("proizvodDTO") ProizvodDTO proizvodDTO,
            BindingResult bindingResult,
            @RequestParam("kategorijaId") Long kategorijaId,
            Model model,
            RedirectAttributes redirect
    ) {
        if (bindingResult.hasErrors()) {
            // Vrati nazad formu sa greškama i podacima
            model.addAttribute("kategorije", kategorijaService.findAll());
            return "dodaj";
        }

        try {
            KategorijaProizvoda kategorija = kategorijaService.findById(kategorijaId).orElse(null);

            // Pretvori DTO u entitet
            Proizvod proizvod = new Proizvod();
            BeanUtils.copyProperties(proizvodDTO, proizvod);
            proizvod.setKategorija(kategorija);

            proizvodService.save(proizvod);
            redirect.addFlashAttribute("poruka", "Uspešno dodat proizvod.");
            return "redirect:/proizvodi/dodaj";
        } catch (Exception e) {
            model.addAttribute("poruka", "Greška prilikom dodavanja proizvoda.");
            model.addAttribute("kategorije", kategorijaService.findAll());
            return "dodaj";
        }
    }

    @GetMapping("/uredi/{id}")
    public String prikaziFormuZaUredivanje(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Proizvod proizvod = proizvodService.findById(id)
                .orElseThrow(() -> new RuntimeException("Proizvod nije pronadjen"));
        model.addAttribute("proizvod", proizvod);
        model.addAttribute("kategorije", kategorijaService.findAll());
        return "urediProizvod";
    }

    @PostMapping("/uredi")
    public String urediProizvod(@ModelAttribute Proizvod proizvod, @RequestParam("kategorijaId") Long kategorijaId, RedirectAttributes redirect
    ) {
        try {
            KategorijaProizvoda kategorija = kategorijaService.findById(kategorijaId).orElse(null);
            proizvod.setKategorija(kategorija);

            proizvodService.save(proizvod);
            redirect.addFlashAttribute("poruka", "Uspesno azuriran proizvod.");
        } catch (Exception e) {
            redirect.addFlashAttribute("poruka", "Greska prilikom azuriranja proizvoda.");
        }
        return "redirect:/proizvodi/uredi/" + proizvod.getId();
    }

    @PostMapping("/obrisi/{id}")
    public String obrisiProizvod(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            proizvodService.findById(id).ifPresent(p -> proizvodService.delete(p));
            redirect.addFlashAttribute("poruka", "Proizvod uspesno obrisan.");
        } catch (Exception e) {
            redirect.addFlashAttribute("poruka", "Greska prilikom brisanja proizvoda.");
        }
        return "redirect:/";
    }
}
