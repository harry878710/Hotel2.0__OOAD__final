package member;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




interface MemberOperation {
	
	public static String url = "jdbc:postgresql://140.112.151.227/";
	public static String user = "postgres";
	public static String passwords = "harry8787";
	
	public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, passwords);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
	
	public static int addUser(String id, String password, String checkPass, String member) {
		
		String tmp = url + member;
		
		if (!password.equals(checkPass)) {
			return 1;
		}
		if (id.equals("") || password.equals("") || checkPass.equals("")) {
			return 3;
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO " + member + " (ID,PASSWORD) " + "VALUES ('" + id + "','" + password + "');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();

		} catch (SQLException e) {
			return 2;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		return 0;
	}

	public static int userLogin(String userId, String password, String member) {
		
//		role = member;
		
		switch (member) {
		case "user":
			if (!userId.equals("") && !password.equals("")) {
				if (TouristOperation.hasUser(userId)) {
					if (TouristOperation.checkPassword(userId, password)) {
						for (int i = 0; i < Tourist.userList.size(); i++) {
							if (userId.equals(Tourist.userList.get(i).getName())) {
								// find specified user in userList,change his login status.
								Tourist.userList.get(i).setLogin(true);
								return 0;
							}
						}
					} else {
						// wrong password.
						return 1;
					}
				} else {
					// no such specified user in userList.
					return 2;
				}
			} else {
				// try to login without fill all the blanks
				return 3;
			}
			break;
		case "landlord":
			if (!userId.equals("") && !password.equals("")) {
				if (LandlordOperation.hasUser(userId)) {
					if (LandlordOperation.checkPassword(userId, password)) {
						for (int i = 0; i < Landlord.landlordList.size(); i++) {
							if (userId.equals(Landlord.landlordList.get(i).getName())) {
								// find specified user in userList,change his login status.
								Landlord.landlordList.get(i).setLogin(true);
								return 0;
							}
						}
					} else {
						// wrong password.
						return 1;
					}
				} else {
					// no such specified user in userList.
					return 2;
				}
			} else {
				// try to login without fill all the blanks
				return 3;
			}
			break;
		}
		return 4;

	}

	public static void showAllUser(String member) {
		
		String tmp = url + member;
		
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + member + ";");
			while (rs.next()) {
				String id = rs.getString("ID");
				String password = rs.getString("PASSWORD");

				System.out.println("ID = " + id);
				System.out.println("PASSWORD = " + password);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");

	}

	// To change a user's password.
	// If the user Id is not exist, it would print a msg.
	// If the old password is wrong, it would print a msg, too.
	public static int changePassword(String id, String oldPass, String newPass, String repeat, String member) {
		
		String tmp = url + member;
		
		if (!hasUser(id, member)) {
			System.out.println("The user id is not exist.");
			return -1;
		}
		if (id.equals("") || oldPass.equals("") || newPass.equals("") || repeat.equals("")) {
			return 3;
		}
		if (!newPass.equals(repeat)) {
			return 2;
		}
		if (newPass.equals(oldPass)) {
			return 4;
		}
		int toReturn = -1;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + member + ";");
			while (rs.next()) {
				String tmpId = rs.getString("ID");
				String tmpPass = rs.getString("PASSWORD");
				if (tmpId.equals(id)) {
					if (tmpPass.equals(oldPass)) {
						String sql = "UPDATE " + member + " set PASSWORD = '" + newPass + "' where ID='" + id + "';";
						stmt.executeUpdate(sql);
						c.commit();
						System.out.println("Changed password successfully.");
						toReturn = 0;
					} else {
						System.out.println("Your old password is wrong.");
						toReturn = 1;
					}
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return toReturn;
		// System.out.println("Operation done successfully");
	}

	// Check if the password is right.
	// If the id is not exist or the password is wrong, it would return false.
	// Only if the password is right would it return true.
	public static boolean checkPassword(String id, String password, String member) {
		
		String tmp = url + member;
		
		if (!hasUser(id, member)) {
			return false;
		}
		boolean toReturn = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + member + ";");
			while (rs.next()) {
				String tmpId = rs.getString("ID");
				String tmpPass = rs.getString("PASSWORD");
				if (tmpId.equals(id)) {
					if (tmpPass.equals(password)) {
						toReturn = true;
					} else {
						// System.out.println("Your password is wrong.");
						toReturn = false;
					}
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");
		return toReturn;
	}

	public static boolean hasUser(String id, String member) {
		
		String tmp = url + member;
		
		boolean get = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + member + ";");
			while (rs.next()) {
				if (rs.getString("ID").equals(id)) {
					get = true;
					break;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return get;
		// System.out.println("Operation done successfully");
	}

	public static boolean anyoneLoggedin(String member) {
		
		switch (member) {
		case "user":
			for (int i = 0; i < Tourist.userList.size(); i++) {
				if (Tourist.userList.get(i).isLogin()) {
					return true;
				}
			}
			break;
		case "landlord":
			for (int i = 0; i < Landlord.landlordList.size(); i++) {
				if (Landlord.landlordList.get(i).isLogin()) {
					return true;
				}
			}
			break;
		}
		return false;

	}

	public static void everyOneloggedOut(String member) {
		
		switch (member) {
		case "user":
			for (int i = 0; i < Tourist.userList.size(); i++) {
				Tourist.userList.get(i).setLogin(false);
			}
			break;
		case "landlord":
			for (int i = 0; i < Landlord.landlordList.size(); i++) {
				Landlord.landlordList.get(i).setLogin(false);
			}
			break;
		}

	}

	public static String whoIsLoggedin(String member) {
		
		
		
		switch (member) {
		case "user":
			for (int i = 0; i < Tourist.userList.size(); i++) {
				if (Tourist.userList.get(i).isLogin()) {
					return Tourist.userList.get(i).getName();
				}
			}
			break;
		case "landlord":
			for (int i = 0; i < Landlord.landlordList.size(); i++) {
				if (Landlord.landlordList.get(i).isLogin()) {
					return Landlord.landlordList.get(i).getName();
				}
			}
			break;
		}
		return null;

	}

	public static ArrayList uploadUserList(String member) {
		
		String tmp = url + member;
		
		ArrayList list = new ArrayList();
		switch (member) {
		case "user":
			list = new ArrayList<Tourist>();
			break;
		case "landlord":
			list = new ArrayList<Landlord>();
		}
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(tmp, user, passwords);
			c.setAutoCommit(false);
			// System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + member + ";");
			while (rs.next()) {
				String name = rs.getString("ID");
				String password = rs.getString("PASSWORD");
				switch (member) {
				case "user":
					Tourist user = new Tourist(name, password);
					list.add(user);
					break;
				case "landlord":
					Landlord landlord = new Landlord(name, password);
					list.add(landlord);
					break;
				}

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return list;
	}

}
