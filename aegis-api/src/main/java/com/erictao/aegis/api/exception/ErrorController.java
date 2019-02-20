package com.erictao.aegis.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author TJX
 * @Title: ErrorController
 * @date 2018/7/2819:26
 */
@Slf4j
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(AegisApiException.class)
    @ResponseBody
    public ResponseEntity<SimpleResponse>  processUnauthenticatedException(NativeWebRequest request, Exception e) {
        log.error("请求出现异常:", e);
        SimpleResponse response = new SimpleResponse();
        response.setMessage(e.getMessage());
        ResponseEntity<SimpleResponse> responseEntity = null;
        if (e instanceof AegisApiException) {
            responseEntity = new ResponseEntity<>(response, ((AegisApiException)e).getHttpStatus());
        } else {
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity ;
    }
}
