package Com.StudentInfo.Students.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.StudentInfo.Students.Exception.ResourceAlreadyPresent;
import com.StudentInfo.Students.Exception.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, String> HandleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((er) -> {
			String field = er.getField();
			String mesg = er.getDefaultMessage();
			map.put(field, mesg);
		});
		return map;
	}

	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> HandleNotFoundException(StudentNotFoundException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("error message", ex.getMessage());
		return map;
	}

	@ExceptionHandler(ResourceAlreadyPresent.class)
	@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
	public AppError HandleResourceAlredy(ResourceAlreadyPresent res) {
		AppError er = new AppError(LocalDateTime.now(), res.getMessage(), HttpStatus.ALREADY_REPORTED.value());
		return er;
	}

}
