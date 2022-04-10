package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Rebelde;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebeldeRepository extends CrudRepository<Rebelde, Integer> {

    List<Rebelde> findAll(Pageable pageable);

    Rebelde findByLocationId(Integer id);

}
