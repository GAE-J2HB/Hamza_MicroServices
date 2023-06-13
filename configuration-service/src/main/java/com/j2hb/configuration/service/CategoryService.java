package com.j2hb.configuration.service;

import com.j2hb.configuration.entity.Categories;
import com.j2hb.configuration.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public String addCategory(CategoryRequest request, Long idAutoEcole) {
        if (getCategory(request.getNom(), idAutoEcole) != null) {
            return "Category already exists";
        }
        Categories categories = Categories.builder()
                .nom(request.getNom())
                .description(request.getDescription())
                .nbrQst(request.getNbrQst())
                .moyen(request.getNote())
                .autoEcoleId(idAutoEcole)
                .build();
        categoryRepository.save(categories);
        return "Category added successfully";
    }

    private Categories getCategory(String name, Long idAutoEcole) {
        return categoryRepository.findByNomAndAutoEcoleId(name, idAutoEcole).orElse(null);
    }

    public String deleteCategory(String name, Long idAutoEcole) {
        categoryRepository.deleteByNomAndAutoEcoleId(name, idAutoEcole);
        return "Category deleted successfully";
    }

    public String updateCategory(CategoryRequest request, Long idAutoEcole, String name) {
        Categories categoriesToUpdate = getCategory(name, idAutoEcole);
        if (categoriesToUpdate == null) {
            return "Category not found";
        }
        categoriesToUpdate.setDescription(request.getDescription() == null ? categoriesToUpdate.getDescription() : request.getDescription());
        categoriesToUpdate.setNbrQst(request.getNbrQst() == 0 ? categoriesToUpdate.getNbrQst() : request.getNbrQst());
        categoriesToUpdate.setMoyen(request.getNote() == 0 ? categoriesToUpdate.getMoyen() : request.getNote());
        categoryRepository.save(categoriesToUpdate);
        return "Category updated successfully";
    }

    public List<Categories> getAllCategories(Long idAutoEcole) {
        return categoryRepository.findAllByAutoEcoleId(idAutoEcole);
    }
}
