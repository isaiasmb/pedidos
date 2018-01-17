package com.pedidos.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pedidos.api.exception.BadProfitabilityException;
import com.pedidos.api.exception.InvalidMultipleException;
import com.pedidos.api.exception.ProductNotFound;

@ControllerAdvice
public class OrdersExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String userMessage = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String devMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = createListOfErrors(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.not_found", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		List<Error> errors = createListOfViolates(ex.getConstraintViolations());
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ BadProfitabilityException.class, InvalidMultipleException.class, ProductNotFound.class })
	public ResponseEntity<Object> handleItemException(Exception ex,  WebRequest request) {
		String userMessage = ex.getMessage();
		String devMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Error> createListOfViolates(Set<ConstraintViolation<?>> violates) {
		List<Error> errors = new ArrayList<>();
		for (ConstraintViolation violate : violates) {
			String userMessage = violate.getMessage();
			String devMessage = violate.getConstraintDescriptor().toString();
			errors.add(new Error(userMessage, devMessage));
		}
		return errors;
	}

	private List<Error> createListOfErrors(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String devMessage = fieldError.toString();
			errors.add(new Error(userMessage, devMessage));
		}
		return errors;
	}

}
