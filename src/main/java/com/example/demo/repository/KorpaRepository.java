package com.example.demo.repository;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Korpa;
import com.example.demo.model.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa, Long> {
    List<Korpa> findByKorisnik(Korisnik korisnik);
    void deleteByKorisnik(Korisnik korisnik);
    Optional<Korpa> findByKorisnikAndProizvod(Korisnik korisnik, Proizvod proizvod);

}
