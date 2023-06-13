package com.j2hb.configuration.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDepenseRequest {
    @Size(min = 2,max = 50)
    private String libelle;
    @Size(min = 2)
    private String description;
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1)
    @Range(min = 1,max = 3)
    private Integer typeDepense;
}
