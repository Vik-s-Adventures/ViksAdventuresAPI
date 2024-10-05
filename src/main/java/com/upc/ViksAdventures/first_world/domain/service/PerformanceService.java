package com.upc.ViksAdventures.first_world.domain.service;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerformanceService {
    List<Performance> getAll();
    List<Performance> getByCompetenceId(Long competenceId);
    Performance getById(Long performanceId);
    Performance create(Performance performance);
    Performance update(Long performanceId, Performance performance);
    ResponseEntity<?> delete(Long performanceId);
}
