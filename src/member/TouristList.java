package member;

import java.util.ArrayList;

public class TouristList {

	public static ArrayList<Tourist> userList;

	static {
		userList = TouristOperation.uploadUserList();
	}

}
