package com.letscode.starwarsrest.dto;

import com.letscode.starwarsrest.model.Raca;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RebeldeDTO {

    private String name;

    private int age;

    private Raca race;

}
