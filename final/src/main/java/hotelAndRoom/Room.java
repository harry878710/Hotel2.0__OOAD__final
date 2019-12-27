package hotelAndRoom;
public class Room {

	private String type;
	private int price;
	private Hotel hotel;

	public Room(String type, int price, Hotel hotel) {
		this.type = type;
		this.price = price;
		this.hotel = hotel; // Hotel is an immutable class, no privacy leak exists.
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String toString() {
		return hotel.toString() + "\nRoom type: " + type + "\nRoom price: " + price;
	}

}
