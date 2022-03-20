package com.letscode.starwarsrest.converter;

import com.letscode.starwarsrest.dto.LocationDTO;
import com.letscode.starwarsrest.model.Location;
import lombok.Getter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location locationDTOToLocation(LocationDTO locationDTO);

    LocationDTO locationToLocationDTO(Location location);


}
