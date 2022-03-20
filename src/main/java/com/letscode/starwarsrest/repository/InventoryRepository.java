package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
}
