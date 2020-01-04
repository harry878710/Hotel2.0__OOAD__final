package book_Hotel_Room;

public class HotelList {

	public static final Hotel[] ALLHOTEL;
	public static int TOTAL_NUMBER_OF_HOTEL;

	static {
		ALLHOTEL = HotelOperation.uploadHotelList();
		TOTAL_NUMBER_OF_HOTEL = HotelOperation.sizeOfHotelList();		
	}
}
