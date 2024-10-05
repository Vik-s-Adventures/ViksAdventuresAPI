package com.upc.ViksAdventures.first_world.domain.persistence;

import com.upc.ViksAdventures.first_world.domain.model.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    List<Definition> findDefinitionsByTopicId(Long topicId);
}
