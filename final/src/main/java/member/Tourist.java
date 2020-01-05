package member;

import java.util.ArrayList;

public class Tourist implements Member{
	
	public static ArrayList<Tourist> userList;

	static {
		userList = TouristOperation.uploadUserList();
	}
	
	private String name;
	private String password;
	private boolean login = false;
	
	public Tourist(String n, String password) {
		this.name = n;
		this.password = password;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean loggin) {
		this.login = loggin;
	}

	public String getName() {
		return this.name;
	}
}
