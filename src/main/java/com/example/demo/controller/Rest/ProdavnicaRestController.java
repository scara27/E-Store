package com.example.demo.controller.Rest;

import com.example.demo.dto.KategorijaDTO;
import com.example.demo.dto.PorudzbinaDTO;
import com.example.demo.model.Porudzbina;
import com.example.demo.service.ProdavnicaRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProdavnicaRestController {

    @Autowired
    private ProdavnicaRestService prodavnicaRestService;

    @GetMapping("/getProizvodById")
    public ResponseEntity<?> getProizvod(@RequestParam Long id){
        if (id<=0)
            return ResponseEntity.badRequest().body("Nevažeći ID za pretragu proizvoda.");
        if (prodavnicaRestService.findById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proizvod sa ID-jem " + id + " nije pronađen.");
        return ResponseEntity.ok(prodavnicaRestService.findById(id));
    }

    //"u obradi", "poslato", "isporučeno", "otkazano"
    @PutMapping("/updatePorudzbina")
    public ResponseEntity<?> updatePorudzbina(@RequestBody PorudzbinaDTO dto) {
        if (dto.getId() == null || dto.getStatus() == null || dto.getStatus().isBlank()) {
            return ResponseEntity.badRequest().body("ID i novi status su obavezni.");
        }

        PorudzbinaDTO azurirana = prodavnicaRestService.updateStatus(dto.getId(), dto.getStatus());

        if (azurirana == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Porudžbina sa ID " + dto.getId() + " nije pronađena.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Status uspešno ažuriran.");
        response.put("porudzbina", azurirana);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/kategorije")
    public ResponseEntity<?> dodajKategoriju(@RequestBody @Valid KategorijaDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Vrati greške validacije
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        KategorijaDTO kreirana = prodavnicaRestService.dodajKategoriju(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreirana);
    }



    @GetMapping("/porudzbine/neisporucene")
    public ResponseEntity<?> getNeisporucenePorudzbine() {
        List<Porudzbina> porudzbine = prodavnicaRestService.findNeisporucene();

        if (porudzbine == null || porudzbine.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nema neisporučenih porudžbina.");
        }

        prodavnicaRestService.generisiIzvestajNeisporucene(porudzbine);

        return ResponseEntity.ok(porudzbine);
    }

    @GetMapping("/porudzbine/korisnik/{korisnikId}")
    public ResponseEntity<?> getPorudzbineByKorisnik(@PathVariable Long korisnikId) {
        List<Porudzbina> porudzbine = prodavnicaRestService.findByKorisnikId(korisnikId);

        if (porudzbine == null || porudzbine.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nema porudžbina za korisnika sa ID: " + korisnikId);
        }

        prodavnicaRestService.generisiIzvestajZaKorisnika(porudzbine, korisnikId);

        return ResponseEntity.ok(porudzbine);
    }
}