package com.letscode.starwarsrest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MediaInventoryDTO {

    private double mediaGuns;

    private double mediaAmmo;

    private double mediaFood;


    private double mediaWater;

}
