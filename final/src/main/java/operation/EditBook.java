package operation;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bookAndUser.Book;
import bookAndUser.BookList;
import bookAndUser.BookOperation;
import hotelAndRoom.HotelList;
import hotelAndRoom.Room;

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
			if (newRoomCombination[0] > HotelList.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[0]
					|| newRoomCombination[1] > HotelList.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[1]
					|| newRoomCombination[2] > HotelList.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[2]) {
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
		BookList.bookList = BookOperation.uploadBookList();
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

			if (myBook.getRoomCombination()[0] > HotelList.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[0]
					|| myBook.getRoomCombination()[1] > HotelList.ALLHOTEL[myBook.getHotelId()].getRoomCombination()[1]
					|| myBook.getRoomCombination()[2] > HotelList.ALLHOTEL[myBook.getHotelId()]
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
		BookList.bookList = BookOperation.uploadBookList();
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
		BookList.bookList = BookOperation.uploadBookList();
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
			totalRoom[i] = HotelList.ALLHOTEL[hotelId].getRoomCombination()[i];
		}
		// Traverse the whole booklist
		for (int i = 0; i < BookList.bookList.size(); i++) {
			if (BookList.bookList.get(i).getHotelId() == hotelId) {
				// check if the books in bookList is same date of theDate, too.
				Date currentDatePointer = new Date(BookList.bookList.get(i).getCheckInDate().getTime());
				int thisBookNights = BookList.bookList.get(i).getNights();
				// Traverse every dates occupied by the current Book(for "nights" times)
				for (int j = 0; j < thisBookNights; j++) {
					// For the date that matches with parameter(theDate) we subtract the number of
					// each type of room
					if (theDate.equals(currentDatePointer)) {
						totalRoom[0] -= BookList.bookList.get(i).getRoomCombination()[0];
						totalRoom[1] -= BookList.bookList.get(i).getRoomCombination()[1];
						totalRoom[2] -= BookList.bookList.get(i).getRoomCombination()[2];
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
		long currentTime = System.currentTimeMillis();
		Date today = stringToDate(dateToString(new Date(currentTime)));
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
		Room[] roomInfo = HotelList.ALLHOTEL[hotelId].getRoomInfo();
		int priceToReturn = 0;
		priceToReturn += nights * roomInfo[0].getPrice() * newCombination[0];
		priceToReturn += nights * roomInfo[1].getPrice() * newCombination[1];
		priceToReturn += nights * roomInfo[2].getPrice() * newCombination[2];
		return priceToReturn;
	}

	private int calculateNight(Date checkIn, Date checkOut) {
		int toReturn = 0;
		Date datePointer = new Date(checkIn.getTime());
		while (datePointer != checkOut) {
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
