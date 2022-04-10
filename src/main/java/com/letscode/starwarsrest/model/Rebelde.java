package com.letscode.starwarsrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rebelde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int age;

    private Raca race;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="location",referencedColumnName = "id")
    private Location location;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="inventory",referencedColumnName = "id")
    private Inventory inventory;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Login userLogin;


}
