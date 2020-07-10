package com.mastercard.code.challenge.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class CodeChallengeController {

	private static final Logger logger = LogManager.getLogger("CodeChallengeController");
	
	@Autowired
	public CodeChallengeUtil util;
	Map<String, String>  citiesMap = null;
	
	@Value("${citi.file.name}")
	public String fileName;
	
	@GetMapping("/connected")
	public String connectCities(@RequestParam(required=true) String  origin, @RequestParam(required=true) String destination) {
		logger.info("origin  : "+ origin+", destination:  "+ destination);
		
		if(!(util.validateParams.test(origin, destination))) {
			return "Invalid param, Please provide both origin, destination Params....";
		}
		if(citiesMap == null) {
		 try {
			citiesMap = util.getCitiesFromFile();
			logger.info("citiesMap  : "+citiesMap);
			if(citiesMap.isEmpty())
				return "Error:  " + fileName + " file not found.";
		} catch (IOException e) {
			
		}
		}
		if(!citiesMap.isEmpty()) {
			String result ="";
			logger.info("citiesMap   " + citiesMap);
			String connectedDestination = citiesMap.get(origin);
			logger.info(" connectedDestination  : " + connectedDestination);
			if(connectedDestination != null && connectedDestination.equalsIgnoreCase(destination)) {
				result = "YES";
			}else {
				result = "NO";
			}
			logger.info("in connectCities()");
			return origin + " : " + destination  +  " Connected : " + result;
		} else {
			return "Error: " + fileName + " file not found.";
		}
	}
	
}
