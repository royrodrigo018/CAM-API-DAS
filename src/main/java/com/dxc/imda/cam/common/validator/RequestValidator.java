package com.dxc.imda.cam.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.model.ErrorResponse;
import com.dxc.imda.cam.common.util.ErrorHandler;
import com.dxc.imda.cam.common.util.MessageHandler;
import com.dxc.imda.cam.common.util.StringUtil;

@Component
public class RequestValidator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// TODO check requestExpiryGracePeriod
	@Value("${spring.app.timeStamp.gracePeriod:4}")
	private int requestExpiryGracePeriod;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ErrorResponse validateAccountId(String reqAccountId) {	
		ErrorResponse errorResponse = null;
		if (StringUtil.isBlank(reqAccountId)) {
			errorResponse = errorHandler.getErrorResponse(messageHandler.getMessage("invalid.accountId"), "401");
		}		
		return errorResponse;
	}
	
	public ErrorResponse validateNonce(String nonce) {
		ErrorResponse errorResponse = null;
		long int32BitUnsigned = Long.parseLong(nonce);
		logger.info("int32BitUnsigned: " + int32BitUnsigned);
		if ((int32BitUnsigned >= 0) && (int32BitUnsigned <= 4294967295l)) {
			return null;
		}else {
			errorResponse = errorHandler.getErrorResponse(messageHandler.getMessage("invalid.nonce"), "401");
		}
		return errorResponse;	
	}
	
	public ErrorResponse validateTimeStamp(String requestTimeStamp) {
		ErrorResponse errorResponse = null;
		logger.info("checkTimeStamp: " + requestTimeStamp);
		if (!StringUtil.isBlank(requestTimeStamp)) {
			long longTS = Long.parseLong(requestTimeStamp);
			long currentMilliSecond = System.currentTimeMillis();
			long timeStamp = currentMilliSecond - longTS;
			
			logger.info("longTS: " + longTS);
			logger.info("currentMilliSecond: " + currentMilliSecond);
			
			logger.info("requestExpiryGracePeriod: " + requestExpiryGracePeriod);
			logger.info("timeStamp in ms: " + timeStamp);
			logger.info("requestExpiryGracePeriod: " + requestExpiryGracePeriod * 60 * 1000);
			logger.info("timeStamp in min: " + timeStamp / (60 * 1000));
			
			if (timeStamp <= (requestExpiryGracePeriod * 60 * 1000)) {
				return null;
			}
		}else {
			errorResponse = errorHandler.getErrorResponse(messageHandler.getMessage("invalid.requestDatetime"), "401");
		}
		return errorResponse;		
	}
}
