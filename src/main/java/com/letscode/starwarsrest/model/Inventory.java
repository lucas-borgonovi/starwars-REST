package com.letscode.starwarsrest.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int gun;

    private int ammo;

    private int water;

    private int food;



}
