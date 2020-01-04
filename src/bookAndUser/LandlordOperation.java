package bookAndUser;

import java.util.ArrayList;

import hotelAndRoom.HotelList;

public class LandlordOperation implements MemberOperation {
	public static void main(String[] args) {

	}

	// Add an user with corresponding id and password.
	// If the id has been used, it would cause an exception.
	// We can turn it to a pop-up window later.
	public static int addUser(String id, String password, String checkPass) {
		return MemberOperation.addUser(id, password, checkPass, "landlord");

	}

	public static int userLogin(String userId, String password) {
		return MemberOperation.userLogin(userId, password, "landlord");
	}

	// To show you all the user.
	// Actually, you can use DB Browser to see all the user, too.
	public static void showAllUser() {
		MemberOperation.showAllUser("landlord");

	}

	// To change a user's password.
	// If the user Id is not exist, it would print a msg.
	// If the old password is wrong, it would print a msg, too.
	public static int changePassword(String id, String oldPass, String newPass, String repeat) {
		return MemberOperation.changePassword(id, oldPass, newPass, repeat, "landlord");
	}

	// Check if the password is right.
	// If the id is not exist or the password is wrong, it would return false.
	// Only if the password is right would it return true.
	public static boolean checkPassword(String id, String password) {
		return MemberOperation.checkPassword(id, password, "landlord");
	}

	public static boolean hasUser(String id) {
		return MemberOperation.hasUser(id, "landlord");
	}

	public static boolean anyoneLoggedin() {
		return MemberOperation.anyoneLoggedin("landlord");
	}

	public static void everyOneloggedOut() {
		MemberOperation.everyOneloggedOut("landlord");
	}

	public static String whoIsLoggedin() {
		return MemberOperation.whoIsLoggedin("landlord");
	}

	public static ArrayList<Landlord> uploadUserList() {
		ArrayList<Landlord> toReturn = MemberOperation.uploadUserList("landlord");
		return toReturn;
	}

	public static int[] listMyHotelId(String landlordName) {
		ArrayList<Integer> myHotelId = new ArrayList<Integer>();
		for (int i = 0; i < HotelList.TOTAL_NUMBER_OF_HOTEL; i++) {
			if (HotelList.ALLHOTEL[i].getLandlord().equals(landlordName)) {
				myHotelId.add(HotelList.ALLHOTEL[i].getId());
			}
		}
		int[] toReturn = new int[myHotelId.size()];
		for (int i = 0; i < myHotelId.size(); i++) {
			toReturn[i] = myHotelId.get(i);
		}
		return toReturn;
	}

	public static String showThisHotelOrder(int hotelId) {
		ArrayList<String> bookIdList = BookOperation.listBookIdOfHotel(hotelId);
		StringBuffer tmp = new StringBuffer("");
		for (int i = 0; i < bookIdList.size(); i++) {
			tmp.append(BookOperation.showBook(bookIdList.get(i)));
		}
		String toReturn = new String(tmp);
		return toReturn;
	}
	
	public static String showAllMyHotel(String landlord) {
		
		
		return null;
	}

}
