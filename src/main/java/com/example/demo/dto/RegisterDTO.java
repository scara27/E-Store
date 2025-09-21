package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    @NotBlank(message = "Korisnicko ime ne sme biti prazno.")
    @Size(max = 20, message = "Duzina korisnickog imena ne sme biti veca od 20 karaktera.")
    @Size(min = 3, message = "Duzina korisnickog imena ne sme biti manja od 3 karaktera.")
    private String korisnickoIme;

    @NotBlank(message = "Lozinka ne sme biti prazna.")
    @Size(max = 40, message = "Duzina lozinke ne sme biti veca od 40 karaktera.")
    @Size(min = 3, message = "Duzina lozinke ne sme biti manja od 3 karaktera.")
    private String sifra;

    @NotBlank(message = "Ime ne sme biti prazno.")
    @Size(max = 30, message = "Duzina imena ne sme biti veca od 30 karaktera.")
    private String ime;

    @NotBlank(message = "Prezime ne sme biti prazno.")
    @Size(max = 30, message = "Duzina prezimena ne sme biti veca od 30 karaktera.")
    private String prezime;

    @NotBlank(message = "Email adresa ne sme biti prazna.")
    @Email(message = "Email adresa nije validna.")
    private String email;

    @NotBlank(message = "Datum rodjenja je obavezan.")
    private String datumRodjenja;

    private Long tipKorisnikaId;
}
