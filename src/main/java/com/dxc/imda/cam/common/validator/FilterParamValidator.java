package com.dxc.imda.cam.common.validator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.SortedBy;
import com.dxc.imda.cam.common.model.ErrorResponse;
import com.dxc.imda.cam.common.util.ErrorHandler;
import com.dxc.imda.cam.common.util.MessageHandler;
import com.dxc.imda.cam.common.model.FilterParam;
import com.dxc.imda.cam.common.util.StringUtil;

@Component
public class FilterParamValidator {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ErrorResponse errorResponse = null;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ErrorResponse validateFilterParam(String resourceType, Map<String, String> reqBodyMap) {	
		
		String filterValue = reqBodyMap.get(Constants.FILTER).toString();
		int startIndexValue = Integer.parseInt(reqBodyMap.get(Constants.START_INDEX));
		int itemsPerPageValue = Integer.parseInt(reqBodyMap.get(Constants.ITEMS_PER_PAGE));
		String ascOrderByValue = reqBodyMap.get(Constants.ASC_ORDER_BY);
		String descOrderByValue = reqBodyMap.get(Constants.DESC_ORDER_BY);
		
		logger.info("filter: " + filterValue);
		logger.info("startIndex: " + startIndexValue);
		logger.info("itemsPerPage: " + itemsPerPageValue);
		logger.info("ascOrderBy: " + ascOrderByValue);
		logger.info("descOrderBy: " + descOrderByValue);
		
		if (!StringUtil.isBlank(filterValue)) {
			//errorResponse = errorHandler.getErrorResponse(resourceType, "Filter is null. ", "404");
		}
		if (startIndexValue < 0) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("less.zero.index"), "404");
		}	
		if (itemsPerPageValue < 0) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("less.zero.items"), "404");
		}
		if (!StringUtil.isBlank(ascOrderByValue) && !StringUtil.isBlank(descOrderByValue)) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("required.orderBy"), "404");
		}
		if (!StringUtil.isBlank(ascOrderByValue)) {
			logger.info("validate direction ASC");
			errorResponse = validateOrderBy(resourceType, ascOrderByValue);			
		}
		if (!StringUtil.isBlank(descOrderByValue)) {
			logger.info("validate direction DESC");
			errorResponse = validateOrderBy(resourceType, descOrderByValue);
		}		
		return errorResponse;
	}
	
	public ErrorResponse validateOrderBy(String resourceType, String orderBy) {			
		boolean isOrdered = false;
		for (SortedBy sortedBy: SortedBy.values()) {
			//logger.info("checkOrderBy sortedBy.getSortedBy(): {} " + sortedBy.getSortedBy());
			if (sortedBy.getSortedBy().equalsIgnoreCase(orderBy)) {
				isOrdered = true;
				break; // TODO
			}
		}
		logger.info("validateOrderBy isOrdered: {} " + isOrdered);
		if (!isOrdered) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("invalid.orderBy"), "404");
		}				
		return errorResponse;
	}
	
	public String[] validateFilterParam(String filterValue) {
		FilterParam filterParam = convertArrayToValueObject(filterValue);
		String columName = filterParam.getColumnName();
		String operator = filterParam.getOperator();
		String value = filterParam.getKeyword();	
		logger.info("validateFilterParam input: {} {} {} " + columName +", "+ operator +", "+ value);	
		
		String[] filterParams = {null, operator, value};
		filterParams[1] = operator;
		if ("groupId".equalsIgnoreCase(columName)) {
			filterParams[0] = Constants.ROLE_NAME;
			filterParams[2] = value;
		}else if ("groupName".equalsIgnoreCase(columName)) {
			filterParams[0] = Constants.ROLE_DESC;
			filterParams[2] = value;
		}
		logger.info("validateFilterParam output: {0} {1} {2} " + filterParams[0] +", "+ filterParams[1] +", "+ filterParams[2]);	
		return filterParams;		
	}
	
	private FilterParam convertArrayToValueObject(String filter) {
		String spaceDelimeter = " ";
		String singleQuoteDelimeter = "'";		
		String[] arrOfString = StringUtil.splitString(filter, spaceDelimeter);
		String[] arrWithQuote = StringUtil.splitString(filter, singleQuoteDelimeter);
		FilterParam filterParam = new FilterParam();
		filterParam.setColumnName(arrOfString[0].trim());
		filterParam.setOperator(arrOfString[1].trim());
		filterParam.setKeyword(arrWithQuote[1].trim());		
		return filterParam;
	}	
}