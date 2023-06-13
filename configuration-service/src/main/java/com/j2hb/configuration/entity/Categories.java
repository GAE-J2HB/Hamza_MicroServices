package com.j2hb.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Categories {
    @Id
    @Size(min = 1)
    private String nom;
    private String description;
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private Integer moyen;
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private Integer nbrQst;
    private long autoEcoleId;
}
