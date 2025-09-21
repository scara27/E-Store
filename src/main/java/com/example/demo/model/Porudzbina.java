package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "porudzbina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Porudzbina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @Column(nullable = false)
    private LocalDate datum;

    @Column(nullable = false)
    private String status; // "u obradi", "poslato", "isporučeno", "otkazano"

    @Column(name = "ukupna_cena", nullable = false)
    private Double ukupnaCena;
}
