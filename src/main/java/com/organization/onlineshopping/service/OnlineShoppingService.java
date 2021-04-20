package com.organization.onlineshopping.service;

import org.springframework.stereotype.Service;

import com.organization.onlineshopping.domain.model.BookingDetails;
import com.organization.onlineshopping.vo.BookingResponse;

@Service
public class OnlineShoppingService {
	
	public BookingResponse booking(String weekday,String time,String userid) {
		BookingResponse resp = new BookingResponse();
		resp.setOutcomeCode(200);
		resp.setOutcomeMessage("Success");
		resp.setOutcomeUserMessage("Successfully Booked the timeslot");
		resp.setSlotBooked(true);
		BookingDetails bkdtails= new BookingDetails();
		bkdtails.setTime(11);
		bkdtails.setUserid(3);
		bkdtails.setWeekday("Wednesday");
		resp.setBookingdetails(bkdtails);
		return resp;
	}

}
