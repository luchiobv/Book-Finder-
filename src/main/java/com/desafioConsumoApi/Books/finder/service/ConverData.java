package com.desafioConsumoApi.Books.finder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T gettingData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json.toString(),classe);

        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

}
