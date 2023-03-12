package com.teksocial.application.repositories;

import com.teksocial.application.models.UserModel;
import com.teksocial.application.repositories.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface User_Repo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

}
