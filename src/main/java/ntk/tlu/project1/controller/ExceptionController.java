package ntk.tlu.project1.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.NoResultException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler({NoResultException.class})
	public String notFound(NoResultException e) {
		return "Exception/NoResultException";
	}
}
