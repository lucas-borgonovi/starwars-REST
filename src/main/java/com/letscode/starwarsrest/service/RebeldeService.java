package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.dto.RebeldeDTO;
import com.letscode.starwarsrest.model.Inventory;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.repository.LocationRepository;
import com.letscode.starwarsrest.repository.RebeldeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RebeldeService {

    private final RebeldeRepository rebeldeRepository;

    public List<Rebelde> getAll(){
        return Streamable.of(rebeldeRepository.findAll()).toList();
    }

    public Rebelde getById(Integer id){
            Optional<Rebelde> rebelde = rebeldeRepository.findById(id);
        return rebelde.orElse(null);

    }

    public Rebelde addRebelde(Rebelde rebelde){

        return rebeldeRepository.save(rebelde);

    }

    public Rebelde updateRebelde(Integer id, RebeldeDTO rebeldeDTO){

        Rebelde rebeldeToUpdate = getById(id);

        if(rebeldeToUpdate == null){
            return null;
        }

        if(rebeldeDTO.getName() != null){
            rebeldeToUpdate.setName(rebeldeDTO.getName());
        }
        if(rebeldeDTO.getAge() != 0){
            rebeldeToUpdate.setAge(rebeldeDTO.getAge());
        }
        if(rebeldeDTO.getRace() != null){
            rebeldeToUpdate.setRace(rebeldeDTO.getRace());
        }


        return rebeldeRepository.save(rebeldeToUpdate);

    }





}
