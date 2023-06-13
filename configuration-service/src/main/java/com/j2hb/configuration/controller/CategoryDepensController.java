package com.j2hb.configuration.controller;

import com.j2hb.configuration.entity.CategorieDepense;
import com.j2hb.configuration.service.CategoryDepensService;
import com.j2hb.configuration.service.CategoryDepenseRequest;
import com.j2hb.configuration.util.JwtService;
import com.j2hb.configuration.util.SchoolClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/e")
public class CategoryDepensController {
    private final CategoryDepensService categoryDepensService;
    private final SchoolClient schoolClient;
    private final JwtService jwtService;

    private boolean getResponse(Long id_auto_ecole, HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        String userEmail = jwtService.extractUsername(jwtToken);
        return !schoolClient.isMember(id_auto_ecole, userEmail);
    }

    @GetMapping("/{id_auto_ecole}/configurations/categories-depenses")
    public ResponseEntity<List<CategorieDepense>> getCategories(@PathVariable Long id_auto_ecole, HttpServletRequest request) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        if (categoryDepensService.getAllCategoriesDepenses(id_auto_ecole) == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(categoryDepensService.getAllCategoriesDepenses(id_auto_ecole));
    }

    @PostMapping("/{id_auto_ecole}/configurations/add-categorie-depense")
    public ResponseEntity<String> addCategory(HttpServletRequest request, @RequestBody CategoryDepenseRequest categorieDepense, @PathVariable Long id_auto_ecole) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryDepensService.addCategoryDepense(categorieDepense, id_auto_ecole));
    }

    @DeleteMapping("/{id_auto_ecole}/configurations/delete-categorie-depense/{id_category_depense}")
    public ResponseEntity<String> deleteCategory(HttpServletRequest request, @PathVariable long id_category_depense, @PathVariable Long id_auto_ecole) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryDepensService.deleteCategoryDepense(id_category_depense));
    }

    @PutMapping("/{id_auto_ecole}/configurations/update-categorie-depense/{id_category_depense}")
    public ResponseEntity<String> updateCategory(HttpServletRequest request, @RequestBody CategoryDepenseRequest categorieDepense, @PathVariable Long id_auto_ecole, @PathVariable long id_category_depense) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryDepensService.updateCategoryDepense(categorieDepense, id_category_depense));
    }

}
