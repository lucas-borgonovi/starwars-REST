package com.letscode.starwarsrest.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    private Roles updatedBy;

}
