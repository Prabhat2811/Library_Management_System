package jsp.springboot.exception;

public class NoRecordAvailableException extends RuntimeException{
	public NoRecordAvailableException(String msg) {
		super(msg);
	}

}
