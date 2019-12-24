package bookAndUser;

public class User {
	private String name;
	private String password;
	private boolean loggin = false;
	
	public User(String n, String password) {
		this.name = n;
		this.password = password;
	}

	public boolean isLoggin() {
		return loggin;
	}

	public void setLoggin(boolean loggin) {
		this.loggin = loggin;
	}

	public String getName() {
		return this.name;
	}
}
