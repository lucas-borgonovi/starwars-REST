package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.dto.RebeldeDTO;
import com.letscode.starwarsrest.model.Raca;
import com.letscode.starwarsrest.model.Rebelde;

public class RebeldeUtil {

    public static Rebelde createRebelde(){
        return Rebelde.builder()
                .id(1)
                .name("teste")
                .age(15)
                .race(Raca.HUMANO)
                .location(LocationUtil.createLocation())
                .inventory(InventoryUtil.createInventory(1))
                .build();
    }

    public static RebeldeDTO createRebeldeDTO(){
        return RebeldeDTO.builder()
                .name("teste")
                .age(15)
                .race(Raca.HUMANO)
                .build();
    }

    public static Rebelde createRebeldeToUpdate(){
        return Rebelde.builder()
                .id(1)
                .name("testeUpdated")
                .age(20)
                .race(Raca.RAKATA)
                .location(LocationUtil.createLocation())
                .inventory(InventoryUtil.createInventory(1))
                .build();
    }

    public static Rebelde createTraidor(){
        return Rebelde.builder()
                .id(2)
                .name("traidor")
                .age(15)
                .race(Raca.HUMANO)
                .location(LocationUtil.createLocation())
                .inventory(InventoryUtil.createInventory(1))
                .build();
    }



}
