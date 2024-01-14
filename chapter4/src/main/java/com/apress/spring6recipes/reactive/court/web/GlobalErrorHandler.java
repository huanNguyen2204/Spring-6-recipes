package com.apress.spring6recipes.reactive.court.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    public Flux<ErrorMessage> handleValidationErrors(WebExchangeBindException ex) {
        return Flux.fromIterable(ex.getFieldError())
            .map(this::toErrorMessage);
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(
        WebExchangeBindException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        ServerWebExchange exchange
    ) {
        var locale = exchange.getLocaleContext().getLocale();
        var errors = ex.resolveErrorMessages(getMessageSource(), locale);
        ex.getBody().setProperty("errors", errors.values());
        return super.handleWebExchangeBindException(ex, null, headers, status, exchange);
    }

    private ErrorMessage toErrorMessage(FieldError fe) {
        return new ErrorMessage(fe.getField(), fe.getDefaultMessage());
    }

    record ErrorMessage(String field, String message) { }
}
