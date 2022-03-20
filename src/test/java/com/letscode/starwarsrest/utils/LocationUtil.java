package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.model.Location;

public class LocationUtil {

    public static Location createLocation(){
        return Location.builder()
                .id(1)
                .galaxyName("teste nome galaxia")
                .latitude(12)
                .longitude(16)
                .build();
    }

    public static Location createLocationUpdate(){
        return Location.builder()
                .id(1)
                .galaxyName("teste nome galaxia updated")
                .latitude(16)
                .longitude(21)
                .build();
    }


}
