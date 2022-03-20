package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Rebelde;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends CrudRepository<Rebelde, Integer>{

}
