package com.letscode.starwarsrest.service;


import com.letscode.starwarsrest.dto.MediaInventoryDTO;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Traidor;
import com.letscode.starwarsrest.repository.TradeRepository;
import com.letscode.starwarsrest.utils.InventoryUtil;
import com.letscode.starwarsrest.utils.ReportUtil;
import com.letscode.starwarsrest.utils.TraidorUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ReportServiceTest {


    @InjectMocks
    ReportService reportService;

    @Mock
    TradeService tradeService;


    @Mock
    TraidorService traidorService;



    @Test
    void checkGetMediaSuccess(){


       List<Inventory> listInventories =  InventoryUtil.createListInventories();

        Mockito.when(tradeService.getAllInventories()).thenReturn(listInventories);

       MediaInventoryDTO mediaItens =   reportService.getMediaDeItens();

        Assertions.assertThat(mediaItens).isNotNull();
        Assertions.assertThat(mediaItens.getMediaAmmo()).isEqualTo(1.75);
        Assertions.assertThat(mediaItens.getMediaGuns()).isEqualTo(4);
        Assertions.assertThat(mediaItens.getMediaFood()).isEqualTo(4);
        Assertions.assertThat(mediaItens.getMediaWater()).isEqualTo((double)13/4);


    }

    @Test
    void checkGetPontosPerdidosSuccess(){

      List<Traidor> listTraidores =  TraidorUtil.createListTraidores();


      Mockito.when(traidorService.getAllTraidores()).thenReturn(listTraidores);

        for (Traidor traidor :
                listTraidores) {
            Mockito.when(tradeService.getTotalPoints(traidor.getTraidor().getInventory())).thenReturn(ReportUtil.createTotalPontosPerdidos());
        }

        int pontosFinal =  reportService.getPontosPerdidosTraidores();

        Assertions.assertThat(pontosFinal).isNotEqualTo(0);
        Assertions.assertThat(pontosFinal).isEqualTo(87);




    }





}
