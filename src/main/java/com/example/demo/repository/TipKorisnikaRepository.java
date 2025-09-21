package com.example.demo.repository;

import com.example.demo.model.TipKorisnika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipKorisnikaRepository extends JpaRepository<TipKorisnika, Long> {
    Optional<TipKorisnika> findByNaziv(String naziv);
}
