package com.chatbot.infomate.dataimport;

import com.chatbot.infomate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

import com.chatbot.infomate.repository.UserRepository;
import com.chatbot.infomate.repository.CategoryRepository;
import com.chatbot.infomate.repository.SubcategoryRepository;
import com.chatbot.infomate.repository.QuestionRepository;
import com.chatbot.infomate.repository.FeedbackRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subCategoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUsers();

        loadCategories();

        loadSubCategories();

        loadQuestions();

        loadFeedback();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            List<User> users = Arrays.asList(
                    new User( "guest@domain.com", "123456", RoleEnum.GUEST),
                    new User( "john@test.com", "123456", RoleEnum.REGISTERED),
                    new User( "tausif@test.com", "123456", RoleEnum.REGISTERED),
                    new User( "huseyin@test.com", "123456", RoleEnum.REGISTERED),
                    new User( "admin@infomate.com", "nimda", RoleEnum.ADMIN)
            );
            userRepository.saveAll(users);
        }
    }

    private void loadCategories() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.asList(
                    new Category("Technology"),
                    new Category("Health"),
                    new Category("Science")
            );
            categoryRepository.saveAll(categories);
        }
    }

    private void loadSubCategories() {
        if (subCategoryRepository.count() == 0) {
            Category techCategory = categoryRepository.findByCategoryName("Technology").orElseThrow();
            Category healthCategory = categoryRepository.findByCategoryName("Health").orElseThrow();

            List<Subcategory> subCategories = Arrays.asList(
                    new Subcategory("Programming", techCategory),
                    new Subcategory("AI & ML", techCategory),
                    new Subcategory("Nutrition", healthCategory),
                    new Subcategory("Mental Health", healthCategory)
            );
            subCategoryRepository.saveAll(subCategories);
        }
    }

    private void loadQuestions() {
        if (questionRepository.count() == 0) {
            Subcategory programming = subCategoryRepository.findBySubcategoryName("Programming").orElseThrow();
            Subcategory ai = subCategoryRepository.findBySubcategoryName("AI & ML").orElseThrow();

            List<Question> questions = Arrays.asList(
                    new Question("What is Java?", "Java is a high-level programming language...", programming, 50),
                    new Question("What is Spring Boot?", "Spring Boot is a framework...", programming, 30),
                    new Question("What is Machine Learning?", "Machine Learning is a subset of AI...", ai, 40)
            );
            questionRepository.saveAll(questions);
        }
    }

    private void loadFeedback() {
        if (feedbackRepository.count() == 0) {
            User john = userRepository.findByUsername("john@test.com").orElseThrow();
            User tausif = userRepository.findByUsername("tausif@test.com").orElseThrow();
            User guestUser = userRepository.findByUsername("guest@domain.com").orElseThrow();
            Question javaQuestion = questionRepository.findByQuestionTextContainingIgnoreCase("What is Java?").orElseThrow();
            Question mcQuestion = questionRepository.findByQuestionTextContainingIgnoreCase("What is Machine Learning?").orElseThrow();
            List<Feedback> feedbacks = Arrays.asList(
                    new Feedback(javaQuestion, john, "Very informative", true),
                    new Feedback(mcQuestion, tausif, "Impressive", true),
                    new Feedback(mcQuestion, john, "Thanks", true),
                    new Feedback(javaQuestion, guestUser, "Could be better", false)
            );
            feedbackRepository.saveAll(feedbacks);
        }
    }
}
