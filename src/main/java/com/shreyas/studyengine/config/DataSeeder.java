package com.shreyas.studyengine.config;

import com.shreyas.studyengine.model.Question;
import com.shreyas.studyengine.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedQuestions(QuestionRepository repo) {
        return args -> {
            // Remove any accidental blank test questions
            repo.findAll().stream()
                .filter(q -> q.getQuestionText() == null || q.getQuestionText().isBlank())
                .forEach(repo::delete);

            if (repo.count() == 0) {
                repo.saveAll(List.of(
                    create("SSC_CGL", "Quant", "MEDIUM",
                        "If x + 1/x = 5, what is the value of x^2 + 1/x^2?",
                        "23", "25", "27", "21", "A"),
                    create("SSC_CGL", "Reasoning", "EASY",
                        "Find the missing number in the series: 3, 8, 15, 24, ?",
                        "33", "35", "36", "34", "B"),
                    create("RRB_NTPC", "General Awareness", "EASY",
                        "Which constitutional amendment lowered the voting age in India from 21 to 18?",
                        "42nd Amendment", "44th Amendment", "61st Amendment", "73rd Amendment", "C"),
                    create("GATE", "OS", "MEDIUM",
                        "Which page replacement algorithm suffers from Belady's Anomaly?",
                        "LRU", "FIFO", "Optimal", "LFU", "B"),
                    create("GATE", "DBMS", "HARD",
                        "A relation is in 3NF if every non-prime attribute is non-transitively dependent on every:",
                        "Foreign key", "Super key", "Candidate key", "Partial key", "C")
                ));
            }
        };
    }

    private Question create(String exam, String subject, String diff, String text,
                            String a, String b, String c, String d, String correct) {
        Question q = new Question();
        q.setExamCategory(exam);
        q.setSubject(subject);
        q.setDifficulty(diff);
        q.setQuestionText(text);
        q.setOptionA(a);
        q.setOptionB(b);
        q.setOptionC(c);
        q.setOptionD(d);
        q.setCorrectOption(correct);
        return q;
    }
}
