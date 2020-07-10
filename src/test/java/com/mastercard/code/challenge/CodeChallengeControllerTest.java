package com.mastercard.code.challenge;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mastercard.code.challenge.controller.CodeChallengeController;
import com.mastercard.code.challenge.controller.CodeChallengeUtil;

@RunWith(MockitoJUnitRunner.class)
class CodeChallengeControllerTest {

	@Test
	void test() {
		assertTrue(true);
	}
	
	@InjectMocks
	CodeChallengeController controller;
	
	@Mock
	CodeChallengeUtil util;
	
	@Test
	public void getConnectSuccess() {
		String source = "Newark";
		String dest = "Boston";
		String result = source + " : " + dest  +  " Connected : "+"YES";
	//	ReflectionTestUtils.setField(controller, "fileName", "city.txt");
		Map<String, String> map = new HashMap<>();
		map.put("Newark", "Boston");
//		try {
//			when(util.getCitiesFromFile()).thenReturn(map);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	//	String actual = controller.connectCities("Newark", "Boston");
	//	assertTrue(actual.equals(result));
	}
}
