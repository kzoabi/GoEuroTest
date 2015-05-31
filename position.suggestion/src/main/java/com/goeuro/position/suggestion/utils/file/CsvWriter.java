package com.goeuro.position.suggestion.utils.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.goeuro.position.suggestion.api.model.dto.LocationDto;

public class CsvWriter {

	private static Logger logger = Logger.getLogger(CsvWriter.class.getName());
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	public static void write(String fileHeader, String fileName, List<LocationDto> locationDtoList) {
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(fileHeader);
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			Iterator<LocationDto> locationIter = locationDtoList.iterator();
        	
        	LocationDto locationDto = null;
        	while (locationIter.hasNext()) {
        		locationDto = locationIter.next();
        		String outputLine = locationDto.get_id() + COMMA_DELIMITER + 
        				locationDto.getName() + COMMA_DELIMITER + 
        				locationDto.getType() + COMMA_DELIMITER + 
        				locationDto.getGeo_position().getLatitude() + COMMA_DELIMITER + 
        				locationDto.getGeo_position().getLongitude();
				fileWriter.append(outputLine);
				fileWriter.append(NEW_LINE_SEPARATOR);
        	}
        	
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error in CsvWriter", e);
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error while flushing/closing CsvWriter", e);
			}
			
		}
	}

}
