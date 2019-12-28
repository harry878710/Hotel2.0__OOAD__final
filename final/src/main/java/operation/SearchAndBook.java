package operation;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import bookAndUser.BookList;
import bookAndUser.BookOperation;
import hotelAndRoom.HotelList;
import hotelAndRoom.Room;

public class SearchAndBook {
	// default constructor
	public SearchAndBook() {
	}

	/**
	 * Check the vacancy of specified check-in date, nights, number of single,
	 * double and quad room, city. And then sort the list by the specified
	 * operation.
	 * 
	 * @return a string of all vacancy hotels' details.
	 * @throws InputException
	 */
	public String checkVacancy(String checkInDate, int night, int[] roomCombination, String city, String sorting)
			throws InputException {
		Date checkIn = stringToDate(checkInDate);
		// check input format, may throw an exception.
		validCheckInDate(checkIn);
		Date checkOut = calculateCheckOutDate(checkIn, night);
		String checkOutDate = dateToString(checkOut);

		// check if the room number of every hotel in array is enough for new book
		// if the hotel has enough number, store its Id into the arrayList
		// assign city
		ArrayList<Integer> qualifiedHotelId = cityFilter(city,
				hotelsWithEnoughRoom(roomCombination, checkIn, checkOut));

		// sort by operation
		StringBuffer tmp = new StringBuffer("");
		if (sorting.equals("Sorted by Hotel ID(small to large)")) {
			// Sorted by Hotel ID(small to large)
			for (int i = 0; i < qualifiedHotelId.size(); i++) {
				int hotelId = qualifiedHotelId.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += HotelList.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomCombination[0]
						+ "\n Double : " + roomCombination[1] + "\n Quad : " + roomCombination[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Hotel ID(large to small)")) {
			// Sorted by Hotel ID(large to small)
			for (int i = qualifiedHotelId.size() - 1; i >= 0; i--) {
				int hotelId = qualifiedHotelId.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += HotelList.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomCombination[0]
						+ "\n Double : " + roomCombination[1] + "\n Quad : " + roomCombination[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Price(small to large)")) {
			// Sorted by Price(small to large)
			ArrayList<Integer> sortList = sortByPrice(qualifiedHotelId, roomCombination);
			for (int i = 0; i < sortList.size(); i++) {
				int hotelId = sortList.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += HotelList.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomCombination[0]
						+ "\n Double : " + roomCombination[1] + "\n Quad : " + roomCombination[2]
						+ "\n===========================================\n");
			}
		} else if (sorting.equals("Sorted by Price(large to small)")) {
			// Sorted by Price(large to small)
			ArrayList<Integer> sortList = sortByPrice(qualifiedHotelId, roomCombination);
			for (int i = sortList.size() - 1; i >= 0; i--) {
				int hotelId = sortList.get(i);
				int price = 0;
				for (int j = 0; j < 3; j++) {
					price += HotelList.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate + "\n" + "Stay nights : " + night
						+ " nights, Total price : " + price + "\nRoom : \n" + " Single : " + roomCombination[0]
						+ "\n Double : " + roomCombination[1] + "\n Quad : " + roomCombination[2]
						+ "\n===========================================\n");
			}
		}

		String toReturn = new String(tmp);
		return toReturn;
	}

	public ArrayList<Integer> vacancyHotels(String checkInDate, int night, int[] roomCombination, String city)
			throws InputException {
		// turn the String object of date to a Date object
		Date checkIn = stringToDate(checkInDate);
		// check input format, may throw an exception.
		validCheckInDate(checkIn);
		Date checkOut = calculateCheckOutDate(checkIn, night);
		// Date checkOut = parseString(checkOutDate_str);

		// find the number of people need how many of room with the three type of room
		// check if the room number of every hotel in array is enough for new book
		// if the hotel has enough number, store its Id into the arrayList
		// assign city
		ArrayList<Integer> qualifiedHotelId = cityFilter(city,
				hotelsWithEnoughRoom(roomCombination, checkIn, checkOut));

		return qualifiedHotelId;
	}

	/**
	 * Book the specified order.
	 * 
	 * @return the bookId if booked sucessfully.
	 */
	public String commitBook(String checkInDate, int night, int hotelId, String userId, int[] roomCombination) {
		int singleRoom = roomCombination[0];
		int doubleRoom = roomCombination[1];
		int quadroRoom = roomCombination[2];

		Date checkIn = stringToDate(checkInDate);
		Date checkOut = calculateCheckOutDate(checkIn, night);
		// check available of room again.
		int[][] remainRoomNumber = remainingRoomNumber(checkIn, checkOut);
		if (remainRoomNumber[hotelId][0] < singleRoom || remainRoomNumber[hotelId][1] < doubleRoom
				|| remainRoomNumber[hotelId][2] < quadroRoom) {
			// Room number is not enough.
			if (singleRoom > HotelList.ALLHOTEL[hotelId].getRoomCombination()[0] || doubleRoom > HotelList.ALLHOTEL[hotelId].getRoomCombination()[1]
					|| quadroRoom >= HotelList.ALLHOTEL[hotelId].getRoomCombination()[2]) {
				System.out.println("Room number is never enough.");
				return null;
			} else {
				System.out.println("Room number is not enough.");
				return null;
			}
		}

		DecimalFormat decimal = new DecimalFormat("00000");
		String bookId = decimal.format(BookList.bookedNumber);
		String checkOutDate = dateToString(checkOut);
		int price = calculateTotalPrice(hotelId, night, roomCombination);
		BookOperation.addBook(bookId, hotelId, singleRoom, doubleRoom, quadroRoom, price, night, checkInDate,
				checkOutDate, userId);

		BookList.bookList = BookOperation.uploadBookList();
		BookList.bookedNumber += 1;
		return bookId;
	}

	/**
	 * To turn a String "yyyy/MM/dd" to a Date obj.
	 * 
	 * @param str
	 * @return the Date obj according to the str.Return null if the String is not a
	 *         valid date
	 */
	private Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.parse(str, new ParsePosition(0));
	}

	/**
	 * Turn a Date object into String form
	 * 
	 * @param date
	 * 
	 * @return
	 */
	private String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
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
	 * Check the if the check-in date is valid. The date is valid only if it's after
	 * today.
	 * 
	 * @param checkInDate
	 * @return true if the input checkInDate is valid.
	 * @throws InputException
	 */
	public boolean validCheckInDate(Date checkIn) throws InputException {
		// check check-in and check-out date are not null
		if (checkIn == null) {
			throw new InputException("error: Input of date should be \"yyyy/mm/dd\"");
		}
		// check check-in date is later than today
		long currentTime = System.currentTimeMillis();
		Date today = new Date(currentTime);
		if (!today.before(checkIn)) {
			throw new InputException("Invalid date");
		}
		return true;
	}

	/**
	 * Find the remaining room number of several days.
	 * 
	 * @param checkIn
	 * @param checkOut
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] remainingRoomNumber(Date checkIn, Date checkOut) {
		// the vacancy of check-in date
		int[][] minRoomNumber = roomOfThatDate(checkIn);
		Date nextDate = nextDate(checkIn);
		// if next date is not the check-out date, continue the loop.
		while (!nextDate.equals(checkOut)) {
			// the vacancy of next date
			int[][] roomNumberTomorrow = roomOfThatDate(nextDate);
			// compare remainRoomNumber with remainRoomNumber2
			// store the less number
			for (int i = 0; i < minRoomNumber.length; i++) {
				for (int j = 0; j < minRoomNumber[i].length; j++) {
					minRoomNumber[i][j] = (minRoomNumber[i][j] <= roomNumberTomorrow[i][j]) ? minRoomNumber[i][j]
							: roomNumberTomorrow[i][j];
				}
			}
			nextDate = nextDate(nextDate);
		}
		return minRoomNumber;
	}

	/**
	 * Find the remaining room number of the specified date, theDate.
	 * 
	 * @param theDate
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] roomOfThatDate(Date theDate) {
		// initialize the array as all initial room number.
		int[][] totalRoom = new int[HotelList.TOTAL_NUMBER_OF_HOTEL][3];
		for (int i = 0; i < totalRoom.length; i++) {
			for (int j = 0; j < totalRoom[i].length; j++) {
				totalRoom[i][j] = HotelList.ALLHOTEL[i].getRoomCombination()[j];
			}
		}
		// Traverse the whole booklist
		for (int i = 0; i < BookList.bookList.size(); i++) {
			int myHotelId = BookList.bookList.get(i).getHotelId();
			// check if the books in bookList is same date of theDate, too.
			Date currentDatePointer = new Date(BookList.bookList.get(i).getCheckInDate().getTime());
			int thisBookNights = BookList.bookList.get(i).getNights();
			// Traverse every dates occupied by the current Book(for "nights" times)
			for (int j = 0; j < thisBookNights; j++) {
				// For the date that matches with parameter(theDate) we subtract the number of
				// each type of room
				if (theDate.equals(currentDatePointer)) {
					totalRoom[myHotelId][0] -= BookList.bookList.get(i).getRoomCombination()[0];
					totalRoom[myHotelId][1] -= BookList.bookList.get(i).getRoomCombination()[1];
					totalRoom[myHotelId][2] -= BookList.bookList.get(i).getRoomCombination()[2];

				}
				currentDatePointer = nextDate(currentDatePointer);
			}
		}
		return totalRoom;
	}

	/**
	 * Caculate the check out date of specified check in date & staying nights
	 * 
	 * @param checkInDate
	 * @param night
	 * @return
	 */
	private Date calculateCheckOutDate(Date checkInDate, int night) {
		Date toReturn = new Date(checkInDate.getTime());
		for (int i = 0; i < night; i++) {
			toReturn = nextDate(toReturn);
		}
		return toReturn;
	}

	/**
	 * under the specific number of people, list all the possibility of room
	 * combination
	 * 
	 * @param arg_numOfPeople
	 * @return
	 */
	// 31 should be changed to the possible maximum in database
	public int[] possibleRoomNumber(int arg_numOfPeople) {
		int s, d, q;
		Set<Integer> possibleRoomNumber = new HashSet<Integer>();
		for (s = 0; s < 31; s++) {
			for (d = 0; d < 31; d++) {
				for (q = 0; q < 31; q++) {
					if ((s + 2 * d + 4 * q) == arg_numOfPeople) {
						possibleRoomNumber.add(s + d + q);
					}
				}
			}
		}
		int[] sortedRoomNumber = new int[possibleRoomNumber.size()];
		Iterator<Integer> it = possibleRoomNumber.iterator();
		int j = 0;
		while (it.hasNext()) {
			Integer i = it.next();
			sortedRoomNumber[j] = i;
			j++;
		}

		return sortedRoomNumber;
	}

	/**
	 * Calculate all possible combination of types of room, under specified number
	 * of people
	 * 
	 * @param arg_numOfPeople
	 * @param arg_numOfRoom
	 * @return
	 */
	// 31 should be modified
	public ArrayList<Integer[]> possibleRoomCombination(int arg_numOfPeople, int arg_numOfRoom) {
		int s, d, q;
		ArrayList<Integer[]> combination = new ArrayList<Integer[]>();
		for (s = 0; s < 31; s++) {
			for (d = 0; d < 31; d++) {
				for (q = 0; q < 31; q++) {
					if ((s + 2 * d + 4 * q) == arg_numOfPeople && (s + d + q) == arg_numOfRoom) {
						Integer[] tmp = { s, d, q };
						combination.add(tmp);
					}
				}
			}
		}
		return combination;
	}

	/**
	 * List all the hotels that have enough room for our search
	 * 
	 * @param arg_roomCombination
	 * @param checkIn
	 * @param checkOut
	 * @return
	 */
	private ArrayList<Integer> hotelsWithEnoughRoom(int[] arg_roomCombination, Date checkIn, Date checkOut) {
		// the whole remain room number of all hotel and room type
		int[][] remainRoomNumber = remainingRoomNumber(checkIn, checkOut);
		ArrayList<Integer> hotelsWithEnoughRoom = new ArrayList<Integer>(1500);
		for (int i = 0; i < remainRoomNumber.length; i++) {
			if (remainRoomNumber[i][0] >= arg_roomCombination[0] && remainRoomNumber[i][1] >= arg_roomCombination[1]
					&& remainRoomNumber[i][2] >= arg_roomCombination[2]) {
				hotelsWithEnoughRoom.add(i);
			}
		}
		return hotelsWithEnoughRoom;
	}

	/**
	 * List all the hotels that have enough room and is in the specified city
	 * 
	 * @param city
	 * @param hotelsWithEnoughRoom
	 * @return
	 */
	private ArrayList<Integer> cityFilter(String city, ArrayList<Integer> hotelsWithEnoughRoom) {
		ArrayList<Integer> hotelsInSpecificCity = new ArrayList<Integer>();
		switch (city) {
		case "SomeWhere":
			// every city
			hotelsInSpecificCity = hotelsWithEnoughRoom;
			break;
		case "Taipei":
			// only Taipei
			for (int i = 0; i < hotelsWithEnoughRoom.size(); i++) {
				String locality = HotelList.ALLHOTEL[hotelsWithEnoughRoom.get(i)].getLocality();
				if (locality.equals("台北")) {
					hotelsInSpecificCity.add(hotelsWithEnoughRoom.get(i));
				}
			}
			break;
		case "Taichung":
			// only Taichung
			for (int i = 0; i < hotelsWithEnoughRoom.size(); i++) {
				String locality = HotelList.ALLHOTEL[hotelsWithEnoughRoom.get(i)].getLocality();
				if (locality.equals("台中")) {
					hotelsInSpecificCity.add(hotelsWithEnoughRoom.get(i));
				}
			}
			break;
		case "Kaohsiung":
			// only Kaohsiung
			for (int i = 0; i < hotelsWithEnoughRoom.size(); i++) {
				String locality = HotelList.ALLHOTEL[hotelsWithEnoughRoom.get(i)].getLocality();
				if (locality.equals("高雄")) {
					hotelsInSpecificCity.add(hotelsWithEnoughRoom.get(i));
				}
			}
			break;
		}
		return hotelsInSpecificCity;
	}

	private ArrayList<Integer> sortByPrice(ArrayList<Integer> qualifiedHotelId, int[] roomCombination) {
		Map<Integer, Integer> sortMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < qualifiedHotelId.size(); i++) {
			int hotelId = qualifiedHotelId.get(i);
			int price = 0;
			for (int j = 0; j < 3; j++) {
				price += HotelList.ALLHOTEL[hotelId].getRoomInfo()[j].getPrice() * roomCombination[j];
			}
			sortMap.put(hotelId, price);
		}
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
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
			sortedList.add(keyOfMin);
			sortMap.remove(keyOfMin);
		}
		return sortedList;
	}

	private int calculateTotalPrice(int hotelId, int nights, int[] newCombination) {
		Room[] roomInfo = HotelList.ALLHOTEL[hotelId].getRoomInfo();
		int priceToReturn = 0;
		priceToReturn += nights * roomInfo[0].getPrice() * newCombination[0];
		priceToReturn += nights * roomInfo[1].getPrice() * newCombination[1];
		priceToReturn += nights * roomInfo[2].getPrice() * newCombination[2];
		return priceToReturn;
	}
}
