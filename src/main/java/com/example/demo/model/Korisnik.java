package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "korisnik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String email;
    private LocalDate datumRodjenja;

    @ManyToOne
    @JoinColumn(name = "tip_korisnika_id")
    private TipKorisnika tipKorisnika;
}
