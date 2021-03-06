package com.se.ue04.controller.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.se.ue04.dto.MessageDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Class for exception handling
@RestControllerAdvice
public class ApiAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNullPointerException(NullPointerException exception) {

        MessageDTO message = new MessageDTO();
        message.setMessage("Errors found in request, try again later");
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNumberFormatException(NumberFormatException exception) {
        MessageDTO message = new MessageDTO();
        message.setMessage("Errors found in request, try again later");
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNumberFormatException(JsonParseException exception) {
        MessageDTO message = new MessageDTO();
        message.setMessage("Errors found in request, try again later");
        message.setType("ERROR");
        return message;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDTO processNumberFormatException(EmptyResultDataAccessException exception) {
        MessageDTO message = new MessageDTO();
        message.setMessage("Errors found in request, try again later");
        message.setType("ERROR");
        return message;
    }




}
