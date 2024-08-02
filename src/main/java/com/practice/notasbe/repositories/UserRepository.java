package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.Rol;
import com.practice.notasbe.entities.UserE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserE, Integer> {
    UserE findByFirstNameAndLastName(String firstN, String lastN);
    List<UserE> findByRolId(Rol rolId);
    Optional<UserE> findUserByEmail(String Email);
}
