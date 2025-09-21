package com.example.demo.service;

import com.example.demo.model.KategorijaProizvoda;
import com.example.demo.repository.KategorijaProizvodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategorijaProizvodaService {

    @Autowired
    private KategorijaProizvodaRepository kategorijaRepo;

    public List<KategorijaProizvoda> findAll() {
        return kategorijaRepo.findAll();
    }

    public Optional<KategorijaProizvoda> findById(Long id) {
        return kategorijaRepo.findById(id);
    }
}
