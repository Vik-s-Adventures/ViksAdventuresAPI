package com.upc.ViksAdventures.first_world.domain.persistence;

import com.upc.ViksAdventures.first_world.domain.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> { }
