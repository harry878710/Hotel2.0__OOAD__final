package bookAndUser;

public class User {
	private String name;
	private String password;
	private boolean login = false;
	
	public User(String n, String password) {
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
