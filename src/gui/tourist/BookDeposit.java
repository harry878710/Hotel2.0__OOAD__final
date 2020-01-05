package gui.tourist;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import book_Hotel_Room.Hotel;

public class BookDeposit {
	private int hotelId;
	private int night;
	private int[] roomCombination;
	private String checkInDate;
	private String city;

	public BookDeposit(String w, int[] r, String d, int n, int h) {
		city = w;
		roomCombination = r;
		checkInDate = d;
		night = n;
		hotelId = h;
	}

	public int getNight() {
		return night;
	}

	public int[] getRoomCombination() {
		return roomCombination;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public String getCity() {
		return city;
	}

	public int getHotelId() {
		return hotelId;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date checkOut = sdf.parse(checkInDate, new ParsePosition(0));
		String checkOutDate = null;
		if (checkOut != null) {
			for (int i = night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
			checkOutDate = sdf.format(checkOut);
		}
		int price = 0;
		for (int j = 0; j < 3; j++) {
			price += Hotel.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
		}
		return Hotel.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate + "\nCheck-out date : "
				+ checkOutDate + "\n" + "Stay nights : " + night + " nights\nTotal price : " + price + "\nRoom : \n"
				+ " Single : " + roomCombination[0] + "\n Double : " + roomCombination[1] + "\n Quad : "
				+ roomCombination[2] + "\n";
	}

	private Date nextDate(Date thisDate) {
		// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}

	public int[] numOfPeopleAndRoom(int numOfPeople, int numOfRoom) {
		int[] roomType = new int[3];
		if (numOfPeople == 4 * numOfRoom - 1) {
			numOfPeople += 1;
		}
		roomType[2] = numOfPeople / 4;
		roomType[1] = (numOfPeople % 4) / 2;
		roomType[0] = (numOfPeople % 4) % 2;
		while (roomType[0] + roomType[1] + roomType[2] != numOfRoom) {
			if (roomType[2] > 0) {
				roomType[2] -= 1;
				roomType[1] += 2;
			} else if (roomType[1] > 0) {
				roomType[1] -= 1;
				roomType[0] += 2;
			}
		}
		return roomType;
	}

}