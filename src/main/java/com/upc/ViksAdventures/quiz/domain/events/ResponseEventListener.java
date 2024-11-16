package com.upc.ViksAdventures.quiz.domain.events;

import com.upc.ViksAdventures.profile.domain.model.Student;
import com.upc.ViksAdventures.quiz.domain.model.Quiz;
import com.upc.ViksAdventures.quiz.domain.model.Response;
import com.upc.ViksAdventures.quiz.domain.service.ResponseService;
import com.upc.ViksAdventures.quiz.domain.service.ResultService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseEventListener {

    private final ResultService resultService;
    private final ResponseService responseService;

    public ResponseEventListener(ResultService resultService, ResponseService responseService) {
        this.resultService = resultService;
        this.responseService = responseService;
    }

    @EventListener
    public void handleResponseCreated(ResponseCreatedEvent event) {
        Response response = event.getResponse();
        Student student = response.getStudent();
        Quiz quiz = response.getOption().getQuestion().getQuiz();

        // Verificar si el estudiante ya tiene 10 respuestas para este quiz
        List<Response> responses = responseService.getResponsesByStudentAndQuiz(student, quiz);
        if (responses.size() >= 10) {
            throw new IllegalStateException("El estudiante ya ha alcanzado el límite de 10 respuestas para este quiz.");
        }

        // Si no ha alcanzado el límite, proceder a crear o actualizar el resultado
        resultService.updateOrCreateResult(student, quiz);
    }
}
