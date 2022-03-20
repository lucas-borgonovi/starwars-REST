package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.TraidorDTO;
import com.letscode.starwarsrest.exception.AutoReportException;
import com.letscode.starwarsrest.exception.InvalidIdException;
import com.letscode.starwarsrest.exception.ObjectNull;
import com.letscode.starwarsrest.exception.SameRebeldeException;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.model.Traidor;
import com.letscode.starwarsrest.repository.TraidorRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TraidorService {

    private final TraidorRepository traidorRepository;

    private final RebeldeService rebeldeService;

    private final Environment env;


    public List<Traidor> getAllTraidores(){

        return traidorRepository.findDistinctFirstBy();

    }

    public Traidor getTraidorById(Integer id){

        Optional<Traidor> traidor = traidorRepository.findById(id);

        return traidor.orElse(null);

    }


    public Traidor addTraidor(TraidorDTO dto) throws SameRebeldeException, AutoReportException {


        if(dto.idTraidor() > 0 && dto.idRebelde() >0){

            Integer idRebelde = dto.idRebelde();
            Integer idTraidor = dto.idTraidor();

            Rebelde rebelde = rebeldeService.getById(idRebelde);

            Rebelde traidor = rebeldeService.getById(idTraidor);

            if(traidor != null && rebelde != null){

                Traidor traidorDB = traidorRepository.findTraidorByTraidorIdAndRebeldeId(idTraidor,idRebelde);

                if(traidorDB != null){
                    if(Objects.equals(idRebelde, traidorDB.getRebelde().getId())){

                        throw new SameRebeldeException();

                    }
                }

                if(Objects.equals(idTraidor, idRebelde)){
                    throw new AutoReportException();
                }

                Traidor traidorToSave = new Traidor();

                traidorToSave.setTraidor(traidor);

                traidorToSave.setRebelde(rebelde);

                traidorToSave.setId(1);

                return traidorRepository.save(traidorToSave);
            }

            throw new ObjectNull();


        }

        throw new InvalidIdException();


    }

    public Boolean checkTraidor(Integer idTraidor){

        Rebelde rebelde = rebeldeService.getById(idTraidor);

        if(rebelde != null){

//           Integer total =  traidorRepository.countTraidorByIdTraidorEquals(idTraidor);

           Integer total =  traidorRepository.countTraidorByTraidorId(idTraidor);

           if(total >= Integer.parseInt(env.getProperty("numeroTraidor"))){

               return true;

           }

           return false;

        }

        return null;

    }

    public double getPorcentagemTraidores(){

       List<Traidor> listTraidor = getAllTraidores();

       List<Integer> traidorIdLista = listTraidor.stream().map(traidor -> {
           if(checkTraidor(traidor.getTraidor().getId())){
               return traidor.getTraidor().getId();
           }
           return 0;
       }).filter(x-> !x.equals(0)).toList();

       return traidorIdLista.size();

    }


}
