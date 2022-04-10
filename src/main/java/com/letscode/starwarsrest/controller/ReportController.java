package com.letscode.starwarsrest.controller;

import com.letscode.starwarsrest.dto.MediaInventoryDTO;
import com.letscode.starwarsrest.dto.PontosTraidoresDTO;
import com.letscode.starwarsrest.dto.PorcentagemRebeldeDTO;
import com.letscode.starwarsrest.dto.PorcentagemTraidores;
import com.letscode.starwarsrest.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {


    private final ReportService reportService;


    @GetMapping("/porcTraidores")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PorcentagemTraidores> getPorcentagemTraidores(){

        double response =  reportService.getPorcentagemTraidores();

        PorcentagemTraidores porcentagem = new PorcentagemTraidores(response);

        return ResponseEntity.ok(porcentagem);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/porcRebelde")
    public ResponseEntity<PorcentagemRebeldeDTO> getPorcentagemRebelde(){

        double response =  reportService.getPorcentagemRebelde();

        PorcentagemRebeldeDTO porcentagem = new PorcentagemRebeldeDTO(response);

        return ResponseEntity.ok(porcentagem);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mediaIventario")
    public ResponseEntity<MediaInventoryDTO> getMediaInventario(){

      MediaInventoryDTO mediaItens =   reportService.getMediaDeItens();


      return ResponseEntity.ok(mediaItens);


    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pontosPerdidos")
    public ResponseEntity<PontosTraidoresDTO> getPontosPerdidos(){

        int pontosPerdidos =   reportService.getPontosPerdidosTraidores();

        PontosTraidoresDTO pontosTraidores = new PontosTraidoresDTO(pontosPerdidos);


        return ResponseEntity.ok(pontosTraidores);


    }


}
