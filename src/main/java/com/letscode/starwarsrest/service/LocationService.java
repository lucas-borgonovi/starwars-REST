package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.exception.ForbiddenAccess;
import com.letscode.starwarsrest.exception.ObjectNull;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.model.Rebelde;
import com.letscode.starwarsrest.model.Roles;
import com.letscode.starwarsrest.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    private final RebeldeService rebeldeService;


    public Location getLocationById(Integer id){


        Optional<Location> location =  locationRepository.findById(id);

        return location.orElse(null);


    }


    public Location updateLocation(Integer id, Location location) throws ForbiddenAccess {

        Rebelde rebelde = rebeldeService.getByLocationId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        var roles = authentication.getAuthorities();
        var role = roles.stream().toArray()[0].toString().substring(5);

        if(rebelde == null){
            throw new ObjectNull();
        }

        if(Objects.equals(currentUsername, rebelde.getUserLogin().getUsername())){

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

                locationToUpdate.setUpdatedBy(Roles.valueOf(role));

                return locationRepository.save(locationToUpdate);

            }
        }

        throw new ForbiddenAccess();

    }



}
