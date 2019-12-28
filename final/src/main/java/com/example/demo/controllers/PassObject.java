package com.example.demo.controllers;

public class PassObject {
	String checkInDate;
	int night;
	int[] roomCombination;
	String city;
	String sorting;
	public String re;
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public int getNight() {
		return night;
	}
	public void setNight(int night) {
		this.night = night;
	}
	public int[] getRoomCombination() {
		return roomCombination;
	}
	public void setRoomCombination(int[] roomCombination) {
		this.roomCombination = roomCombination;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSorting() {
		return sorting;
	}
	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
}
