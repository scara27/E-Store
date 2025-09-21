package com.example.demo.service;

import com.example.demo.exception.KorisnikNotFoundException;
import com.example.demo.exception.NemaDovoljnoNaStanjuException;
import com.example.demo.exception.ProizvodNotFoundException;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Korpa;
import com.example.demo.model.Proizvod;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.KorpaRepository;
import com.example.demo.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KorpaService {

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private ProizvodRepository proizvodRepository;

    public List<Korpa> findByKorisnik(Korisnik korisnik) {
        return korpaRepository.findByKorisnik(korisnik);
    }

    @Transactional
    public void deleteByKorisnik(Korisnik korisnik) {
        korpaRepository.deleteByKorisnik(korisnik);
    }

    public Korpa dodajUKorpu(Long korisnikId, Long proizvodId, int kolicina) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronadjen"));

        Proizvod proizvod = proizvodRepository.findById(proizvodId)
                .orElseThrow(() -> new ProizvodNotFoundException("Proizvod nije pronadjen"));

        if (proizvod.getKolicina() < kolicina) {
            throw new NemaDovoljnoNaStanjuException("Nema dovoljno proizvoda na stanju.");
        }

        proizvod.setKolicina(proizvod.getKolicina() - kolicina);
        proizvodRepository.save(proizvod);

        Korpa korpa = new Korpa();
        korpa.setKorisnik(korisnik);
        korpa.setProizvod(proizvod);
        korpa.setKolicina(kolicina);

        return korpaRepository.save(korpa);
    }

    @Transactional
    public void isprazniKorpu(Long korisnikId) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new KorisnikNotFoundException("Korisnik nije pronadjen"));

        List<Korpa> korpas = korpaRepository.findByKorisnik(korisnik);
        for (Korpa korpa : korpas) {
            Proizvod p = korpa.getProizvod();
            p.setKolicina(p.getKolicina() + korpa.getKolicina());
            proizvodRepository.save(p);
        }

        korpaRepository.deleteAll(korpas);
    }
}
