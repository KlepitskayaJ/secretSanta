package com.innowise.secretSanta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailDuplicationException extends RuntimeException
{
    public EmailDuplicationException(String message) { super(message); }
}