package com.letscode.starwarsrest.controller;

import com.letscode.starwarsrest.converter.LocationMapper;
import com.letscode.starwarsrest.dto.LocationDTO;
import com.letscode.starwarsrest.exception.ForbiddenAccess;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("location")
public class LocationController {

    private final LocationService locationService;

    private final LocationMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Integer id){

       Location location =  locationService.getLocationById(id);

       if(location == null){
           return ResponseEntity.notFound().build();
       }

       LocationDTO locationDTO = mapper.locationToLocationDTO(location);

       return ResponseEntity.ok(locationDTO);


    }

    @PreAuthorize("hasAnyRole('REBELDE','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateRebeldeLocation(@PathVariable Integer id, @RequestBody @Valid LocationDTO locationDTO) throws ForbiddenAccess {


       Location locationUpdated =  locationService.updateLocation(id,mapper.locationDTOToLocation(locationDTO));

       if(locationUpdated == null){
           return ResponseEntity.notFound().build();
       }


       return ResponseEntity.ok(mapper.locationToLocationDTO(locationUpdated));

    }

}
