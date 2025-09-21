package com.example.demo.controller.Web;

import com.example.demo.dto.ProizvodDTO;
import com.example.demo.repository.KategorijaProizvodaRepository;
import com.example.demo.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    KategorijaProizvodaRepository kategorijaProizvodaRepository;

    @GetMapping("/")
    public String prikaziPocetnuStranicu(Model model, Principal principal) {
        List<ProizvodDTO> proizvodi = proizvodService.findAll().stream().map(proizvod -> new ProizvodDTO(
                proizvod.getId(),
                proizvod.getNaziv(),
                proizvod.getCena(),
                proizvod.getSlika(),
                proizvod.getProizvodjac(),
                proizvod.getKolicina(),
                proizvod.getOpis(),
                proizvod.getKategorija().getNaziv()
        )).toList();

        model.addAttribute("proizvodi", proizvodi);

        boolean ulogovan = principal != null;

        model.addAttribute("ulogovan", ulogovan);
        model.addAttribute("kategorije", kategorijaProizvodaRepository.findAll());

        return "home";
    }
}
