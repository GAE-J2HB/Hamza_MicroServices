package com.j2hb.configuration.repository;

import com.j2hb.configuration.entity.Categories;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories, String> {
    List<Categories> findAllByAutoEcoleId(long autoEcoleId);
    void deleteByNomAndAutoEcoleId(@Size(min = 2) String name, long autoEcoleId);
    Optional<Categories> findByNomAndAutoEcoleId(@Size(min = 2) String name, long autoEcoleId);
}
