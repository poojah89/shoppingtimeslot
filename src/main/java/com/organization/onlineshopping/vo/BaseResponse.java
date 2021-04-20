package com.organization.onlineshopping.vo;

import lombok.Data;

@Data
public class BaseResponse {
	
	private int outcomeCode;
	private String outcomeMessage;
	private String outcomeUserMessage;

}
