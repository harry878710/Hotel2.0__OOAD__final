package member;

import java.util.ArrayList;

public class LandlordList {

	public static ArrayList<Landlord> landlordList;

	static {
		landlordList = LandlordOperation.uploadUserList();
	}

}
