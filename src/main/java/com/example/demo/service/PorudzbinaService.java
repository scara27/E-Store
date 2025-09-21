package com.example.demo.service;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Korpa;
import com.example.demo.model.Porudzbina;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public Porudzbina create(Korisnik korisnik, List<Korpa> stavke) {
        double ukupnaCena = stavke.stream()
                .mapToDouble(k -> k.getProizvod().getCena() * k.getKolicina())
                .sum();

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setKorisnik(korisnik);
        porudzbina.setDatum(LocalDate.now());
        porudzbina.setStatus("u obradi");
        porudzbina.setUkupnaCena(ukupnaCena);

        return porudzbinaRepository.save(porudzbina);
    }
}
