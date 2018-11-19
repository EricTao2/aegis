package com.erictao.aegis.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author TJX
 * @Title: AegisApiException
 * @date 2018/7/2717:47
 */
@Data
public class AegisApiException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public AegisApiException(String message) {
        super(message);
    }

    public AegisApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
