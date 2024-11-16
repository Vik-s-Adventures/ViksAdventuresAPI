package com.upc.ViksAdventures.quiz.api.internal;

import com.upc.ViksAdventures.profile.domain.model.Student;
import com.upc.ViksAdventures.profile.domain.service.StudentService;
import com.upc.ViksAdventures.profile.mapping.StudentMapper;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.service.ResponseService;
import com.upc.ViksAdventures.quiz.domain.service.QuizService;
import com.upc.ViksAdventures.quiz.service.PredictionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/internal/prediction")
public class PredictionController {

    private final StudentService studentService;
    private final ResponseService responseService;
    private final QuizService quizService;
    private final StudentMapper studentMapper;
    private final PredictionServiceImpl predictionService;

    @Autowired
    public PredictionController(StudentService studentService, ResponseService responseService,
                                QuizService quizService, StudentMapper studentMapper,
                                PredictionServiceImpl predictionService) {
        this.studentService = studentService;
        this.responseService = responseService;
        this.quizService = quizService;
        this.studentMapper = studentMapper;
        this.predictionService = predictionService;
    }

    @GetMapping("/input/{studentId}/{quizId}")
    public ResponseEntity<Map<String, Object>> getInputForPrediction(@PathVariable Long studentId,
                                                                     @PathVariable Long quizId) {
        Student student = studentService.getBydId(studentId);
        Quiz quiz = quizService.getBydId(quizId);
        List<Response> responses = responseService.getResponsesByStudentAndQuiz(student, quiz);

        int age = studentMapper.calculateAge(student.getBirthDate());

        Map<String, Object> input = new LinkedHashMap<>();
        input.put("STUDENT_ID", student.getId());
        input.put("SEXO", student.getSex());
        input.put("EDAD", age);

        for (int i = 0; i < responses.size() && i < 10; i++) {
            input.put("PREG" + (i + 1), responses.get(i).getOption().isCorrect() ? 1 : 0);
        }

        return ResponseEntity.ok(input);
    }

    @PostMapping("/predict/{studentId}/{quizId}")
    public ResponseEntity<Map<String, Object>> predictLearningPath(@PathVariable Long studentId,
                                                                   @PathVariable Long quizId) {
        Map<String, Object> inputData = getInputForPrediction(studentId, quizId).getBody();

        Map<String, Object> prediction = predictionService.enviarDatosYRecibirPrediccion(inputData);

        return ResponseEntity.ok(prediction);
    }
}