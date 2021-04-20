package com.organization.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.onlineshopping.exception.InternalException;
import com.organization.onlineshopping.vo.BookingResponse;
import com.organization.onlineshopping.service.OnlineShoppingService;
import com.organization.onlineshopping.vo.BookingResponse;

@RestController
@RequestMapping("/api")
public class OnlineShoppingController {
	
@Autowired
OnlineShoppingService onlineShpServ;

@PostMapping
public ResponseEntity bookingTimeSlot(@RequestParam (value = "weekday", required = false) String weekday,
		@RequestParam (value = "time", required = false) String time,
		@RequestParam (value = "userid", required = false) String userid) {
	BookingResponse bookingresp = onlineShpServ.booking(weekday,time, userid);
	return new ResponseEntity(bookingresp, HttpStatus.OK);
}
}
