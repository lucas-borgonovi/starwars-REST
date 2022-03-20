package com.letscode.starwarsrest.converter;

import com.letscode.starwarsrest.dto.InventoryDTO;
import com.letscode.starwarsrest.dto.TradeDTO;
import com.letscode.starwarsrest.model.Inventory;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TradeMapper {

    Inventory inventoryDTOToInventory(InventoryDTO dto);

    InventoryDTO inventoryToInventoryDTO(Inventory inventory);

    Inventory[] arrayInventoryToInventoryDTOArray(InventoryDTO[] inventoryArray);

    List<InventoryDTO> listInventoryToListInventoryDTO(List<Inventory> inventories);

    List<TradeDTO> listInventorytoTradeDTO(List<TradeDTO> inventories);

    Inventory tradeDTOTOInventory(TradeDTO inventory);


}
