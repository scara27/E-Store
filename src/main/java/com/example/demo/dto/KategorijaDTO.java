package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KategorijaDTO {

    private Long id;

    @NotBlank(message = "Naziv kategorije je obavezan.")
    @Size(max = 100, message = "Naziv kategorije ne sme biti duži od 100 karaktera.")
    private String naziv;
}
