package com.organization.onlineshopping.vo;

import com.organization.onlineshopping.domain.model.BookingDetails;

import lombok.Data;

@Data
public class BookingResponse extends BaseResponse{
	
	private boolean slotBooked;
	private BookingDetails bookingdetails;

}
