package com.j2hb.school;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.j2hb.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_auto_ecole")
@Entity
@Builder
public class AutoEcole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long autoEcole_id;
    @NotEmpty
    private String logo;
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
    @NotEmpty
    private String imageRC;
    @NotEmpty
    private String imageAgrement;
    @Size(min = 9)
    private String numCnss;
    @Size(min = 4)
    private String ice;
    @Size(min = 10)
    private String numCmpt;
    @DecimalMax("100")
    private double tva;
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

    @ManyToMany
    @JoinTable(
            name = "auto_ecole_user",
            joinColumns = @JoinColumn(name = "auto_ecole_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }
    public long getAutoEcole_id() {
        return autoEcole_id;
    }
}
