package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proizvod")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private Double cena;

    private String slika;

    private String proizvodjac;

    private Integer kolicina;

    private String opis;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private KategorijaProizvoda kategorija;
}
