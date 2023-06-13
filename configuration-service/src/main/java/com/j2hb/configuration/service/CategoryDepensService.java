package com.j2hb.configuration.service;

import com.j2hb.configuration.entity.CategorieDepense;
import com.j2hb.configuration.repository.CategoryDepenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryDepensService {
    private final CategoryDepenseRepository categoryDepenseRepository;

    public String addCategoryDepense(CategoryDepenseRequest categorieDepense, Long idAutoEcole) {
        if (categoryDepenseRepository.existsByAutoEcoleIdAndLibelle(idAutoEcole, categorieDepense.getLibelle())) {
            return "This Category Already Exist";
        }
        CategorieDepense categorieDepense1 = new CategorieDepense();
        setType(categorieDepense, categorieDepense1);
        categorieDepense1.setLibelle(categorieDepense.getLibelle());
        categorieDepense1.setDescription(categorieDepense.getDescription());
        categorieDepense1.setAutoEcoleId(idAutoEcole);
        categoryDepenseRepository.save(categorieDepense1);
        return "Category Added Successfully";
    }

    public void setType(CategoryDepenseRequest categorieDepense, CategorieDepense categorieDepense1) {
        switch (categorieDepense.getTypeDepense()) {
            case 1 -> categorieDepense1.setTypeDepense("Personnel");
            case 2 -> categorieDepense1.setTypeDepense("Vehicule");
            case 3 -> categorieDepense1.setTypeDepense("Local");
            case 4 -> categorieDepense1.setTypeDepense("Autre");
            default -> throw new IllegalStateException("Unexpected value: " + categorieDepense.getTypeDepense());
        }
    }

    public String updateCategoryDepense(CategoryDepenseRequest categorieDepense, Long idCategoryDepense) {
        if (!categoryDepenseRepository.existsById(idCategoryDepense)) {
            return "This Category Doesn't Exist";
        } else {
            CategorieDepense categoryDepense1 = categoryDepenseRepository.findById(idCategoryDepense).get();
            categoryDepense1.setLibelle(categorieDepense.getLibelle());
            categoryDepense1.setDescription(categorieDepense.getDescription());
            setType(categorieDepense, categoryDepense1);
            categoryDepenseRepository.save(categoryDepense1);
            return "Category Updated Successfully";
        }
    }

    public String deleteCategoryDepense(Long idCategoryDepense) {
        if (!categoryDepenseRepository.existsById(idCategoryDepense)) {
            return "This Category Doesn't Exist";
        } else {
            categoryDepenseRepository.deleteById(idCategoryDepense);
            return "Category Deleted Successfully";
        }
    }

    public CategorieDepense getCategoryDepense(Long idCategoryDepense) {
        if (!categoryDepenseRepository.existsById(idCategoryDepense)) {
            return null;
        } else {
            return categoryDepenseRepository.findById(idCategoryDepense).get();
        }
    }

    public List<CategorieDepense> getAllCategoriesDepenses(Long idAutoEcole) {
        return categoryDepenseRepository.findAllByAutoEcoleId(idAutoEcole);
    }

}
