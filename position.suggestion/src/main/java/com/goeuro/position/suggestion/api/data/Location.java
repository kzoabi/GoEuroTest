package com.goeuro.position.suggestion.api.data;

import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeuro.position.suggestion.api.connection.LocationAPI;
import com.goeuro.position.suggestion.api.model.dto.LocationDto;

public class Location {

    public List<LocationDto> get(LocationAPI apiConnection, String location) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser dataReceived = apiConnection.getData(location);
		List<LocationDto> locationDto = mapper.readValue(dataReceived, getEntityTypeReference());

		return locationDto;
    }

    protected TypeReference<List<LocationDto>> getEntityTypeReference() {
        return new TypeReference<List<LocationDto>>() {
        };
    }

}
