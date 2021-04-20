package com.organization.onlineshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organization.onlineshopping.domain.model.BookingDetails;
import com.organization.onlineshopping.exception.InternalException;
import com.organization.onlineshopping.repository.BookingRepository;

@Service
public class OnlineShoppingService {

	@Autowired
	private BookingRepository bookingrepo;

	public BookingDetails bookTimeSlot(BookingDetails bookingRequest) throws InternalException {

		List<BookingDetails> bookdetByUserIdAndDay = bookingrepo.findAllByuserIdAndWeekDay(bookingRequest.getUserId(),
				bookingRequest.getWeekDay());
		List<BookingDetails> bookdetByTimeAndDay = bookingrepo.findAllByTimeAndWeekDay(bookingRequest.getTime(),
				bookingRequest.getWeekDay());

		if (bookdetByTimeAndDay.size() > 7) {
			throw new InternalException(400, "Acceptance Criteria not met",
					"time slot is already booked by 8 users for the same time");
		}

		if (!bookdetByUserIdAndDay.isEmpty()) {

			throw new InternalException(400, "Acceptance Criteria not met",
					"One user can only book one-time slot per day");

		}

		return bookingrepo.save(bookingRequest);
	}

	public List<BookingDetails> FetchBookingsByUserId(Long userId) {

		List<BookingDetails> bookdet = bookingrepo.findAllByuserId(userId);
		return bookdet;

	}

	public List<BookingDetails> FetchBookingsByWeekDay(String weekDay) {
		List<BookingDetails> bookdet = bookingrepo.findAllByweekDayOrderByTimeAsc(weekDay);
		return bookdet;
	}

	public List<BookingDetails> FetchBookingsByTimeslots(int time) {

		List<BookingDetails> bookdet = bookingrepo.findAllBytime(time);
		return bookdet;

	}

}
