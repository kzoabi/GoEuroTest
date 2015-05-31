package com.goeuro.position.suggestion.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

	private String _id;
	
	private String name;
	
	private String type;
	
    private GeoPositionDto geo_position;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPositionDto getGeo_position() {
		return geo_position;
	}

	public void setGeo_position(GeoPositionDto geo_position) {
		this.geo_position = geo_position;
	}

}
