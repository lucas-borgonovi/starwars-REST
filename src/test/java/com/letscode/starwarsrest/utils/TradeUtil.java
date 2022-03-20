package com.letscode.starwarsrest.utils;

import com.letscode.starwarsrest.dto.TradeDTO;

public class TradeUtil {


    public static TradeDTO createTradeDTO(){


        return new TradeDTO(1,2,2,4,1);

    }

    public static TradeDTO[] createTradeDTOArray(){

        TradeDTO tradeDTO = new TradeDTO(4,4,4,4,1);
        TradeDTO tradeDTO2 = new TradeDTO(4,4,4,4,2);

        TradeDTO[] tradeDTOArray = new TradeDTO[]{tradeDTO,tradeDTO2};

        return tradeDTOArray;


    }



}
