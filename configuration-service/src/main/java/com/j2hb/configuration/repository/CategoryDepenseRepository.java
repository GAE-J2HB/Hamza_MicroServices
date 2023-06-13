package com.j2hb.configuration.repository;

import com.j2hb.configuration.entity.CategorieDepense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryDepenseRepository extends JpaRepository<CategorieDepense, Long> {
    boolean existsByAutoEcoleIdAndLibelle(Long idAutoEcole, String libelle);
    List<CategorieDepense> findAllByAutoEcoleId(Long idAutoEcole);
}
