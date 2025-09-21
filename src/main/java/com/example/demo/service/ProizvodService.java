package com.example.demo.service;


import com.example.demo.model.Proizvod;
import com.example.demo.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProizvodService {

    @Autowired
    private ProizvodRepository proizvodRepository;

    public Proizvod save(Proizvod proizvod) {
        return proizvodRepository.save(proizvod);
    }

    public Optional<Proizvod> findById(Long id) {
        return proizvodRepository.findById(id);
    }

    public List<Proizvod> findAll() {
        return proizvodRepository.findAll();
    }

    public List<Proizvod> findFiltered(String kategorija, String sort, Double cenaDo) {
        List<Proizvod> proizvodi = proizvodRepository.findAll();

        if (kategorija != null && !kategorija.isEmpty()) {
            proizvodi = proizvodi.stream()
                    .filter(p -> p.getKategorija().getNaziv().equalsIgnoreCase(kategorija))
                    .toList();
        }

        if (cenaDo != null) {
            proizvodi = proizvodi.stream()
                    .filter(p -> p.getCena() <= cenaDo)
                    .toList();
        }

        if (sort != null) {
            switch (sort) {
                case "cena_rastuce":
                    proizvodi = proizvodi.stream()
                            .sorted(Comparator.comparing(Proizvod::getCena))
                            .toList();
                    break;
                case "cena_opadajuce":
                    proizvodi = proizvodi.stream()
                            .sorted(Comparator.comparing(Proizvod::getCena).reversed())
                            .toList();
                    break;
                case "naziv_az":
                    proizvodi = proizvodi.stream()
                            .sorted(Comparator.comparing(Proizvod::getNaziv))
                            .toList();
                    break;
                case "naziv_za":
                    proizvodi = proizvodi.stream()
                            .sorted(Comparator.comparing(Proizvod::getNaziv).reversed())
                            .toList();
                    break;
            }
        }

        return proizvodi;
    }

    public void delete(Proizvod proizvod) {
        proizvodRepository.delete(proizvod);
    }
}
