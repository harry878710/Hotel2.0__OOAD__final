package bookAndUser;

import java.util.Date;
import hotelAndRoom.*;

public class Book {
	private int hotelId;
	private int[] roomCombination = new int[3];
	private int totalPrice;
	private Date checkInDate = null;
	private int nights;
	private String bookId = "";
	private String userId = "";

	public Book(int hotelId, int[] roomCombination,int totalPrice,Date checkInDate, int nights,  String bookId, String userId) {
		this.checkInDate = checkInDate;
		this.nights = nights;
		this.hotelId = hotelId;
		this.totalPrice = totalPrice;
		this.roomCombination[0] = roomCombination[0];
		this.roomCombination[1] = roomCombination[1];
		this.roomCombination[2] = roomCombination[2];
		this.bookId = bookId;
		this.userId = userId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int[] getRoomCombination() {
		int[] toReturn = new int[3];
		for (int i = 0; i < 3; i++) {
			toReturn[i] = roomCombination[i];
		}
		return toReturn;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public Date getCheckInDate() {
		return new Date(checkInDate.getTime());
	}

	public int getNights() {
		return nights;
	}

	public String getBookId() {
		return bookId;
	}

	public String getUserId() {
		return userId;
	}

	// Append Hotel's toString & Room's toString at the end(to be modified)
	public String toString() {
		return "Book ID: " + bookId + "\nUser ID: " + userId + "\n";
	}

}
