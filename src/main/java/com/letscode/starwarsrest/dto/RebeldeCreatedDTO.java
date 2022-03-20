package com.letscode.starwarsrest.dto;

import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.model.Raca;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public record RebeldeCreatedDTO(
        String name,
        Integer age,
        Raca race,
        LocationDTO location,
        InventoryDTO inventory
) {
}
