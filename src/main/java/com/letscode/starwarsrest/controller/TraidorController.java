package com.letscode.starwarsrest.controller;

import com.letscode.starwarsrest.dto.TraidorDTO;
import com.letscode.starwarsrest.exception.AutoReportException;
import com.letscode.starwarsrest.exception.SameRebeldeException;
import com.letscode.starwarsrest.model.Traidor;
import com.letscode.starwarsrest.service.TraidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("traidor")
public class TraidorController {

    private final TraidorService traidorService;

    @PostMapping
    public Traidor addTraidor(@RequestBody @Valid TraidorDTO traidor) throws SameRebeldeException, AutoReportException {

        return traidorService.addTraidor(traidor);

    }


}
