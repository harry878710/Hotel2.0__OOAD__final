package hotelAndRoom;

public class Hotel {

	private int id;
	private int star;
	private int[] roomCombination = new int[3];
	
	private String locality;
	private String address;
	private Room[] roomInfo = new Room[3];

	public Hotel(int id, int star, String locality, String address) {
		this.id = id;
		this.star = star;
		this.locality = locality;
		this.address = address;
	}

	public String getLocality() {
		return locality;
	}

	public int getId() {
		return id;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			return id == ((Hotel) obj).id && star == ((Hotel) obj).star && locality == ((Hotel) obj).locality
					&& address == ((Hotel) obj).address;
		}
	}

	public int[] getRoomCombination() {
		int[] toReturn = new int[3];
		for (int i = 0; i < 3; i++) {
			toReturn[i] = roomCombination[i];
		}
		return toReturn;
	}

	public String toString() {
		return "Hotel ID : " + id + "\nHotel star : " + star + " star\nAddress : " + locality + " " + address;
	}

}
