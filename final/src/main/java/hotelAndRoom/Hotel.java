package hotelAndRoom;

public class Hotel {

	private int id;
	private int star;
	private String locality;
	private String address;

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
	
	public String toString() {
		return "Hotel ID : " + id + "\nHotel star : " + star + " star\nAddress : " + locality +" "+ address;
	}

	
}
