package com.j2hb.exam.controller;

import com.j2hb.exam.entity.Exam;
import com.j2hb.exam.service.ExamService;
import com.j2hb.exam.util.AuthClient;
import com.j2hb.exam.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/e/{id}")
public class ExamController {
    private final ExamService examService;
    private final JwtService jwtService;
    private final AuthClient authClient;

    private ResponseEntity<String> getValidateResult(HttpServletRequest request, long idAutoEcole) {
        String token = request.getHeader("Authorization").substring(7);
        if (!authClient.validateOperation(idAutoEcole, jwtService.extractUsername(token))) {
            return ResponseEntity.status(403).build();
        }
        return null;
    }

    @PostMapping("/exams/create-exam")
    public ResponseEntity<String> createExam(HttpServletRequest request, @PathVariable("id") long idAutoEcole, @RequestBody Request examRequest) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return build;
        return ResponseEntity.created(null).body(examService.createExam(idAutoEcole, examRequest));
    }

    @GetMapping("/exams/{idExam}")
    public ResponseEntity<Exam> getExam(HttpServletRequest request, @PathVariable("id") long idAutoEcole, @PathVariable("idExam") long idExam) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(examService.getExam(idAutoEcole, idExam));
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getExams(HttpServletRequest request, @PathVariable("id") long idAutoEcole) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(examService.getExams(idAutoEcole));
    }

    @DeleteMapping("/exams/{idExam}")
    public ResponseEntity<String> deleteExam(HttpServletRequest request, @PathVariable("id") long idAutoEcole, @PathVariable("idExam") long idExam) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(examService.deleteExam(idAutoEcole, idExam));
    }

    @PutMapping("/exams/{idExam}")
    public ResponseEntity<String> updateExam(HttpServletRequest request, @PathVariable("id") long idAutoEcole, @PathVariable("idExam") long idExam, @RequestBody Request examRequest) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(examService.updateExam(idAutoEcole, idExam, examRequest));
    }

    @PostMapping("/exams/{idExam}/add-result")
    public ResponseEntity<String> addResult(HttpServletRequest request, @PathVariable("id") long idAutoEcole, @PathVariable("idExam") long idExam, @RequestBody Request examRequest) {
        ResponseEntity<String> build = getValidateResult(request, idAutoEcole);
        if (build != null) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(examService.addResult(idAutoEcole, idExam, examRequest));
    }
}
