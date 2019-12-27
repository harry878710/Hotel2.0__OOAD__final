package bookAndUser;

import java.util.ArrayList;

public class UserList {

	public static ArrayList<User> userList;

	static {
		userList = UserOperation.uploadUserList();
	}

}
