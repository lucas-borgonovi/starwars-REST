package com.letscode.starwarsrest.converter;

import com.letscode.starwarsrest.dto.RebeldeCreatedDTO;
import com.letscode.starwarsrest.dto.RebeldeDTO;
import com.letscode.starwarsrest.model.Rebelde;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RebeldeMapper {

    Rebelde rebeldeDTOToRebelde(RebeldeDTO rebeldeDTO);

    RebeldeDTO rebeldeToRebeldeDTO(Rebelde rebelde);


}
