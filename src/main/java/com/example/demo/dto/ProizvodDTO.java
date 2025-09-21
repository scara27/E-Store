package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProizvodDTO {

    private Long id;

    @NotBlank(message = "Naziv proizvoda je obavezan.")
    @Size(max = 200, message = "Duzina naziva proizvoda ne sme biti veca od 200 karaktera.")
    private String naziv;

    @NotNull(message = "Cena je obavezna.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Vrednost mora biti veća od 0.")
    private Double cena;

    private String slika;

    @NotBlank(message = "Naziv proizvodjaca je obavezan.")
    @Size(max = 200, message = "Duzina naziva proizvodjaca ne sme biti veca od 200 karaktera.")
    private String proizvodjac;

    @NotNull(message = "Kolicina je obavezna.")
    @Min(value = 1, message = "Količina mora biti najmanje 1.")
    private Integer kolicina;

    private String opis;

    private String kategorija;

}
