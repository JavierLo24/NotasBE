package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Student;
import com.practice.notasbe.entities.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Student findByUserEId(UserE userE);
}
