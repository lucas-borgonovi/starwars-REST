package com.letscode.starwarsrest.model;

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


}
