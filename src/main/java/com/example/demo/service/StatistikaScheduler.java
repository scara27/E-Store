package com.example.demo.service;

import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.ProizvodRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class StatistikaScheduler {

    private final KorisnikRepository korisnikRepository;
    private final ProizvodRepository proizvodRepository;

    public StatistikaScheduler(KorisnikRepository korisnikRepository, ProizvodRepository proizvodRepository) {
        this.korisnikRepository = korisnikRepository;
        this.proizvodRepository = proizvodRepository;
    }

    // svakih 3 minuta
    @Scheduled(fixedRate = 1000 * 60 * 3)
    public void prikaziStatistiku() {
        long brojKorisnika = korisnikRepository.count();
        long brojProizvoda = proizvodRepository.count();

        System.out.println("Trenutno registrovanih korisnika: " + brojKorisnika);
        System.out.println("Trenutno dostupnih proizvoda: " + brojProizvoda);
    }

    @Scheduled(fixedRate = 10 * 1000) // svakih 1h
    public void obrisiStareIzvestaje() {
        String folderPath = "src/main/resources/generated-reports/";
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder za izveštaje ne postoji.");
            return;
        }

        // datum granica - brisati sve pre 24.07.2025.
        LocalDate granicniDatum = LocalDate.of(2025, 7, 25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        File[] fajlovi = folder.listFiles((dir, name) -> name.endsWith(".pdf"));
        if (fajlovi == null) return;

        for (File fajl : fajlovi) {
            try {
                // Izvuci datum iz naziva fajla
                String ime = fajl.getName(); // npr. "porudzbine_korisnik_1_20250723_153012.pdf"
                String[] delovi = ime.split("_");
                if (delovi.length < 5) continue; // nepravilan format

                String datumStr = delovi[3]; // "20250723"
                LocalDate datumFajla = LocalDate.parse(datumStr, formatter);

                if (datumFajla.isBefore(granicniDatum)) {
                    if (fajl.delete()) {
                        System.out.println("Obrisan fajl: " + fajl.getName());
                    } else {
                        System.out.println("Nije moguće obrisati fajl: " + fajl.getName());
                    }
                }

            } catch (Exception e) {
                System.out.println("Greška pri obradi fajla: " + fajl.getName());
            }
        }
    }
}
