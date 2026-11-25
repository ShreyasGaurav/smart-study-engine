package com.shreyas.studyengine.repository;

import com.shreyas.studyengine.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExamCategory(String examCategory);
    List<Question> findByExamCategoryAndSubject(String examCategory, String subject);
}
