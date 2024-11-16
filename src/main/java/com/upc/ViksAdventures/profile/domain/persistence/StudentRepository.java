package com.upc.ViksAdventures.profile.domain.persistence;

import com.upc.ViksAdventures.profile.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { }
