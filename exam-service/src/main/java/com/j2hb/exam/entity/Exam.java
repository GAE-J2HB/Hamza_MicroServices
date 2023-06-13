package com.j2hb.exam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate dateDepot;
    private LocalDate dateExamen;
    private String type;
    private String categorie;
    private int note;
    private boolean resultat;
    private long idAutoEcole;
    private long idCandidate;
}


