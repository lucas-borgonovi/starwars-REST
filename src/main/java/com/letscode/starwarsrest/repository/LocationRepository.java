package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location,Integer> {
}
