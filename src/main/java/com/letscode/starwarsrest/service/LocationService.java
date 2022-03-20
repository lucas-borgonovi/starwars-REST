package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;


    public Location getLocationById(Integer id){


        Optional<Location> location =  locationRepository.findById(id);

        return location.orElse(null);


    }


    public Location updateLocation(Integer id, Location location){

        Location locationToUpdate = getLocationById(id);

        if(locationToUpdate != null){

            if(location.getGalaxyName() != null){
                locationToUpdate.setGalaxyName(location.getGalaxyName());
            }
            if(location.getLongitude() != 0){
                locationToUpdate.setLongitude(location.getLongitude());
            }
            if(location.getLatitude() != 0){
                locationToUpdate.setLatitude(location.getLatitude());
            }

            return locationRepository.save(locationToUpdate);

        }

        return null;

    }



}
