package com.practice.notasbe.repositories;

import com.practice.notasbe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstNameAndLastName(String firstN, String lastN);

}
