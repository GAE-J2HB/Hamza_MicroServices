package com.j2hb.exam.repository;

import com.j2hb.exam.entity.Exam;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepo extends JpaRepository<Exam, Long> {

    //    Optional<Exam> findByIdAutoEcoleAndId(long idAutoEcole, long idExam);
    Exam findByIdCandidateAndIdAutoEcoleAndCategorieAndType(long idCandidat, long idAutoEcole, @NotNull String category, @NotNull String type);

    Optional<Exam> findByIdAutoEcoleAndId(long idAutoEcole, long idExam);

    Integer countByIdAndIdAutoEcoleAndIdCandidateAndType(long idAutoEcole, long idExam, long idCandidate, String type);

    List<Exam> findAllByIdAutoEcole(long idAutoEcole);
}
