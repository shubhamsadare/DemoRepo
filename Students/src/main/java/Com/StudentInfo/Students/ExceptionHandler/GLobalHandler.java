package Com.StudentInfo.Students.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GLobalHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> HandleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<AppError> HandleNotFoundException(StudentNotFoundException ex) {
		 String message = ex.getMessage();
		 String trace="Trace message";
		AppError er=new AppError(LocalDateTime.now(),message,HttpStatus.NOT_FOUND.value(),trace );
		return  new ResponseEntity<>(er, HttpStatus.NOT_FOUND) ;
	}

}
