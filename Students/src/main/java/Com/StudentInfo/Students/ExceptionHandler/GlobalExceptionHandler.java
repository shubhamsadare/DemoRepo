package Com.StudentInfo.Students.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<AppError> HandleNotFoundException(StudentNotFoundException st) {
		LocalDateTime now = LocalDateTime.now();
		String message = st.getMessage();
		int code=HttpStatus.NOT_FOUND.value();
		AppError er=new AppError(now,message,code);
		
		return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ResourceAlreadyPresent.class)
	public ResponseEntity<AppError> HandleResourceAlreadyPresentException(ResourceAlreadyPresent rp) {
		LocalDateTime now = LocalDateTime.now();
		String message = rp.getMessage();
		int code=HttpStatus.ALREADY_REPORTED.value();
		AppError er=new AppError(now,message,code);
		
		return new ResponseEntity<>(er,HttpStatus.ALREADY_REPORTED);
	}
	
}
