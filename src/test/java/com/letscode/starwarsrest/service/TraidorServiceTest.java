package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.TraidorDTO;
import com.letscode.starwarsrest.exception.AutoReportException;
import com.letscode.starwarsrest.exception.SameRebeldeException;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.model.Traidor;
import com.letscode.starwarsrest.repository.RebeldeRepository;
import com.letscode.starwarsrest.repository.TraidorRepository;
import com.letscode.starwarsrest.utils.RebeldeUtil;
import com.letscode.starwarsrest.utils.TraidorUtil;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TraidorServiceTest {

    @InjectMocks
    TraidorService traidorService;

    @Mock
    RebeldeService rebeldeService;

    @Mock
    RebeldeRepository rebeldeRepository;

    @Mock
    TraidorRepository traidorRepository;

    @Mock
    Environment env;


    @Test
    void checkAddTraidorSuccess() throws SameRebeldeException, AutoReportException {

        TraidorDTO traidorDTO = TraidorUtil.createTraidorDTO();

        Rebelde rebelde = RebeldeUtil.createRebelde();
        Rebelde traidor = RebeldeUtil.createTraidor();


        Mockito.when(rebeldeRepository.findById(traidorDTO.idRebelde()))
                .thenReturn(Optional.of(rebelde));
        Mockito.when(rebeldeRepository.findById(traidorDTO.idTraidor()))
                .thenReturn(Optional.of(traidor));

        Mockito.when(traidorRepository
                        .findTraidorByTraidorIdAndRebeldeId(traidorDTO.idTraidor(),traidorDTO.idRebelde()))
                .thenReturn(null);

        Mockito.when(rebeldeService.getById(traidorDTO.idRebelde()))
                .thenReturn(rebelde);

        Mockito.when(rebeldeService.getById(traidorDTO.idTraidor()))
                .thenReturn(traidor);


        Traidor traidorToSave = Mockito.mock(Traidor.class);

        traidorToSave.setTraidor(traidor);
        traidorToSave.setRebelde(rebelde);

        Mockito.when(traidorRepository.save(traidorToSave))
                .thenReturn(TraidorUtil.createTraidorObject());


        Traidor traidorSaved = traidorService.addTraidor(traidorDTO);

        Assertions.assertThat(traidorSaved).isNotNull();
        Assertions.assertThat(traidorSaved.getId()).isEqualTo(1);
        Assertions.assertThat(traidorSaved.getTraidor().getId()).isEqualTo(2);
        Assertions.assertThat(traidorSaved.getRebelde().getId()).isEqualTo(1);



    }

    @Test
    void checkPorcentagemTraidoresSuccess(){

        Rebelde traidor = RebeldeUtil.createTraidor();

        Mockito.when(traidorRepository.findDistinctFirstBy()).thenReturn(TraidorUtil.createListTraidores());
        Mockito.when(rebeldeService.getById(traidor.getId())).thenReturn(traidor);
        Mockito.when(traidorRepository.countTraidorByTraidorId(traidor.getId())).thenReturn(3);
        Mockito.when(env.getProperty("numeroTraidor")).thenReturn("3");

        double listSize = traidorService.getPorcentagemTraidores();


        Assertions.assertThat(listSize).isNotNull();
        Assertions.assertThat(listSize).isEqualTo(3);

    }





}
