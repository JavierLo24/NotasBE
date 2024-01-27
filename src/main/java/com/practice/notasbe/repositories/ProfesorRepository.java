package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository  extends JpaRepository<Profesor, Integer> {
}
