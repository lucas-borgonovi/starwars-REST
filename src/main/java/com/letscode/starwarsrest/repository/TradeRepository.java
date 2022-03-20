package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends CrudRepository<Inventory, Integer> {
}
