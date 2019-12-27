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
import hotelAndRoom.RoomList;

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

		System.out.println("000" + roomCombination[0] + roomCombination[1] + roomCombination[2]);
		// turn the String object of date to a Date object
		Date checkIn = parseString(checkInDate);
		// check input format, may throw an exception.
		validCheckInDate(checkIn);
		String checkOutDate_str = calculateCheckOutDate(checkInDate, night);
		Date checkOut = parseString(checkOutDate_str);

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
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate_str + "\n" + "Stay nights : " + night
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
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate_str + "\n" + "Stay nights : " + night
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
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate_str + "\n" + "Stay nights : " + night
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
					price += RoomList.ALLROOM[hotelId][j].getPrice() * roomCombination[j];
				}
				tmp.append("\n" + HotelList.ALLHOTEL[hotelId].toString() + "\nCheck-in date : " + checkInDate
						+ ", Check-out date : " + checkOutDate_str + "\n" + "Stay nights : " + night
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
		Date checkIn = parseString(checkInDate);
		// check input format, may throw an exception.
		validCheckInDate(checkIn);
		String checkOutDate_str = calculateCheckOutDate(checkInDate, night);
		Date checkOut = parseString(checkOutDate_str);

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
	public String commitBook(String checkInDate_str, int night, int hotelId, String userId, int[] roomCombination) {
		int singleRoom = roomCombination[0];
		int doubleRoom = roomCombination[1];
		int quadroRoom = roomCombination[2];

		Date checkIn = parseString(checkInDate_str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String checkOutDate_str = calculateCheckOutDate(checkInDate_str, night);
		Date checkOut = parseString(checkOutDate_str);
		// check available of room again.
		int[][] remainRoomNumber = remainingRoomNumber(checkIn, checkOut);
		if (remainRoomNumber[hotelId][0] < singleRoom || remainRoomNumber[hotelId][1] < doubleRoom
				|| remainRoomNumber[hotelId][2] < quadroRoom) {
			// Room number is not enough.
			if (singleRoom > RoomList.totalRoomNumber[hotelId][0] || doubleRoom > RoomList.totalRoomNumber[hotelId][1]
					|| quadroRoom >= RoomList.totalRoomNumber[hotelId][2]) {
				System.out.println("Room number is never enough.");
				return null;
			} else {
				System.out.println("Room number is not enough.");
				return null;
			}
		}

		DecimalFormat decimal = new DecimalFormat("00000");
		String bookId = decimal.format(BookList.bookedNumber);
		BookOperation.addBook(bookId, userId, checkInDate_str, checkOutDate_str, hotelId, singleRoom, doubleRoom,
				quadroRoom);
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
	public Date parseString(String str) {
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
	 * Check the if the check-in date is valid. The date is valid only if it's after
	 * today.
	 * 
	 * @param checkInDate
	 * @return true if the input checkInDate is valid.
	 * @throws InputException
	 */
	public boolean validCheckInDate(Date checkInDate) throws InputException {
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
	 * Find the remain room number of several days.
	 * 
	 * @param checkIn
	 * @param checkOut
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] remainingRoomNumber(Date checkIn, Date checkOut) {
		// the vacancy of check-in date
		int[][] minRoomNumber = remainingRoomNumberOfThatDate(checkIn);
		Date nextDate = nextDate(checkIn);
		// if next date is not the check-out date, continue the loop.
		while (!nextDate.equals(checkOut)) {
			// the vacancy of next date
			int[][] roomNumberTomorrow = remainingRoomNumberOfThatDate(nextDate);
			// compare remainRoomNumber with remainRoomNumber2
			// store the less number
			for (int i = 0; i < minRoomNumber.length; i++) {
				for (int j = 0; j < minRoomNumber[i].length; j++) {
					minRoomNumber[i][j] = (minRoomNumber[i][j] <= roomNumberTomorrow[i][j])
							? minRoomNumber[i][j]
							: roomNumberTomorrow[i][j];
				}
			}
			nextDate = nextDate(nextDate);
		}
		return minRoomNumber;
	}

	/**
	 * Find the remain room number of the specified date, theDate.
	 * 
	 * @param theDate
	 * @return an 2-d array. The first dimension indicates the hotel ID. The second
	 *         dimension indicates the room type.
	 */
	private int[][] remainingRoomNumberOfThatDate(Date theDate) {
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

	private String calculateCheckOutDate(String arg_checkIn, int arg_night) {
		Date checkOut = parseString(arg_checkIn);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (checkOut != null) {
			for (int i = arg_night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
		}
		return sdf.format(checkOut);
	}

	/**
	 * under the specific number of people, list all the possibility of room
	 * combination
	 * 
	 * @param arg_numOfPeople
	 * @return
	 */
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

	private ArrayList<Integer> hotelsWithEnoughRoom(int[] arg_roomCombination, Date arg_checkIn, Date arg_checkOut) {
		// the whole remain room number of all hotel and room type
		int[][] remainRoomNumber = remainingRoomNumber(arg_checkIn, arg_checkOut);
		ArrayList<Integer> hotelsWithEnoughRoom = new ArrayList<Integer>(1500);
		for (int i = 0; i < remainRoomNumber.length; i++) {
			if (remainRoomNumber[i][0] >= arg_roomCombination[0] && remainRoomNumber[i][1] >= arg_roomCombination[1]
					&& remainRoomNumber[i][2] >= arg_roomCombination[2]) {
				hotelsWithEnoughRoom.add(i);
			}
		}
		return hotelsWithEnoughRoom;
	}

	private ArrayList<Integer> cityFilter(String arg_city, ArrayList<Integer> hotelsWithEnoughRoom) {
		ArrayList<Integer> hotelsInSpecificCity = new ArrayList<Integer>();
		switch (arg_city) {
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
				price += RoomList.ALLROOM[hotelId][j].getPrice() * roomCombination[j];
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
}
