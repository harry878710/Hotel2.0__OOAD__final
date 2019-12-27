package bookAndUser;

import java.util.Date;
import hotelAndRoom.*;

public class Book {
	
	private Date date = null;
	private Room room = null;
	private String bookId = "";
	private String userId = "";

	public Book(Date date, Room room, String bookId, String userId) {
		this.date = date;
		this.room = room;
		this.bookId = bookId;
		this.userId = userId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Room getRoom() {
		return room;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public String getUserId() {
		return userId;
	}

	public String toString() {
		return "Book ID: " + bookId + "\nUser ID: " + userId + "\n" + room.toString();
	}

}
