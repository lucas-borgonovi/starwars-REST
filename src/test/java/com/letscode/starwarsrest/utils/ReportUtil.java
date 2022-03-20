package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.dto.MediaInventoryDTO;
import com.letscode.starwarsrest.model.Inventory;

public class ReportUtil {

    public static MediaInventoryDTO createMediaItensObject(){

        return MediaInventoryDTO.builder().mediaAmmo(4).mediaFood(3).mediaGuns(2).mediaWater(5).build();


    }

    public static int createTotalPontosPerdidos(){

       Inventory inventory =  InventoryUtil.createInventory(1);


        return inventory.getAmmo() * 3 + inventory.getGun() * 4 + inventory.getFood() * 1 + inventory.getWater() * 2;

    }




}
