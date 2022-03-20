package com.letscode.starwarsrest.controller;


import com.letscode.starwarsrest.converter.RebeldeMapper;
import com.letscode.starwarsrest.dto.RebeldeCreatedDTO;
import com.letscode.starwarsrest.dto.RebeldeDTO;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.service.RebeldeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("rebelde")
public class RebeldeController {

    private final RebeldeService rebeldeService;

    private final RebeldeMapper mapper;

    @GetMapping
    public List<Rebelde> getAll(){
        return rebeldeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rebelde> getById(@PathVariable Integer id){

        try{
           Rebelde rebelde =  rebeldeService.getById(id);
           return ResponseEntity.ok(rebelde);
        }catch(NoSuchElementException ex){

            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping()
    public ResponseEntity<RebeldeDTO> addRebelde(@RequestBody @Valid Rebelde rebelde){

        Rebelde rebeldeSaved = rebeldeService.addRebelde(rebelde);

        return ResponseEntity.ok(mapper.rebeldeToRebeldeDTO(rebeldeSaved));

    }


    @PutMapping("/{id}")
    public ResponseEntity<RebeldeDTO> updateRebelde(@PathVariable Integer id, @RequestBody @Valid RebeldeDTO rebeldeDTO){

        Rebelde rebeldeUpdated = rebeldeService.updateRebelde(id,rebeldeDTO);

        if(rebeldeUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.rebeldeToRebeldeDTO(rebeldeUpdated));

    }





}
