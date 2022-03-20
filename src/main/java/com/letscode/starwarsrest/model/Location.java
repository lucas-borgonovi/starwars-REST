package com.letscode.starwarsrest.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int latitude;

    private int longitude;

    private String galaxyName;

}
