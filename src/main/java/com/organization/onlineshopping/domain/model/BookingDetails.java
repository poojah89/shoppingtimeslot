package com.organization.onlineshopping.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name = "timeslot")
@Data
public class BookingDetails {
	
	@Id
	@NotNull
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "week_day")
	private String weekDay;
	
	@Column(name = "time")
	private int time;
	

}
