package online.fimbi.Exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { FimbiException.class })
	public ResponseEntity<Object> handleFimbiException(FimbiException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;

		ApiException apiException = new ApiException(
				e.getMessage(),
				e,
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z")));

		return new ResponseEntity<>(apiException, badRequest);

	}

}
