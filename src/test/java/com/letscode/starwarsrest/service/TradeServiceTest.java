package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.TradeDTO;
import com.letscode.starwarsrest.exception.TotalPointsNotEqual;
import com.letscode.starwarsrest.exception.TraidorException;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.repository.TradeRepository;
import com.letscode.starwarsrest.utils.InventoryUtil;
import com.letscode.starwarsrest.utils.TradeUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TradeServiceTest {


    @Mock
    Environment env;

    @InjectMocks
    TradeService tradeService;

    @Mock
    TradeRepository tradeRepository;

    @Mock
    TraidorService traidorService;


    @Test
    void checkTradeItensSucces() throws TraidorException, TotalPointsNotEqual {

       TradeDTO[] tradeDTOArray = TradeUtil.createTradeDTOArray();


        Mockito.when(traidorService.checkTraidor(1)).thenReturn(false);
        Mockito.when(traidorService.checkTraidor(2)).thenReturn(false);

        Inventory rebelde =  InventoryUtil.createInventory(1);

        Inventory rebelde2 = InventoryUtil.createInventory(2);

        Mockito.when(tradeRepository.findById(1)).thenReturn(java.util.Optional.of(rebelde));
        Mockito.when(tradeRepository.findById(2)).thenReturn(java.util.Optional.of(rebelde2));


        Mockito.when(env.getProperty("gun")).thenReturn("4");
        Mockito.when(env.getProperty("ammo")).thenReturn("3");
        Mockito.when(env.getProperty("food")).thenReturn("1");
        Mockito.when(env.getProperty("water")).thenReturn("2");

        Mockito.when(tradeRepository.save(rebelde)).thenReturn(rebelde);
        Mockito.when(tradeRepository.save(rebelde2)).thenReturn(rebelde2);


        TradeDTO[] tradeDTOS =  tradeService.tradeItens(rebelde,rebelde2,1,2);

        Assertions.assertThat(tradeDTOS).isNotNull();
        Assertions.assertThat(tradeDTOS[0].ammo()).isEqualTo(0);
        Assertions.assertThat(tradeDTOS[0].gun()).isEqualTo(0);
        Assertions.assertThat(tradeDTOS[0].water()).isEqualTo(0);
        Assertions.assertThat(tradeDTOS[0].food()).isEqualTo(0);
        Assertions.assertThat(tradeDTOS[1].ammo()).isEqualTo(tradeDTOArray[1].ammo());
        Assertions.assertThat(tradeDTOS[1].gun()).isEqualTo(tradeDTOArray[1].gun());
        Assertions.assertThat(tradeDTOS[1].food()).isEqualTo(tradeDTOArray[1].food());
        Assertions.assertThat(tradeDTOS[1].water()).isEqualTo(tradeDTOArray[1].water());





    }



}
