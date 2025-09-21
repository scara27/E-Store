package com.example.demo.repository;

import com.example.demo.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
    Optional<Korisnik> findByEmail(String email);
    boolean existsByKorisnickoIme(String korisnickoIme);
    boolean existsByEmail(String email);
}
