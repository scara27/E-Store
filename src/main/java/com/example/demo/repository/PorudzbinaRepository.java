package com.example.demo.repository;

import com.example.demo.model.Korisnik;
import com.example.demo.model.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
    List<Porudzbina> findByKorisnik(Korisnik korisnik);
    List<Porudzbina> findByStatusNot(String status);
    List<Porudzbina> findByDatumBetween(LocalDate start, LocalDate end);

    List<Porudzbina> findByKorisnikId(Long korisnikId);
}
