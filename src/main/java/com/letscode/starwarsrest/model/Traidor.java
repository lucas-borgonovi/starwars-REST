package com.letscode.starwarsrest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Traidor {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Rebelde rebelde;

    @ManyToOne()
    private Rebelde traidor;


}
