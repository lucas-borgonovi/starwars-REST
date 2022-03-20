package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.TradeDTO;
import com.letscode.starwarsrest.exception.TotalPointsNotEqual;
import com.letscode.starwarsrest.exception.TraidorException;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    private final TraidorService traidorService;

    public final RebeldeService rebeldeService;

    private final Environment env;

    public List<Inventory> getAllInventories(){

        return Streamable.of(tradeRepository.findAll()).toList();

    }

    public Inventory getInventoryById(Integer id){

        Optional<Inventory> inventory =  tradeRepository.findById(id);

        return inventory.orElse(null);


    }

    public TradeDTO[] tradeItens(Inventory itensUser, Inventory itensRebelde, Integer idUser, Integer idRebelde) throws TotalPointsNotEqual, TraidorException {

        if(traidorService.checkTraidor(idUser) || traidorService.checkTraidor(idRebelde))
        {

            throw new TraidorException();

        }

        int totalUser = getTotalPoints(itensUser);



        int totalRebelde = getTotalPoints(itensRebelde);


        if(totalRebelde == totalUser){

            Inventory inventoryUser = getInventoryById(idUser);
            Inventory inventoryRebelde =getInventoryById(idRebelde);

            if(checkAvailabity(itensUser,inventoryUser) && checkAvailabity(itensRebelde,inventoryRebelde)){
                changeItens(inventoryUser, itensRebelde);
                changeItens(inventoryRebelde, itensUser);

                removeItensFromInventory(inventoryUser, itensUser);
                removeItensFromInventory(inventoryRebelde, itensRebelde);

                tradeRepository.save(inventoryUser);
                tradeRepository.save(inventoryRebelde);

                TradeDTO inventoryRebelde1 = new TradeDTO(inventoryUser.getGun(),
                        inventoryUser.getAmmo(),inventoryUser.getWater(),inventoryUser.getFood(),idUser);

                TradeDTO inventoryRebelde2 = new TradeDTO(inventoryRebelde.getGun(),
                        inventoryRebelde.getAmmo(),inventoryRebelde.getWater(),inventoryRebelde.getFood(),idRebelde);

                return new TradeDTO[]{inventoryRebelde1,inventoryRebelde2};
            }



        }

        throw new TotalPointsNotEqual();

    }

    public int getTotalPoints(Inventory itensRebelde) {

        int totalRebelde = 0;

        totalRebelde += itensRebelde.getGun()* Integer.parseInt(env.getProperty("gun"));
        totalRebelde += itensRebelde.getAmmo()* Integer.parseInt(env.getProperty("ammo"));
        totalRebelde += itensRebelde.getFood()* Integer.parseInt(env.getProperty("food"));
        totalRebelde += itensRebelde.getWater()* Integer.parseInt(env.getProperty("water"));
        return totalRebelde;
    }

    private boolean isEmptyValue(int attribute){

        if(attribute == 0){
            return true;
        }

        return false;

    }

    private boolean checkAvailabity(Inventory itens, Inventory inventory){


        if(!(inventory.getAmmo() >= itens.getAmmo())){
            return false;
        }
        if(!(inventory.getGun() >= itens.getGun())){
            return false;
        }
        if(!(inventory.getFood() >= itens.getFood())){
            return false;
        }
        if(!(inventory.getWater() >= itens.getWater())){
            return false;
        }

        return true;

    }

    private void removeItensFromInventory(Inventory userInventory, Inventory userItens){

        if(!isEmptyValue(userItens.getGun())){
            userInventory.setGun(userInventory.getGun() - userItens.getGun());
        }
        if(!isEmptyValue(userItens.getAmmo())){
            userInventory.setAmmo(userInventory.getAmmo() - userItens.getAmmo());
        }
        if(!isEmptyValue(userItens.getFood())){
            userInventory.setFood(userInventory.getFood() - userItens.getFood());
        }
        if(!isEmptyValue(userItens.getWater())){
            userInventory.setWater(userInventory.getWater() - userItens.getWater());
        }


    }

    private void changeItens(Inventory inventory, Inventory itens){

        if(!isEmptyValue(itens.getGun())){

            inventory.setGun(itens.getGun() + inventory.getGun());

        }
        if(!isEmptyValue(itens.getAmmo())){

            inventory.setAmmo(itens.getAmmo() + inventory.getAmmo());

        }
        if(!isEmptyValue(itens.getFood())){

            inventory.setFood(itens.getFood() + inventory.getFood());

        }
        if(!isEmptyValue(itens.getWater())){

            inventory.setWater(itens.getWater() + inventory.getWater());
        }



    }




}
