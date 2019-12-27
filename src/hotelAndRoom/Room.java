package hotelAndRoom;

public class Room {

	private String type;
	private int price;

	public Room(String type, int price) {
		this.type = type;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public String toString() {
		return ("Room type: " + type + "\nRoom price: " + price);
	}

}
