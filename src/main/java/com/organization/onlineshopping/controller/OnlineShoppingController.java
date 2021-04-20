package com.organization.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.onlineshopping.domain.model.BookingDetails;
import com.organization.onlineshopping.exception.InternalException;
import com.organization.onlineshopping.service.OnlineShoppingService;
import com.organization.onlineshopping.vo.BookingResponse;

@RestController
@RequestMapping("/api")
public class OnlineShoppingController {

	@Autowired
	OnlineShoppingService onlineShpServ;

	@GetMapping
	public ResponseEntity FetchATimeSlot(@RequestParam(value = "userid", required = true) Long userId) {
		List<BookingDetails> response = onlineShpServ.FetchBookingsByUserId(userId);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity bookATimeSlot(@RequestBody BookingDetails bookingRequest) throws InternalException {
		BookingDetails BookingDetailsresponse = onlineShpServ.bookTimeSlot(bookingRequest);
		BookingResponse response = new BookingResponse();
		response.setBookingdetails(BookingDetailsresponse);
		response.setSlotBooked(true);
		response.setOutcomeCode(200);
		response.setOutcomeMessage("Sucess");
		response.setOutcomeUserMessage("Successfully booked the time slot");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/weekdayUtilization")
	public ResponseEntity FetchATimeSlotbyweekday(@RequestParam(value = "userid", required = true) String weekDay) {
		List<BookingDetails> response = onlineShpServ.FetchBookingsByWeekDay(weekDay);
		return new ResponseEntity(response, HttpStatus.OK);
	}

}
