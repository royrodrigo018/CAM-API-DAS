package com.dxc.imda.cam.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	
	public String convertObjectToJsonString(Object object) {
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = objMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}
