package operation;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import book_Hotel_Room.Book;
import book_Hotel_Room.BookOperation;
import book_Hotel_Room.Hotel;
import book_Hotel_Room.Room;

public class EditBook {

	public EditBook() {

	}

	/**
	 * Edit the order with specified book ID. Change the old room number to new room
	 * number.
	 * 
	 * @param bookId
	 * @param newSingle
	 * @param newDoub
	 * @param newQuad
	 * @return 0 if changed sucessfully.Return 1, or 2 if the change didn't
	 *         successfully.
	 */
	public int editRoomCombination(String bookId, int[] newRoomCombination) {
		// check if the order is valid.
		Book myBook = BookOperation.getBook(bookId);
		int[] remainRoomNumber = remainingRoomNumber(myBook.getCheckInDate(), myBook.getCheckOutDate(),
				myBook.getHotelId());
		remainRoomNumber[0] += myBook.getRoomCombination()[0];
		remainRoomNumber[1] += myBook.getRoomCombination()[1];
		remainRoomNumber[2] += myBook.getRoomCombination()[2];
		if (remainRoomNumber[0] < newRoomCombination[0] || remainRoomNumber[1] < newRoomCombination[1]
				|| remainRoomNumber[2] < newRoomCombination[2]) {
			if (newRoomCombination[0] > Hotel.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[0]
					|| newRoomCombination[1] > Hotel.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[1]
					|| newRoomCombination[2] > Hotel.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[2]) {
				System.out.println("Room number is never enough.");
				return 1;
			} else {
				System.out.println("Room number is not enough.");
				return 2;
			}
		}
		int price = calculateTotalPrice(myBook.getHotelId(), myBook.getNights(), newRoomCombination);
		// The order is valid, change the book.
		BookOperation.changeBook(bookId, newRoomCombination[0], newRoomCombination[1], newRoomCombination[2], price);
		Book.bookList = BookOperation.uploadBookList();
		System.out.println("\nChange Succeeded");
		return 0;
	}

	/**
	 * Edit the order with specified book ID. Change the old check-in and check-out
	 * date to the new one.
	 * 
	 * @param bookId
	 * @param newSingle
	 * @param newDoub
	 * @param newQuad
	 * @return 0 if changed sucessfully.Return 4, or 5 if the change didn't
	 *         succesfully.Return 1, 2, or 3 if input format has error.
	 */
	public int editCheckInDateAndNight(String bookId, String newCheckInDate, String newCheckOutDate) {
		// check input.

		if (validCheckInDate(stringToDate(newCheckInDate), stringToDate(newCheckOutDate)) != 0) {
			return validCheckInDate(stringToDate(newCheckInDate), stringToDate(newCheckOutDate));
		}

		Book myBook = BookOperation.getBook(bookId);
		// check if the new order is valid
		int hotelId = myBook.getHotelId();
		Date newCheckIn = stringToDate(newCheckInDate), newCheckOut = stringToDate(newCheckOutDate);
		int night = calculateNight(newCheckIn, newCheckOut);
		int[] remainRoomNumber = remainingRoomNumber(newCheckIn, newCheckOut, hotelId);
		if (remainRoomNumber[0] < myBook.getRoomCombination()[0] || remainRoomNumber[1] < myBook.getRoomCombination()[1]
				|| remainRoomNumber[2] < myBook.getRoomCombination()[2]) {

			if (myBook.getRoomCombination()[0] > Hotel.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[0]
					|| myBook.getRoomCombination()[1] > Hotel.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[1]
					|| myBook.getRoomCombination()[2] > Hotel.ALLHOTEL[myBook.getHotelId()]
							.getRoomCombination()[2]) {
				System.out.println("Room number is never enough.");
				return 4;
			} else {
				System.out.println("Room number is not enough.");
				return 5;
			}
		}
		// the order is valid, change the order.
		int price = calculateTotalPrice(myBook.getHotelId(), night, myBook.getRoomCombination());
		BookOperation.changeBook(bookId, night, newCheckInDate, dateToString(newCheckOut), price);
		Book.bookList = BookOperation.uploadBookList();
		System.out.println("\nChanging success");
		return 0;
	}

	/**
	 * Delete the book with specified book ID.
	 * 
	 * @param bookId
	 * @return 0 if deleted sucessfully.
	 */
	public int deleteBook(String bookId) {
		BookOperation.deleteBook(bookId);
		Book.bookList = BookOperation.uploadBookList();
		System.out.println("\nDeleting success");
		return 0;
	}

	/**
	 * Find the next date of thisDate
	 * 
	 * @param thisDate
	 * @return next date
	 */

	private int[] remainingRoomNumber(Date checkIn, Date checkOut, int hotelId) {
		// the vacancy of check-in date
		int[] minRoomNumber = roomOfThatDate(checkIn, hotelId);
		Date nextDate = nextDate(checkIn);
		// if next date is not the check-out date, continue the loop.
		while (!nextDate.equals(checkOut)) {
			// the vacancy of next date
			int[] roomNumberTomorrow = roomOfThatDate(nextDate, hotelId);
			// compare remainRoomNumber with remainRoomNumber2
			// store the less number
			for (int i = 0; i < minRoomNumber.length; i++) {
				minRoomNumber[i] = (minRoomNumber[i] <= roomNumberTomorrow[i]) ? minRoomNumber[i]
						: roomNumberTomorrow[i];
			}
			nextDate = nextDate(nextDate);
		}
		return minRoomNumber;
	}

	private int[] roomOfThatDate(Date theDate, int hotelId) {
		// initialize the array as all initial room number.
		int[] totalRoom = new int[3];
		for (int i = 0; i < 3; i++) {
			totalRoom[i] = Hotel.ALLHOTEL[hotelId].getRoomCombination()[i];
		}
		// Traverse the whole booklist
		for (int i = 0; i < Book.bookList.size(); i++) {
			if (Book.bookList.get(i).getHotelId() == hotelId) {
				// check if the books in bookList is same date of theDate, too.
				Date currentDatePointer = new Date(Book.bookList.get(i).getCheckInDate().getTime());
				int thisBookNights = Book.bookList.get(i).getNights();
				// Traverse every dates occupied by the current Book(for "nights" times)
				for (int j = 0; j < thisBookNights; j++) {
					// For the date that matches with parameter(theDate) we subtract the number of
					// each type of room
					if (theDate.equals(currentDatePointer)) {
						totalRoom[0] -= Book.bookList.get(i).getRoomCombination()[0];
						totalRoom[1] -= Book.bookList.get(i).getRoomCombination()[1];
						totalRoom[2] -= Book.bookList.get(i).getRoomCombination()[2];
					}
					currentDatePointer = nextDate(currentDatePointer);
				}
			}
		}
		return totalRoom;
	}

	/**
	 * Check the if the check-in date is valid. The date is valid only if it's after
	 * today.
	 * 
	 * @param checkInDate
	 * @return true if the input checkInDate is valid.
	 * @throws InputException
	 */
	private int validCheckInDate(Date checkIn, Date checkOut) {
		// check check-in and check-out date are not null
		if (checkIn == null || checkOut == null) {
			// ("error: The format of date input should be \"MM/dd/yyyy\"");
			return 1;
		}
		// check check-in date is not before today
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date today = calendar.getTime();
		if (today.after(checkIn)) {
			// ("error: The check in date should not be in the past.");
			return 2;
		}
		if (!checkOut.after(checkIn)) {
			// ("error: The check out date should be after check in date.");
			return 3;
		}
		// ("Everything's Fine");
		return 0;
	}

	/**
	 * To turn a String "MM/dd/yyyy" to a Date obj.
	 * 
	 * @param str
	 * @return the Date obj according to the str.Return null if the String is not a
	 *         valid date
	 */
	private Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(str, new ParsePosition(0));
	}

	private String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}

	private int calculateTotalPrice(int hotelId, int nights, int[] newCombination) {
		Room[] roomInfo = Hotel.ALLHOTEL[hotelId].getRoomInfo();
		int priceToReturn = 0;
		priceToReturn += nights * roomInfo[0].getPrice() * newCombination[0];
		priceToReturn += nights * roomInfo[1].getPrice() * newCombination[1];
		priceToReturn += nights * roomInfo[2].getPrice() * newCombination[2];
		return priceToReturn;
	}

	private int calculateNight(Date checkIn, Date checkOut) {
		int toReturn = 0;
		Date datePointer = new Date(checkIn.getTime());
		while (!datePointer.equals(checkOut)) {
			toReturn++;
			datePointer = nextDate(datePointer);
		}
		return toReturn;
	}

	private Date nextDate(Date thisDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}
}
