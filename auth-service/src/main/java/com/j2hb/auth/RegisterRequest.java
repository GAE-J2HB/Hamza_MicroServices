package com.j2hb.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    //    @NotEmpty
    private MultipartFile logo;
    @Size(min = 2, max = 30)
    private String name;
    @Past
    private LocalDate dateOuverture;
    @Size(min = 2)
    private String numRegistreCom;
    @Size(min = 2)
    private String idFiscale;
    @Past
    private LocalDate dateAutorisation;
    @Size(min = 2)
    private String agrement;
    @Size(min = 2)
    private String nPatente;
    private MultipartFile imageRC;
    private MultipartFile imageAgrement;
    @Size(min = 9)
    private String numCnss;
    @Size(min = 4)
    private String ICE;
    @Size(min = 10)
    private String numCmpt;
    @DecimalMax("100")
    private Double TVA;
    @Size(min = 2)
    private String pays;
    @Size(min = 10, max = 10)
    private String tel;
    @Size(min = 2)
    private String ville;
    @Size(min = 10)
    private String adresse;
    @Size(min = 10)
    private String fax;
}
