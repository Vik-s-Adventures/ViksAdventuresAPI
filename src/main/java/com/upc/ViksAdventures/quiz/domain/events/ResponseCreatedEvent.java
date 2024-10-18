package com.upc.ViksAdventures.quiz.domain.events;

import com.upc.ViksAdventures.quiz.domain.model.Response;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResponseCreatedEvent extends ApplicationEvent {

    private final Response response;

    public ResponseCreatedEvent(Object source, Response response) {
        super(source);
        this.response = response;
    }

}