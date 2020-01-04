package member;

public class Landlord implements Member {
	private String name;
	private String password;
	private boolean login = false;

	public Landlord(String n, String password) {
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
