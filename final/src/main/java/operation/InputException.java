package operation;
//�ΦbcheckVacancy�ˬdinput�O�_���T
public class InputException extends Exception {

	private String condition;
	
	public InputException() {
		
	}
	public InputException(String str) {
		condition = str;
	}
	
	public String getMessage() {
		return condition;
	}
}
