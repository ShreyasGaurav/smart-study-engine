package com.shreyas.studyengine.service;

import com.shreyas.studyengine.model.Question;
import com.shreyas.studyengine.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {

    private final QuestionRepository questionRepository;

    public ExamService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByExam(String examCategory) {
        return questionRepository.findByExamCategory(examCategory.toUpperCase());
    }

    public Map<String, Object> evaluateAttempt(
            Map<Long, String> submittedAnswers,
            double marksPerCorrect,
            double negativePenalty) {

        int correct = 0;
        int wrong = 0;
        int unattempted = 0;
        double totalScore = 0.0;
        List<String> weakSubjects = new ArrayList<>();

        for (Map.Entry<Long, String> entry : submittedAnswers.entrySet()) {
            Question q = questionRepository.findById(entry.getKey()).orElse(null);
            if (q == null) continue;

            String userAns = entry.getValue();
            if (userAns == null || userAns.isBlank()) {
                unattempted++;
            } else if (q.getCorrectOption().equalsIgnoreCase(userAns.trim())) {
                correct++;
                totalScore += marksPerCorrect;
            } else {
                wrong++;
                totalScore -= negativePenalty;
                if (!weakSubjects.contains(q.getSubject())) {
                    weakSubjects.add(q.getSubject());
                }
            }
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("correct", correct);
        result.put("wrong", wrong);
        result.put("unattempted", unattempted);
        result.put("netScore", Math.round(totalScore * 100.0) / 100.0);
        result.put("weakSubjectsToRevise", weakSubjects);
        return result;
    }
}
