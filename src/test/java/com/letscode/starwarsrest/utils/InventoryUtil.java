package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {

    public static Inventory createInventory(Integer id){
        return Inventory.builder()
                .id(id)
                .ammo(1)
                .gun(4)
                .food(4)
                .water(3)
                .build();
    }


    public static List<Inventory> createListInventories(){


        List<Inventory> listInventories = new ArrayList<>();


        listInventories.add(InventoryUtil.createInventory(1));

        listInventories.add(Inventory.builder()
                .id(2)
                .ammo(2)
                .gun(4)
                .food(4)
                .water(4)
                .build());

        listInventories.add(Inventory.builder()
                .id(3)
                .ammo(1)
                .gun(4)
                .food(4)
                .water(1)
                .build());
        listInventories.add(Inventory.builder()
                .id(4)
                .ammo(3)
                .gun(4)
                .food(4)
                .water(5)
                .build());


        return listInventories;



    }


}
