package com.organization.onlineshopping.repository;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organization.onlineshopping.domain.model.BookingDetails;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, Long>{
	
	List<BookingDetails> findAllByuserId(Long userId);
	
	
	List<BookingDetails> findAllByweekDayOrderByTimeAsc(String weekDay);


	List<BookingDetails> findAllBytime(int time);
	
	List<BookingDetails> findAllByTimeAndWeekDay(int time, String weekDay);
	List<BookingDetails> findAllByuserIdAndWeekDay(Long userId, String weekDay);

}
