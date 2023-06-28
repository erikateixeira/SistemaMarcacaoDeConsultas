package com.saper.sistemadeconsultas.exception;

import com.saper.sistemadeconsultas.dto.ErrorDTO;
import com.saper.sistemadeconsultas.exception.exceptions.ConflictStoreException;
import com.saper.sistemadeconsultas.exception.exceptions.FileProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public List<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                HttpServletRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        exception
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    errors.add(new ErrorDTO(
                            Instant.now(),
                            HttpStatus.BAD_REQUEST.toString(),
                            ((FieldError) error).getField(),
                            error.getDefaultMessage(),
                            request.getRequestURI()));
                });
        return errors;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorDTO handleNoSuchElementException(NoSuchElementException exception,
                                                 HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimeStamp(Instant.now());
        errorDTO.setStatus(HttpStatus.NOT_FOUND.toString());
        errorDTO.setError("Recurso não encontrado.");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return errorDTO;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDTO handleDataIntegrityViolationException(DataIntegrityViolationException exception,
                                                          HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimeStamp(Instant.now());
        errorDTO.setStatus(HttpStatus.CONFLICT.toString());
        errorDTO.setError("Conflito no banco de dados.");
        errorDTO.setMessage("A operação não pode ser executada.");
        errorDTO.setPath(request.getRequestURI());

        return errorDTO;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictStoreException.class)
    public ErrorDTO handleConflictStoreException(ConflictStoreException exception,
                                                 HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimeStamp(Instant.now());
        errorDTO.setStatus(HttpStatus.CONFLICT.toString());
        errorDTO.setError("Conflito no banco de dados.");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return errorDTO;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileProcessingException.class)
    public ErrorDTO handleFileProcessingException(FileProcessingException exception,
                                                  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimeStamp(Instant.now());
        errorDTO.setStatus(HttpStatus.NOT_FOUND.toString());
        errorDTO.setError("Erro ao processar o arquivo PDF.");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return errorDTO;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorDTO handleException(Exception exception,
                                    HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimeStamp(Instant.now());
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorDTO.setError("Erro desconhecido.");
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setPath(request.getRequestURI());

        return errorDTO;
    }



}
