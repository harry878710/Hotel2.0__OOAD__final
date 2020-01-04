package testing;

import java.util.ArrayList;

import book_Hotel_Room.*;

public class TestMaxRoomNum {

	private static ArrayList<Integer[]> possibleRoomCombination(int arg_numOfPeople) {
		int s, d, q;
		ArrayList<Integer[]> combination = new ArrayList<Integer[]>();
		for (s = 0; s < 31; s++) {
			for (d = 0; d < 31; d++) {
				for (q = 0; q < 31; q++) {
					if ((s + 2 * d + 4 * q) == arg_numOfPeople) {
						Integer[] tmp = { s, d, q };
						combination.add(tmp);
					}
				}
			}
		}
		return combination;
	}
	private static ArrayList<Integer[]> possibleRoomCombination(int arg_numOfPeople,int arg_numOfRoom){
		int s,d,q;
		ArrayList<Integer[]> combination = new ArrayList<Integer[]>();
		for(s = 0;s<31;s++) {
			for (d = 0;d<31;d++) {
				for(q = 0;q<31;q++) {
					if((s+2*d+4*q)==arg_numOfPeople && (s+d+q)==arg_numOfRoom) {
						Integer[] tmp = {s,d,q};
						combination.add(tmp);
					}
				}
			}
		}
		return combination;
	}
	public static void main(String[] args) {
		ArrayList<Integer[]> tmp = possibleRoomCombination(15,7);
		for (int i = 0; i < tmp.size(); i++) {
			for(int j = 0;j<3;j++) {
				System.out.print(tmp.get(i)[j]+",");
			}
			System.out.println(' ');
		}
	}

}
