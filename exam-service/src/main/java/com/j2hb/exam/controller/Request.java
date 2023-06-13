package com.j2hb.exam.controller;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Request {
    @PastOrPresent
    @NotNull
    private LocalDate dateDepot;
    @Future
    @NotNull
    private LocalDate dateExam;
    @NotNull
    private String type;
    @NotNull
    private String category;
    private int note;
    private int idCandidate;
}
