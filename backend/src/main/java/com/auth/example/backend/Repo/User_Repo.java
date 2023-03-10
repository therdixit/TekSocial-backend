package com.auth.example.backend.Repo;

import com.auth.example.backend.model.User_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface User_Repo extends JpaRepository<User_Table, Long> {

    Optional<User_Table> findByEmail(String email);

}
