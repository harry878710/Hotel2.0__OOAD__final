package book_Hotel_Room;

import java.util.ArrayList;

public class BookList {

	public static ArrayList<Book> bookList;
	public static int bookedNumber;
	
	static {
		bookList = BookOperation.uploadBookList();
		bookedNumber = BookOperation.uploadBookedNumber();
	}
}
