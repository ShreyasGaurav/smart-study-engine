package com.shreyas.studyengine.controller;

import com.shreyas.studyengine.model.Question;
import com.shreyas.studyengine.service.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/questions")
    public Question addQuestion(@RequestBody Question question) {
        return examService.saveQuestion(question);
    }

    @GetMapping("/{examCategory}")
    public List<Question> getExamQuestions(@PathVariable String examCategory) {
        return examService.getQuestionsByExam(examCategory);
    }

    @PostMapping("/submit")
    public Map<String, Object> submitExam(
            @RequestBody Map<Long, String> answers,
            @RequestParam(defaultValue = "2.0") double marksPerCorrect,
            @RequestParam(defaultValue = "0.5") double negativePenalty) {
        return examService.evaluateAttempt(answers, marksPerCorrect, negativePenalty);
    }
}
