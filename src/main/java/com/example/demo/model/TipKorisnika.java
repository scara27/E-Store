package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tip_korisnika")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipKorisnika {
    public TipKorisnika(String naziv) {
        this.naziv = naziv;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;
}
