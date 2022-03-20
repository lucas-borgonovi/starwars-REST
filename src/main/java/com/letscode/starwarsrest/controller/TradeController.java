package com.letscode.starwarsrest.controller;

import com.letscode.starwarsrest.converter.TradeMapper;
import com.letscode.starwarsrest.dto.InventoryDTO;
import com.letscode.starwarsrest.dto.TradeDTO;
import com.letscode.starwarsrest.exception.TotalPointsNotEqual;
import com.letscode.starwarsrest.exception.TraidorException;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("trade")
public class TradeController {

    private final TradeService tradeService;

    private final TradeMapper mapper;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories(){

        List<Inventory> inventories =  tradeService.getAllInventories();

        return ResponseEntity.ok(mapper.listInventoryToListInventoryDTO(inventories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer id) {

        Inventory inventory = tradeService.getInventoryById(id);

        return ResponseEntity.ok(mapper.inventoryToInventoryDTO(inventory));
    }

    @PutMapping()
    public ResponseEntity<List<TradeDTO>> tradeItens(@RequestBody @Valid TradeDTO[] listItens) throws TraidorException, TotalPointsNotEqual {

        Inventory inventory1 = mapper.tradeDTOTOInventory(listItens[0]);
        Inventory inventory2 = mapper.tradeDTOTOInventory(listItens[1]);

        Integer idInventory1 = listItens[0].idRebelde();
        Integer idInventory2 = listItens[1].idRebelde();

        try{
            TradeDTO[] inventoryArray =  tradeService.tradeItens(inventory1,inventory2,idInventory1,idInventory2);

            return ResponseEntity.ok(mapper.listInventorytoTradeDTO(Arrays.stream(inventoryArray).toList()));
        }catch (NoSuchElementException ex){
            return  ResponseEntity.notFound().build();
        }catch (TraidorException | TotalPointsNotEqual ex){
            throw  ex;
        }


    }



}
