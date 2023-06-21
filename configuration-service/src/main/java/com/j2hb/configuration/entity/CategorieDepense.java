package com.j2hb.configuration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategorieDepense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2,max = 50)
    private String libelle;
    @Size(min = 2)
    private String description;
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String typeDepense;
    private Long autoEcoleId;
}
