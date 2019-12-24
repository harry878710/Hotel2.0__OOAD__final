package operation;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bookAndUser.BookList;
import bookAndUser.BookOperation;
import hotelAndRoom.RoomList;

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
	public int changeReservation(String bookId, int[] newRoomCombination) {
		// check if the order is valid.
		String[] bookMsg = BookOperation.getBook(bookId);
		String checkInDate = bookMsg[2], checkOutDate = bookMsg[3];
		int hotelId = new Integer(bookMsg[4]), single = new Integer(bookMsg[5]), doub = new Integer(bookMsg[6]),
				quad = new Integer(bookMsg[7]);
		Date checkIn = parseString(checkInDate), checkOut = parseString(checkOutDate);

		int[][] remainRoomNumber = remainRoomNumber(checkIn, checkOut);
		remainRoomNumber[hotelId][0] += single;
		remainRoomNumber[hotelId][1] += doub;
		remainRoomNumber[hotelId][2] += quad;
		if (remainRoomNumber[hotelId][0] < newRoomCombination[0] || remainRoomNumber[hotelId][1] < newRoomCombination[1]
				|| remainRoomNumber[hotelId][2] < newRoomCombination[2]) {
			if (newRoomCombination[0] > RoomList.totalRoomNumber[hotelId][0] || newRoomCombination[1] > RoomList.totalRoomNumber[hotelId][1]
					|| newRoomCombination[2] >= RoomList.totalRoomNumber[hotelId][2]) {
				System.out.println("Room number is never enough.");
				return 1;
			} else {
				System.out.println("Room number is not enough.");
				return 2;
			}
		}

		// The order is valid, change the book.
		BookOperation.changeBook(bookId, newRoomCombination[0], newRoomCombination[1], newRoomCombination[2]);
		BookList.bookList = BookOperation.uploadBookList();
		System.out.println("\nChanging success");
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
	 * @return 0 if changed sucessfully.Return 1, or 2 if the change didn't
	 *         succesfully.
	 */
	public int changeReservation(String bookId, String newCheckInDate, String newCheckOutDate) {
		// check input.
		try {
			checkInput(parseString(newCheckInDate));
		} catch (InputException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		// check if the new order is valid
		String[] bookMsg = BookOperation.getBook(bookId);
		String userId = bookMsg[1];
		int hotelId = new Integer(bookMsg[4]), single = new Integer(bookMsg[5]), doub = new Integer(bookMsg[6]),
				quad = new Integer(bookMsg[7]);
		Date newCheckIn = parseString(newCheckInDate), newCheckOut = parseString(newCheckOutDate);
		//BookOperation.deleteBook(bookId);
		int[][] remainRoomNumber = remainRoomNumber(newCheckIn, newCheckOut);
		if (remainRoomNumber[hotelId][0] < single || remainRoomNumber[hotelId][1] < doub
				|| remainRoomNumber[hotelId][2] < quad) {
//			BookOperation.addBook(bookId, userId, checkInDate, checkOutDate, hotelId, single, doub, quad);
//			BookList.bookList = BookOperation.uploadBookList();
			if (single > RoomList.totalRoomNumber[hotelId][0] || doub > RoomList.totalRoomNumber[hotelId][1]
					|| quad >= RoomList.totalRoomNumber[hotelId][2]) {
				System.out.println("Room number is never enough.");
				return 1;
			} else {
				System.out.println("Room number is not enough.");
				return 2;
			}
		}
		// the order is valid, change the order.
		BookOperation.deleteBook(bookId);
		BookOperation.addBook(bookId, userId, newCheckInDate, newCheckOutDate, hotelId, single, doub, quad);
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
	public int changeReservation(String bookId) {
		BookOperation.deleteBook(bookId);
		BookList.bookList = BookOperation.uploadBookList();
		System.out.println("\nDeleting success");
		return 0;
	}

	/**
	 * To turn a String "yyyy/MM/dd" to a Date obj.
	 * 
	 * @param str
	 * @return the Date obj according to the str.Return null if the String is not a
	 *         valid date
	 */
	private Date parseString(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.parse(str, new ParsePosition(0));
	}

	/**
	 * Find the next date of thisDate
	 * 
	 * @param thisDate
	 * @return next date
	 */
	private Date nextDate(Date thisDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}

	/**
	 * Find the remain room number of the specified date, theDate.
	 * 
	 * @param theDate
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] remainRoomNumberOfDate(Date theDate) {
		// initialize the array as all initial room number.
		int[][] aa = new int[1500][3];
		for (int i = 0; i < aa.length; i++) {
			for (int j = 0; j < aa[i].length; j++) {
				aa[i][j] = RoomList.totalRoomNumber[i][j];
			}
		}
		for (int i = 0; i < BookList.bookList.size(); i++) {
			// check if the books in bookList is same date of theDate, too.
			if (theDate.equals(BookList.bookList.get(i).getDate())) {
				int hotelId = BookList.bookList.get(i).getRoom().getHotel().getId();
				int type = -1;
				switch (BookList.bookList.get(i).getRoom().getType()) {
				case "Single":
					type = 0;
					break;
				case "Double":
					type = 1;
					break;
				case "Quad":
					type = 2;
					break;
				}
				// decrease the booked hotel's room type's room number
				aa[hotelId][type] -= 1;
			}
		}
		return aa;
	}

	/**
	 * Find the remain room number of several days.
	 * 
	 * @param checkIn
	 * @param checkOut
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] remainRoomNumber(Date checkIn, Date checkOut) {
		// the vacancy of check-in date
		int[][] remainRoomNumber = remainRoomNumberOfDate(checkIn);
		Date nextDate = nextDate(checkIn);
		// if next date is not the check-out date, continue the loop.
		while (!nextDate.equals(checkOut)) {
			// the vacancy of next date
			int[][] remainRoomNumber2 = remainRoomNumberOfDate(nextDate);
			// compare remainRoomNumber with remainRoomNumber2
			// store the less number
			for (int i = 0; i < remainRoomNumber.length; i++) {
				for (int j = 0; j < remainRoomNumber[i].length; j++) {
					remainRoomNumber[i][j] = (remainRoomNumber[i][j] <= remainRoomNumber2[i][j])
							? remainRoomNumber[i][j]
							: remainRoomNumber2[i][j];
				}
			}
			nextDate = nextDate(nextDate);
		}
		return remainRoomNumber;
	}

	/**
	 * Check the if the check-in date is valid. The date is valid only if it's after
	 * today.
	 * 
	 * @param checkInDate
	 * @return true if the input checkInDate is valid.
	 * @throws InputException
	 */
	private boolean checkInput(Date checkInDate) throws InputException {
		// check check-in and check-out date are not null
		if (checkInDate == null) {
			throw new InputException("error: the input of date should be \"yyyy/mm/dd\"");
		}
		// check check-in date is later than today
		long currentTime = System.currentTimeMillis();
		Date today = new Date(currentTime);
		if (!today.before(checkInDate)) {
			throw new InputException("Invalid date");
		}
		return true;
	}

}