package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.dto.TraidorDTO;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.model.Traidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TraidorUtil {

    public static TraidorDTO createTraidorDTO(){


         TraidorDTO traidorDTO =  new TraidorDTO(1,2);

         return traidorDTO;


    }

    public static Traidor createTraidorObject() {


        return Traidor.builder()
                .id(1)
                .traidor(RebeldeUtil.createTraidor())
                .rebelde(RebeldeUtil.createRebelde())
                .build();


    }

    public static List<Traidor> createListTraidores(){


        List<Traidor> traidorList = new LinkedList<Traidor>();

        traidorList.add(TraidorUtil.createTraidorObject());

        traidorList.add(Traidor.builder()
                .id(2)
                .traidor(RebeldeUtil.createTraidor())
                .rebelde(RebeldeUtil.createRebelde())
                .build());

        traidorList.add(Traidor.builder()
                .id(3)
                .traidor(RebeldeUtil.createTraidor())
                .rebelde(RebeldeUtil.createRebelde())
                .build());

        return traidorList;

    }



}
