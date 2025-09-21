package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PorudzbinaDTO {
    private Long id;
    private Long korisnikId;
    private String korisnickoIme;
    private LocalDate datum;
    private String status;
    private Double ukupnaCena;
}
