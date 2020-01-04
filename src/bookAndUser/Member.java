package bookAndUser;

interface Member {
	String name = "";
	String password = "";
	boolean login = false;

	public boolean isLogin();

	public void setLogin(boolean loggin);

	public String getName();

}
