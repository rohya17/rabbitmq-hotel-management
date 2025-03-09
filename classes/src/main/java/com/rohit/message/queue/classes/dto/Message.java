package com.rohit.message.queue.classes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {

	private Integer hotelId;
	private String userName;
	private String roomNumber;
	private LocalDate bookingDate;
//	private LocalTime bookingTimeFrom;
//	private LocalTime bookingTimeTo;
	
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
//	public LocalTime getBookingTimeFrom() {
//		return bookingTimeFrom;
//	}
//	public void setBookingTimeFrom(LocalTime bookingTimeFrom) {
//		this.bookingTimeFrom = bookingTimeFrom;
//	}
//	public LocalTime getBookingTimeTo() {
//		return bookingTimeTo;
//	}
//	public void setBookingTimeTo(LocalTime bookingTimeTo) {
//		this.bookingTimeTo = bookingTimeTo;
//	}
//	@Override
//	public String toString() {
//		return "Message [hotelId=" + hotelId + ", userName=" + userName + ", roomNumber=" + roomNumber
//				+ ", bookingDate=" + bookingDate + ", bookingTimeFrom=" + bookingTimeFrom + ", bookingTimeTo="
//				+ bookingTimeTo + "]";
//	}
	@Override
	public String toString() {
		return "Message [hotelId=" + hotelId + ", userName=" + userName + ", roomNumber=" + roomNumber
				+ ", bookingDate=" + bookingDate + "]";
	}
	
	
	
}
