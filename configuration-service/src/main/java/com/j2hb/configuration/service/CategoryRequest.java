package com.j2hb.configuration.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryRequest {
    private String nom;
    private String description;
    private int nbrQst;
    private int note;
}
