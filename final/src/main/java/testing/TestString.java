package testing;

import java.util.StringTokenizer;

public class TestString {
	public String s = "]";
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer(new TestString().s);
		StringBuffer sb = new StringBuffer("");
		while(st.hasMoreTokens()) {
			sb.append(" "+st.nextToken());
		}
		String str = new String(sb);
		System.out.println(str);
		
	}
}
