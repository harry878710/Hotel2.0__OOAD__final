package testing;

import java.util.StringTokenizer;

public class TestStringTokenizer {

	public static void main(String[] args) {
		int[] roomCombination = { 1, 0, 0 };
		StringTokenizer st = new StringTokenizer("2,5,9", ",");
		int[] tmp = new int[3];
		int i_str = 0;
		while (st.hasMoreElements()) {
			String str;
			str = st.nextToken(",");
			tmp[i_str] = Integer.parseInt(str);
			System.out.println(tmp[i_str]);
			i_str++;
		}

	}

}
