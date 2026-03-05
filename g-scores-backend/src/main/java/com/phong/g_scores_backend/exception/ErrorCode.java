package com.phong.g_scores_backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),

    STUDENT_NOT_FOUND(1001, "Student not found", HttpStatus.NOT_FOUND),
    INVALID_REGISTRATION_NUMBER(1002, "Registration number is not valid", HttpStatus.BAD_REQUEST),

    VALIDATION_ERROR(1003, "Invalid data", HttpStatus.BAD_REQUEST),
    INVALID_GROUP_CODE(1004, "Invalid subject group code", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
