package com.goeuro.position.suggestion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.goeuro.position.suggestion.api.connection.LocationAPI;
import com.goeuro.position.suggestion.api.data.Location;
import com.goeuro.position.suggestion.api.model.dto.LocationDto;
import com.goeuro.position.suggestion.utils.file.CsvWriter;

/**
 * Main
 *
 */
public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
    	if (args.length < 1) {
    		System.err.println("You should enter a location as an input");
    	} else {
	        LocationAPI locationApi = new LocationAPI();
	        Location location = new Location();
	        try {
	        	List<LocationDto> locationsDtoList = location.get(locationApi, args[0]);
	        	CsvWriter.write("_id,name,type,latitude,longitude", "geoLocations.csv", locationsDtoList);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Application running Error", e);
			}
    	}
    }
}
