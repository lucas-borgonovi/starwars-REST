package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.converter.RebeldeMapper;
import com.letscode.starwarsrest.dto.RebeldeDTO;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.model.Raca;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.repository.RebeldeRepository;
import com.letscode.starwarsrest.utils.InventoryUtil;
import com.letscode.starwarsrest.utils.LocationUtil;
import com.letscode.starwarsrest.utils.RebeldeUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class RebeldeServiceTest {


    @InjectMocks
    RebeldeService rebeldeService;

    @Mock
    RebeldeRepository rebeldeRepository;

    @Mock
    RebeldeMapper mapper;



    @Test
    void checkAddRebeldeSuccess(){

        Rebelde rebelde = RebeldeUtil.createRebelde();

        Mockito.when(rebeldeRepository.save(rebelde)).thenReturn(rebelde);

        Rebelde rebeldeSaved = rebeldeService.addRebelde(rebelde);

        Assertions.assertThat(rebeldeSaved).isNotNull();
        Assertions.assertThat(rebeldeSaved.getId()).isEqualTo(1);
        Assertions.assertThat(rebeldeSaved.getName()).isEqualTo("teste");
        Assertions.assertThat(rebeldeSaved.getAge()).isEqualTo(15);
        Assertions.assertThat(rebeldeSaved.getRace()).isEqualTo(Raca.HUMANO);
        Assertions.assertThat(rebeldeSaved.getLocation().getGalaxyName()).isEqualTo(LocationUtil.createLocation().getGalaxyName());
        Assertions.assertThat(rebeldeSaved.getLocation().getLatitude()).isEqualTo(LocationUtil.createLocation().getLatitude());
        Assertions.assertThat(rebeldeSaved.getLocation().getLongitude()).isEqualTo(LocationUtil.createLocation().getLongitude());
        Assertions.assertThat(rebeldeSaved.getInventory().getAmmo()).isEqualTo(InventoryUtil.createInventory(1).getAmmo());
        Assertions.assertThat(rebeldeSaved.getInventory().getGun()).isEqualTo(InventoryUtil.createInventory(1).getGun());
        Assertions.assertThat(rebeldeSaved.getInventory().getFood()).isEqualTo(InventoryUtil.createInventory(1).getFood());
        Assertions.assertThat(rebeldeSaved.getInventory().getWater()).isEqualTo(InventoryUtil.createInventory(1).getWater());


    }

    @Test
    void checkUpdateRebeldeTestSuccess(){

        RebeldeDTO rebeldeDTO = RebeldeUtil.createRebeldeDTO();
        Rebelde rebeldeToUpdate = RebeldeUtil.createRebelde();
        Rebelde rebeldeUpdated = RebeldeUtil.createRebeldeToUpdate();

        Mockito.when(rebeldeRepository.findById(rebeldeToUpdate.getId())).thenReturn(Optional.of(rebeldeToUpdate));
        Mockito.when(rebeldeRepository.save(rebeldeToUpdate)).thenReturn(rebeldeUpdated);


        Rebelde rebeldeSaved = rebeldeService.updateRebelde(1,rebeldeDTO);


        Assertions.assertThat(rebeldeSaved).isNotNull();
        Assertions.assertThat(rebeldeSaved.getId()).isEqualTo(1);
        if(!rebeldeSaved.getName().equals(rebeldeToUpdate.getName())){
            Assertions.assertThat(rebeldeSaved.getName()).isEqualTo("testeUpdated");
        }
        if(rebeldeSaved.getAge() != rebeldeToUpdate.getAge()){
            Assertions.assertThat(rebeldeSaved.getAge()).isEqualTo(20);
        }
        if(!rebeldeSaved.getRace().equals(rebeldeToUpdate.getRace())){
            Assertions.assertThat(rebeldeSaved.getRace()).isEqualTo(Raca.RAKATA);
        }


    }


}
