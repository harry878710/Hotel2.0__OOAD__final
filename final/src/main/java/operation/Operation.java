package operation;

import java.util.*;
import java.util.Map.Entry;

import bookAndUser.*;

import java.text.*;

import hotelAndRoom.*;

public class Operation {

	/**
	 * Check the vacancy of specified check-in date, nights, number of people,
	 * number of room, city. And sorted the list by the specified operation.
	 * 
	 * @param checkInDate
	 * @param night
	 * @param numOfPeople
	 * @param numOfRoom
	 * @param city
	 * @param sorting   to decide how to sort the hotel lists.
	 * @return a string of all vacancy hotels' details.
	 * @throws InputException
	 */
	public static String checkVacancy(String checkInDate, int night, int numOfPeople, int numOfRoom, String city,
			String sorting) throws InputException {
		// turn the String object of date to a Date object
		Date checkIn = parseString(checkInDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date checkOut = sdf.parse(checkInDate, new ParsePosition(0));
		String checkOutDate = null;
		if (checkOut != null) {
			for (int i = night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
			checkOutDate = sdf.format(checkOut);
		}
		// check input format, may throw an exception.
		checkInput(checkIn);
		// find the number of people need how many of room with the three type of room
		int[] roomType = numOfPeopleAndRoom(numOfPeople, numOfRoom);
		// the whole remain room number of all hotel and room type
		int[][] remainRoomNumber = remainRoomNumber(checkIn, checkOut);
		// check if the room number of every hotel in array is enough for new book
		// if the hotel has enough number, store its Id into the arrayList
		ArrayList<Integer> valid = new ArrayList<Integer>(1500);
		for (int i = 0; i < remainRoomNumber.length; i++) {
			if (remainRoomNumber[i][0] >= roomType[0] && remainRoomNumber[i][1] >= roomType[1]
					&& remainRoomNumber[i][2] >= roomType[2]) {
				valid.add(i);
			}
		}

		// sort by city
		ArrayList<Integer> validCity = new ArrayList<Integer>();
		if (city.equals("SomeWhere")) {
			// every city
			validCity = valid;
		} else if (city.equals("Taipei")) {
			// only Taipei
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("����")) {
					validCity.add(valid.get(i));
				}
			}
		} else if (city.equals("Taichung")) {
			// only Taichung
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("�銝�")) {
					validCity.add(valid.get(i));
				}
			}
		} else if (city.equals("Kaohsiung")) {
			// only Kaohsiung
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("擃��")) {
					validCity.add(valid.get(i));
				}
			}
		}

		// sort by operation
		StringBuffer tmp = new StringBuffer("");
		if (sorting.equals("Sorted by Hotel ID(small to large)")) {
			// Sorted by Hotel ID(small to large)
			for (int i = 0; i < validCity.size(); i++) {
				int hotelId = validCity.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomType[0]
						+ "\n Double : " + roomType[1] + "\n Quad : " + roomType[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Hotel ID(large to small)")) {
			// Sorted by Hotel ID(large to small)
			for (int i = validCity.size() - 1; i >= 0; i--) {
				int hotelId = validCity.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomType[0]
						+ "\n Double : " + roomType[1] + "\n Quad : " + roomType[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Price(small to large)")) {
			// Sorted by Price(small to large)
			Map<Integer, Integer> sortMap = new HashMap<Integer, Integer>();
			for (int i = 0; i < validCity.size(); i++) {
				int hotelId = validCity.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				sortMap.put(hotelId, price);
			}
			ArrayList<Integer> sortList = new ArrayList<Integer>();
			int x = sortMap.size();
			for (int i = 0; i < x; i++) {
				int keyOfMin = -1;
				Iterator<Entry<Integer, Integer>> iter = sortMap.entrySet().iterator();
				if (iter.hasNext()) {
					Map.Entry<Integer, Integer> firstEntry = (Map.Entry<Integer, Integer>) iter.next();
					keyOfMin = firstEntry.getKey();
					int min = firstEntry.getValue();
					while (iter.hasNext()) {
						Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter.next();
						Integer key = entry.getKey();
						Integer val = entry.getValue();
						keyOfMin = (val < min) ? key : keyOfMin;
						min = (val < min) ? val : min;
					}
				} else
					break;
				sortList.add(keyOfMin);
				sortMap.remove(keyOfMin);
			}
			for (int i = 0; i < sortList.size(); i++) {
				int hotelId = sortList.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomType[0]
						+ "\n Double : " + roomType[1] + "\n Quad : " + roomType[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Price(large to small)")) {
			// Sorted by Price(large to small)
			Map<Integer, Integer> sortMap = new HashMap<Integer, Integer>();
			for (int i = 0; i < validCity.size(); i++) {
				int hotelId = validCity.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				sortMap.put(hotelId, price);
			}
			ArrayList<Integer> sortList = new ArrayList<Integer>();
			int x = sortMap.size();
			for (int i = 0; i < x; i++) {
				int keyOfMin = -1;
				Iterator<Entry<Integer, Integer>> iter = sortMap.entrySet().iterator();
				if (iter.hasNext()) {
					Map.Entry<Integer, Integer> firstEntry = (Map.Entry<Integer, Integer>) iter.next();
					keyOfMin = firstEntry.getKey();
					int min = firstEntry.getValue();
					while (iter.hasNext()) {
						Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter.next();
						Integer key = entry.getKey();
						Integer val = entry.getValue();
						keyOfMin = (val < min) ? key : keyOfMin;
						min = (val < min) ? val : min;
					}
				} else
					break;
				sortList.add(keyOfMin);
				sortMap.remove(keyOfMin);
			}
			for (int i = sortList.size() - 1; i >= 0; i--) {
				int hotelId = sortList.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomType[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomType[0]
						+ "\n Double : " + roomType[1] + "\n Quad : " + roomType[2]
						+ "\n===========================================\n");
			}
		}

		String toReturn = new String(tmp);
		return toReturn;
	}

	/**
	 * Check the vacancy of specified check-in date, nights, number of people,
	 * number of room, city. And sorted the list by the specified operation.
	 * 
	 * @param checkInDate
	 * @param night
	 * @param numOfPeople
	 * @param numOfRoom
	 * @param city
	 * @param operation
	 * @return a list of hotel ID of vacancy hotels.
	 * @throws InputException
	 */
	public static ArrayList<Integer> vacancyHotels(String checkInDate, int night, int numOfPeople, int numOfRoom,
			String city) throws InputException {
		Date checkIn = parseString(checkInDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date checkOut = sdf.parse(checkInDate, new ParsePosition(0));
		String checkOutDate = null;
		if (checkOut != null) {
			for (int i = night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
			checkOutDate = sdf.format(checkOut);
		}
		checkInput(checkIn);
		int[] roomType = numOfPeopleAndRoom(numOfPeople, numOfRoom);
		int[][] remainRoomNumber = remainRoomNumber(checkIn, checkOut);
		ArrayList<Integer> valid = new ArrayList<Integer>(1500);
		for (int i = 0; i < remainRoomNumber.length; i++) {
			if (remainRoomNumber[i][0] >= roomType[0] && remainRoomNumber[i][1] >= roomType[1]
					&& remainRoomNumber[i][2] >= roomType[2]) {
				valid.add(i);
			}
		}
		ArrayList<Integer> validHotels = new ArrayList<Integer>();
		if (city.equals("SomeWhere")) {
			validHotels = valid;
		} else if (city.equals("Taipei")) {
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("����")) {
					validHotels.add(valid.get(i));
				}
			}
		} else if (city.equals("Taichung")) {
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("�銝�")) {
					validHotels.add(valid.get(i));
				}
			}
		} else if (city.equals("Kaohsiung")) {
			for (int i = 0; i < valid.size(); i++) {
				String locality = HotelList.ALLHOTEL[valid.get(i)].getLocality();
				if (locality.equals("擃��")) {
					validHotels.add(valid.get(i));
				}
			}
		}
		return validHotels;
	}

	/**
	 * Book the specified order.
	 * 
	 * @param userId
	 * @param hotelId
	 * @param checkInDate
	 * @param night
	 * @param single
	 * @param doub
	 * @param quad
	 * @return the bookId if booked sucessfully.
	 */
	public static String reserve(String userId, int hotelId, String checkInDate, int night, int single, int doub,
			int quad) {
		Date checkIn = parseString(checkInDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date checkOut = sdf.parse(checkInDate, new ParsePosition(0));
		String checkOutDate = null;
		if (checkOut != null) {
			for (int i = night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
			checkOutDate = sdf.format(checkOut);
		}
		// check input
		try {
			checkInput(checkIn);
		} catch (InputException e) {
			System.out.println(e.getMessage());
			return null;
		}

		// check available of room again.
		int[][] remainRoomNumber = remainRoomNumber(checkIn, checkOut);
		if (remainRoomNumber[hotelId][0] < single || remainRoomNumber[hotelId][1] < doub
				|| remainRoomNumber[hotelId][2] < quad) {
			// Room number is not enough.
			if (single > RoomList.totalRoomNumber[hotelId][0] || doub > RoomList.totalRoomNumber[hotelId][1]
					|| quad >= RoomList.totalRoomNumber[hotelId][2]) {
				System.out.println("Room number is never enough.");
				return null;
			} else {
				System.out.println("Room number is not enough.");
				return null;
			}
		}

		DecimalFormat decimal = new DecimalFormat("00000");
		String bookId = decimal.format(BookList.bookedNumber);
//		int price = 0;
//		price += RoomList.ALLROOM[hotelId][0].getPrice() * single;
//		price += RoomList.ALLROOM[hotelId][1].getPrice() * doub;
//		price += RoomList.ALLROOM[hotelId][2].getPrice() * quad;
//		System.out.println("\nBooking success");
//		System.out
//				.println("Book ID: " + bookId + ", User ID: " + userId + "\n" + HotelList.ALLHOTEL[hotelId].toString());
//		System.out.println("Check-in date: " + checkInDate + ", Check-out Date: " + checkOutDate);
//		System.out.println("Stay nights:  " + night + " nights, Total price: " + price);
//		System.out.println("Room: \n" + " Single: " + single + "\n Double: " + doub + "\n Quad: " + quad);
		BookOperation.addBook(bookId, userId, checkInDate, checkOutDate, hotelId, single, doub, quad);
		BookList.bookList = BookOperation.uploadBookList();
		BookList.bookedNumber += 1;
		return bookId;
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
	 *         succesfully.
	 */
	public static int changeReservation(String bookId, int newSingle, int newDoub, int newQuad) {
		// check if the order is valid.
		String[] bookMsg = BookOperation.getBook(bookId);
		String checkInDate = bookMsg[2], checkOutDate = bookMsg[3];
		int hotelId = new Integer(bookMsg[4]), single = new Integer(bookMsg[5]), doub = new Integer(bookMsg[6]),
				quad = new Integer(bookMsg[7]);
		Date checkIn = parseString(checkInDate);
		Date checkOut = parseString(checkOutDate);

		int[][] remainRoomNumber = remainRoomNumber(checkIn, checkOut);
		remainRoomNumber[hotelId][0] += single;
		remainRoomNumber[hotelId][1] += doub;
		remainRoomNumber[hotelId][2] += quad;
		if (remainRoomNumber[hotelId][0] < newSingle || remainRoomNumber[hotelId][1] < newDoub
				|| remainRoomNumber[hotelId][2] < newQuad) {
			if (newSingle > RoomList.totalRoomNumber[hotelId][0] || newDoub > RoomList.totalRoomNumber[hotelId][1]
					|| newQuad >= RoomList.totalRoomNumber[hotelId][2]) {
				System.out.println("Room number is never enough.");
				return 1;
			} else {
				System.out.println("Room number is not enough.");
				return 2;
			}
		}

		// The order is valid, change the book.
		BookOperation.changeBook(bookId, newSingle, newDoub, newQuad);
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
	public static int changeReservation(String bookId, String newCheckInDate, String newCheckOutDate) {
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
		String checkInDate = bookMsg[2], checkOutDate = bookMsg[3];
		int hotelId = new Integer(bookMsg[4]), single = new Integer(bookMsg[5]), doub = new Integer(bookMsg[6]),
				quad = new Integer(bookMsg[7]);
		BookOperation.deleteBook(bookId);
		Date newCheckIn = parseString(newCheckInDate);
		Date newCheckOut = parseString(newCheckOutDate);
		int[][] remainRoomNumber = remainRoomNumber(newCheckIn, newCheckOut);
		if (remainRoomNumber[hotelId][0] < single || remainRoomNumber[hotelId][1] < doub
				|| remainRoomNumber[hotelId][2] < quad) {
			BookOperation.addBook(bookId, userId, checkInDate, checkOutDate, hotelId, single, doub, quad);
			BookList.bookList = BookOperation.uploadBookList();
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
	public static int changeReservation(String bookId) {
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
	private static Date parseString(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.parse(str, new ParsePosition(0));
	}

	/**
	 * Check the if the check-in date is valid. The date is valid only if it's after
	 * today.
	 * 
	 * @param checkInDate
	 * @return true if the input checkInDate is valid.
	 * @throws InputException
	 */
	private static boolean checkInput(Date checkInDate) throws InputException {
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

	/**
	 * Find the relation of number of people and room
	 * 
	 * @param numOfPeople
	 * @param numOfRoom
	 * @return an array stores the rooms of different kind of room.
	 */
	private static int[] numOfPeopleAndRoom(int numOfPeople, int numOfRoom) {
		int[] roomType = new int[3];// {number of 嚙瑾嚙瘡嚙踝蕭, 嚙瘦嚙瘡嚙踝蕭, 嚙罵嚙瘡嚙踝蕭}
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

	/**
	 * Find the next date of thisDate
	 * 
	 * @param thisDate
	 * @return next date
	 */
	private static Date nextDate(Date thisDate) {
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
	private static int[][] remainRoomNumberOfDate(Date theDate) {
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
	private static int[][] remainRoomNumber(Date checkIn, Date checkOut) {
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

}
