package com.mastercard.code.challenge.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeChallengeUtil {

	private static final Logger logger = LogManager.getLogger("CodeChallengeUtil");
	
	@Value("${citi.file.name}")
	public String fileName;
			
	public Map<String, String> getCitiesFromFile() throws IOException {
		logger.info("in getCitiesFromFile()");
		Map<String, String> connectedCities = new HashMap<>();
		try {
			logger.info("fileName : " +fileName);
	        ClassLoader classLoader = new CodeChallengeUtil().getClass().getClassLoader();
	     
	        if(classLoader.getResource(fileName) != null) {
	        	   File file = new File(classLoader.getResource(fileName).getFile());
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str = "";
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					logger.info("str  " + str);
					String origin = str.substring(0, str.indexOf(",")).trim();
					String destination = str.substring(str.indexOf(",") +1, str.length()).trim();
					logger.info("origin  : " +origin +"  :  Destination : " + destination);
					connectedCities.put(origin, destination);
				}
	        }
		} catch (Exception  e) {
			logger.error("Exception : " +e);
			throw e;
		} 
		return connectedCities;
	}

	
	BiPredicate<String, String> validateParams = (source, destination) -> 
    { 
        if (!source.isEmpty() || !destination.isEmpty()) 
            return true; 
        return false; 
    }; 
}
