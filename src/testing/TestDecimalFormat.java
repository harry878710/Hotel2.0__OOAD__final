package testing;
import java.text.DecimalFormat;

public class TestDecimalFormat {

	public static void main(String[] args) {
		DecimalFormat decimal = new DecimalFormat("00000");
		String result = decimal.format(1);
		System.out.println(result);
		

	}

}
