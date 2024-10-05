package com.upc.ViksAdventures.first_world.domain.persistence;

import com.upc.ViksAdventures.first_world.domain.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findPerformancesByCompetenceId(Long competenceId);
}
