package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.InventoryDTO;
import com.letscode.starwarsrest.dto.MediaInventoryDTO;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Traidor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TraidorService traidorService;

    private final TradeService tradeService;

    private final RebeldeService rebeldeService;

    @Autowired
    private Environment env;

    public double getPorcentagemTraidores(){

       double total =  traidorService.getPorcentagemTraidores() / rebeldeService.getAll().size();

       return total;

    }

    public double getPorcentagemRebelde(){


       double totalTraidores =  getPorcentagemTraidores();

       double totalRebeldes = 1 - totalTraidores;

       return totalRebeldes;

    }

     public MediaInventoryDTO getMediaDeItens(){


        List<Inventory> listInventories =   tradeService.getAllInventories();


        int totalGuns = listInventories.stream()
                .reduce(0,(total, inventory) -> total + inventory.getGun(), Integer::sum);
        int totalAmmo = listInventories.stream()
                .reduce(0,(total, inventory) -> total + inventory.getAmmo(), Integer::sum);

        int totalFood = listInventories.stream()
                .reduce(0,(total, inventory) -> total + inventory.getFood(), Integer::sum);

        int totalWater = listInventories.stream()
                .reduce(0,(total, inventory) -> total + inventory.getWater(), Integer::sum);

        int totalItens = listInventories.size();

        double mediaGun = calculateMedia(totalGuns,totalItens);
        double mediaAmmo = calculateMedia(totalAmmo,totalItens);
        double mediaFood = calculateMedia(totalFood,totalItens);
        double mediaWater = calculateMedia(totalWater,totalItens);


        return new MediaInventoryDTO(mediaGun,mediaAmmo,mediaFood,mediaWater);


    }

    public int getPontosPerdidosTraidores(){


       List<Traidor> listTraidores =  traidorService.getAllTraidores();


        return listTraidores.stream().reduce(0,(total, traidor)-> total + tradeService.getTotalPoints(traidor.getTraidor().getInventory()),Integer::sum );

    }

    private Inventory getTraidorInventory(Integer idTraidor){

       return  tradeService.getInventoryById(idTraidor);

    }



    private double calculateMedia(int totalAttribute, int totalItens){

        return (double)totalAttribute / totalItens;

    }




}
