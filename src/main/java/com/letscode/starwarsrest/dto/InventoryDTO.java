package com.letscode.starwarsrest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

public record InventoryDTO(
        int gun,
        int ammo,
        int water,
        int food
) {


}
