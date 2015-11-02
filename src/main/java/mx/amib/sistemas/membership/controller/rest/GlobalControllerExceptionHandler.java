package mx.amib.sistemas.membership.controller.rest;

import org.springframework.web.bind.annotation.*;

import mx.amib.sistemas.membership.controller.rest.wrapper.ExceptionResponseWrapper;

import org.springframework.http.*;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ExceptionResponseWrapper handleException(Exception ex) {
		ExceptionResponseWrapper erw = new ExceptionResponseWrapper();
		erw.setName(ex.getClass().getSimpleName());
		erw.setDetails(ex.getMessage());
        return erw;
    }
	
}
