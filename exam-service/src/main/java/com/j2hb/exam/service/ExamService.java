package com.j2hb.exam.service;

import com.j2hb.exam.controller.Request;
import com.j2hb.exam.entity.Exam;
import com.j2hb.exam.repository.ExamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepo examRepo;

    public String createExam(long id, Request examRequest) {
        Exam exam = examRepo.findByIdCandidateAndIdAutoEcoleAndCategorieAndType(examRequest.getIdCandidate(), id, examRequest.getCategory(), examRequest.getType());
        if (exam == null) {
            examRepo.save(Exam.builder()
                    .dateDepot(examRequest.getDateDepot())
                    .dateExamen(examRequest.getDateExam())
                    .type(examRequest.getType())
                    .categorie(examRequest.getCategory())
                    .idCandidate(examRequest.getIdCandidate())
                    .idAutoEcole(id)
                    .build());
            return "Exam created successfully";
        } else if (exam.getDateDepot().isEqual(examRequest.getDateDepot()) && exam.getDateExamen().isEqual(examRequest.getDateExam())
                && exam.getType().equals(examRequest.getType()) && exam.getIdCandidate() == examRequest.getIdCandidate() && exam.getCategorie().equals(examRequest.getCategory())) {
            return "Exam already exists";
        } else {
            throw new RuntimeException("Error");
        }
    }

    public Exam getExam(long idAutoEcole, long idExam) {
        if (examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).isPresent()) {
            return examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).get();
        } else {
            throw new RuntimeException("Exam not found");
        }
    }

    public List<Exam> getExams(long idAutoEcole) {
        return examRepo.findAllByIdAutoEcole(idAutoEcole);
    }

    public String deleteExam(long idAutoEcole, long idExam) {
        examRepo.delete(examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).get());
        return "Exam deleted successfully";
    }

    public String updateExam(long idAutoEcole, long idExam, Request examRequest) {
        examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).map(
                exam -> {
                    exam.setDateDepot(examRequest.getDateDepot());
                    exam.setDateExamen(examRequest.getDateExam());
                    exam.setType(examRequest.getType());
                    exam.setCategorie(examRequest.getCategory());
                    exam.setIdCandidate(examRequest.getIdCandidate());
                    return examRepo.save(exam);
                }
        ).orElseThrow(() -> new RuntimeException("Exam not found"));
        return "Exam updated successfully";
    }

    public String addResult(long idAutoEcole, long idExam, Request examRequest) {
        Exam tempExam = examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).orElseThrow(() -> new RuntimeException("Exam not found"));
        Integer nbrExam = examRepo.countByIdAndIdAutoEcoleAndIdCandidateAndType(idExam, idAutoEcole, tempExam.getIdCandidate(), tempExam.getType());
        if (examRequest.getNote() < 30) {
            if (nbrExam == 1) {
                Exam exam = examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).orElseThrow(() -> new RuntimeException("Exam not found"));
                exam.setNote(examRequest.getNote());
                exam.setResultat(false);
                examRepo.save(exam);
                examRepo.save(
                        Exam.builder()
                                .dateDepot(exam.getDateDepot())
                                .dateExamen(exam.getDateExamen().plusDays(15))
                                .type(exam.getType())
                                .categorie(exam.getCategorie())
                                .idCandidate(exam.getIdCandidate())
                                .idAutoEcole(exam.getIdAutoEcole())
                                .build()
                );
                return "Note Added Successfully";
            } else {
                findAndModifyNoteExam(idAutoEcole, idExam, examRequest, false);
                return "Note Added Successfully";
            }
        } else {
            findAndModifyNoteExam(idAutoEcole, idExam, examRequest, true);
            return "Note Added Successfully";
        }
    }

    private void findAndModifyNoteExam(long idAutoEcole, long idExam, Request examRequest, Boolean result) {
        examRepo.findByIdAutoEcoleAndId(idAutoEcole, idExam).map(
                exam -> {
                    exam.setNote(examRequest.getNote());
                    exam.setResultat(result);
                    return examRepo.save(exam);
                }
        ).orElseThrow(() -> new RuntimeException("Exam not found"));
    }
}