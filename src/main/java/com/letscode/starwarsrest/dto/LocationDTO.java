package com.letscode.starwarsrest.dto;


import com.letscode.starwarsrest.model.Roles;

import java.time.LocalDate;

public record LocationDTO(
         Integer latitude,
         Integer longitude,
         String galaxyName,
         Roles updatedBy

) {

}
