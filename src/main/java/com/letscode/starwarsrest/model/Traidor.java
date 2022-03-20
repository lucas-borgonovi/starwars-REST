package com.letscode.starwarsrest.model;

import com.letscode.starwarsrest.dto.TradeDTO;
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
