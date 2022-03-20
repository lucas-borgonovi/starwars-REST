package com.letscode.starwarsrest.repository;

import com.letscode.starwarsrest.model.Traidor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraidorRepository extends CrudRepository<Traidor,Integer> {

//    Integer countTraidorByIdTraidorEquals(Integer idTraidor);

    Integer countTraidorByTraidorId(Integer idTraidor);

//    Integer countTraidorByIdRebeldeEquals(Integer idRebelde);

    List<Traidor> findDistinctFirstBy();

//    Traidor findTraidorByIdTraidorAndIdRebelde(Integer idTraidor, Integer idRebelde);


    Traidor findTraidorByTraidorIdAndRebeldeId(Integer idTraidor, Integer idRebelde);

}
