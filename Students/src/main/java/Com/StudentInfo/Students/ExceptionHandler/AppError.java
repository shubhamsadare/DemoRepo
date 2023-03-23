package Com.StudentInfo.Students.ExceptionHandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppError {

	private LocalDateTime time;
	private String message;
	private int StatusCode;
	
}
