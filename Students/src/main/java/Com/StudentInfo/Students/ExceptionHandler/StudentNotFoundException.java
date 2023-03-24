package Com.StudentInfo.Students.ExceptionHandler;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(String message) {
		super(message);
	}

}
