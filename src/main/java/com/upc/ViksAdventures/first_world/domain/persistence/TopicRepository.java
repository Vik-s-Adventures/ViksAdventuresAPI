package com.upc.ViksAdventures.first_world.domain.persistence;

import com.upc.ViksAdventures.first_world.domain.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findTopicsByPerformanceId(Long performanceId);
}
