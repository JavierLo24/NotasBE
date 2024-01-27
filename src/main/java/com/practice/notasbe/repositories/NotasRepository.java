package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NotasRepository  extends JpaRepository<Notas, Integer>{
}
