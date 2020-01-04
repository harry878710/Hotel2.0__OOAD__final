package hotelAndRoom;

public class Hotel {

	private int id;
	private int star;
	private String locality;
	private String address;
	private String landlord = "0";
	private int[] roomCombination = new int[3];
	private Room[] roomInfo = new Room[3];

	public Hotel(int id, int star, String locality, String address, int[] roomCombination, Room[] roomInfo) {
		this.id = id;
		this.star = star;
		this.locality = locality;
		this.address = address;
		for (int i = 0; i < 3; i++) {
			this.roomCombination[i] = roomCombination[i];
		}
		for (int i = 0; i < 3; i++) {
			this.roomInfo[i] = new Room(roomInfo[i]);
		}
	}
	
	public Hotel(int id, int star, String locality, String address, int[] roomCombination, Room[] roomInfo,String landlord) {
		this.id = id;
		this.star = star;
		this.locality = locality;
		this.address = address;
		this.landlord = landlord;
		for (int i = 0; i < 3; i++) {
			this.roomCombination[i] = roomCombination[i];
		}
		for (int i = 0; i < 3; i++) {
			this.roomInfo[i] = new Room(roomInfo[i]);
		}
	}

	public int getId() {
		return id;
	}

	public int getStar() {
		return star;
	}

	public String getLocality() {
		return locality;
	}

	public String getAddress() {
		return address;
	}
	
	public String getLandlord() {
		return landlord;
	}

	public int[] getRoomCombination() {
		int[] toReturn = new int[3];
		for (int i = 0; i < 3; i++) {
			toReturn[i] = roomCombination[i];
		}
		return toReturn;
	}

	public Room[] getRoomInfo() {
		Room[] toReturn = new Room[3];
		for (int i = 0; i < 3; i++) {
			toReturn[i] = new Room(roomInfo[i]);
		}
		return toReturn;
	}

	public String toString() {
		return "Hotel ID : " + id + "\nHotel star : " + star + " star\nAddress : " + locality + " " + address;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			return id == ((Hotel) obj).id;
		}
	}

}
