package Com.StudentInfo.Students.ExceptionHandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AppError {

	private LocalDateTime time;
	private String message;
	private int StatusCode;
	private String trace;
	
	public AppError(LocalDateTime time, String message, int statusCode,String trace) {
		super();
		this.time = time;
		this.message = message;
		this.StatusCode = statusCode;
		this.trace=trace;
	}
	
	
}
