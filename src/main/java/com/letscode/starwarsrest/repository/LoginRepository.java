package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

    Optional<Login> findByUsername(String username);

}
