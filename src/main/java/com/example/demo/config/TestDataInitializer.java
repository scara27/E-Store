package com.example.demo.config;

import com.example.demo.model.KategorijaProizvoda;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Proizvod;
import com.example.demo.model.TipKorisnika;
import com.example.demo.repository.KategorijaProizvodaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.ProizvodRepository;
import com.example.demo.repository.TipKorisnikaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class TestDataInitializer {

//    @Bean
    public CommandLineRunner loadTestData(
            TipKorisnikaRepository tipKorisnikaRepository,
            KorisnikRepository korisnikRepository,
            KategorijaProizvodaRepository kategorijaProizvodaRepository,
            ProizvodRepository proizvodRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            /*
            proizvodRepository.deleteAll();
            kategorijaProizvodaRepository.deleteAll();
            korisnikRepository.deleteAll();
            tipKorisnikaRepository.deleteAll();*/

            // Tipovi korisnika
            TipKorisnika kupac = new TipKorisnika("ROLE_KUPAC");
            TipKorisnika prodavac = new TipKorisnika("ROLE_PRODAVAC");
            tipKorisnikaRepository.saveAll(List.of(kupac, prodavac));

            // Korisnici
            Korisnik k1 = new Korisnik(null, "pera", passwordEncoder.encode("pass123"), "Pera", "Peric", "pera@gmail.com",
                    LocalDate.of(1990, 1, 1), kupac);
            Korisnik k2 = new Korisnik(null, "mika", passwordEncoder.encode("pass123"), "Mika", "Mikic", "mika@gmail.com",
                    LocalDate.of(1985, 2, 2), kupac);
            Korisnik p1 = new Korisnik(null, "admin", passwordEncoder.encode("pass123"), "Ana", "Admin", "admin@gmail.com",
                    LocalDate.of(1980, 3, 3), prodavac);
            korisnikRepository.saveAll(List.of(k1, k2, p1));

            // Kategorije
            KategorijaProizvoda k1Kat = new KategorijaProizvoda(null, "Tehnika");
            KategorijaProizvoda k2Kat = new KategorijaProizvoda(null, "Odeća");
            KategorijaProizvoda k3Kat = new KategorijaProizvoda(null, "Hrana");
            kategorijaProizvodaRepository.saveAll(List.of(k1Kat, k2Kat, k3Kat));

            // Proizvodi
            Proizvod pr1 = new Proizvod(null, "Televizor Samsung", 499.99, "tv.jpg", "Samsung", 10, "Smart TV 50\"", k1Kat);
            Proizvod pr2 = new Proizvod(null, "Majica Adidas", 29.99, "majica.jpg", "Adidas", 50, "Crna majica, veličina L", k2Kat);
            Proizvod pr3 = new Proizvod(null, "Čokolada Milka", 1.49, "milka.jpg", "Milka", 100, "Mlečna čokolada", k3Kat);
            proizvodRepository.saveAll(List.of(pr1, pr2, pr3));

            System.out.println("Test podaci uspesno ubaceni.");
        };
    }
}
