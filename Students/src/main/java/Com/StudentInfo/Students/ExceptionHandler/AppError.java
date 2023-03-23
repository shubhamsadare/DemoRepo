package Com.StudentInfo.Students.Exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
//@AllArgsConstructor
public class AppError {

	
	private LocalDateTime timestamp;
	private String message;
	private int StatusCode;
		
	public AppError(LocalDateTime now, String message,int StatusCode) {
		// TODO Auto-generated constructor stub
		this.timestamp=now;
		this.message=message;
		this.StatusCode=StatusCode;
		
	}
}
