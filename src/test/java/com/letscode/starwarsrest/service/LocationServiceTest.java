package com.letscode.starwarsrest.service;

import com.letscode.starwarsrest.exception.ForbiddenAccess;
import com.letscode.starwarsrest.model.Location;
import com.letscode.starwarsrest.repository.LocationRepository;
import com.letscode.starwarsrest.utils.LocationUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class LocationServiceTest {

    @InjectMocks
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Test
    void checkGetElementByIdSuccess(){

       Location location =  LocationUtil.createLocation();

       Mockito.when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));


       Location locationGet =  locationService.getLocationById(1);

        Assertions.assertThat(locationGet).isNotNull();
        Assertions.assertThat(locationGet.getId()).isEqualTo(1);
        Assertions.assertThat(locationGet.getLatitude()).isEqualTo(12);
        Assertions.assertThat(locationGet.getLongitude()).isEqualTo(16);
        Assertions.assertThat(locationGet.getGalaxyName()).isEqualTo("teste nome galaxia");


    }


    @Test
    void checkUpdateSuccess() throws ForbiddenAccess {

        Location location = LocationUtil.createLocation();
        Location locationToUpdate =  LocationUtil.createLocation();
        Location locationUpdated = LocationUtil.createLocationUpdate();

        Mockito.when(locationRepository.findById(locationToUpdate.getId())).thenReturn(Optional.of(locationToUpdate));
        Mockito.when(locationRepository.save(locationToUpdate)).thenReturn(locationUpdated);

        Location locationSaved = locationService.updateLocation(locationToUpdate.getId(),locationUpdated);

        Assertions.assertThat(locationSaved).isNotNull();
        Assertions.assertThat(locationSaved.getId()).isEqualTo(1);
        if(!locationSaved.getGalaxyName().equals(location.getGalaxyName())){
            Assertions.assertThat(locationSaved.getGalaxyName()).isEqualTo("teste nome galaxia updated");
        }
        if(locationSaved.getLatitude() != location.getLatitude()){
            Assertions.assertThat(locationSaved.getLatitude()).isEqualTo(16);
        }
        if(locationSaved.getLongitude() != location.getLongitude()){
            Assertions.assertThat(locationSaved.getLongitude()).isEqualTo(21);
        }



    }


}
