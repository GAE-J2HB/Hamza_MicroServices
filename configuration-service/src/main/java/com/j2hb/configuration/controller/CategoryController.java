package com.j2hb.configuration.controller;

import com.j2hb.configuration.entity.Categories;
import com.j2hb.configuration.service.CategoryRequest;
import com.j2hb.configuration.service.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final SchoolClient schoolClient;
    private final JwtService jwtService;

    private boolean getResponse(Long id_auto_ecole, HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        String userEmail = jwtService.extractUsername(jwtToken);
        return !schoolClient.isMember(id_auto_ecole, userEmail);
    }

    @GetMapping("/{id_auto_ecole}/configurations/categories")
    public ResponseEntity<List<Categories>> getCategories(@PathVariable Long id_auto_ecole, HttpServletRequest request) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        if (categoryService.getAllCategories(id_auto_ecole) == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(categoryService.getAllCategories(id_auto_ecole));
    }

    @PostMapping("/{id_auto_ecole}/configurations/add-categorie")
    public ResponseEntity<String> addCategory(HttpServletRequest request, @RequestBody CategoryRequest Request, @PathVariable Long id_auto_ecole) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryService.addCategory(Request, id_auto_ecole));
    }

    @DeleteMapping("/{id_auto_ecole}/configurations/delete-categorie/{name}")
    public ResponseEntity<String> deleteCategory(HttpServletRequest request, @PathVariable String name, @PathVariable Long id_auto_ecole) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryService.deleteCategory(name, id_auto_ecole));
    }

    @PutMapping("/{id_auto_ecole}/configurations/update-categorie/{name}")
    public ResponseEntity<String> updateCategory(HttpServletRequest request, @RequestBody CategoryRequest Request, @PathVariable Long id_auto_ecole, @PathVariable String name) {
        if (getResponse(id_auto_ecole, request)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(categoryService.updateCategory(Request, id_auto_ecole, name));
    }
}
