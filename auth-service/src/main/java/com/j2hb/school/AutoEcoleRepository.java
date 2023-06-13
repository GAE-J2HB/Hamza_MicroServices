package com.j2hb.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutoEcoleRepository extends JpaRepository<AutoEcole, Long> {

}