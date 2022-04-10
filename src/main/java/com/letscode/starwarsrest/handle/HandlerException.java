package com.letscode.starwarsrest.handle;

import com.letscode.starwarsrest.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {


    @ExceptionHandler({InvalidIdException.class})
    public ResponseEntity<Error> handlerInvalidId(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("O Id fornecido é inválido"));
    }

    @ExceptionHandler({SameRebeldeException.class})
    public ResponseEntity<Error> handlerSameRebeldeId(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("O rebelde ja fez esse registro anteriormente!"));
    }

    @ExceptionHandler({AutoReportException.class})
    public ResponseEntity<Error> handlerAutoReport(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Não é permitido o rebelde se auto reportar!"));
    }

    @ExceptionHandler({TotalPointsNotEqual.class})
    public ResponseEntity<Error> handlerTotalPointsNotEqual(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Transação não efetivada pois o total de pontos não é o mesmo!"));
    }

    @ExceptionHandler({TraidorException.class})
    public ResponseEntity<Error> handlerTradeNullPointer(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Transação não realizada! Um dos rebeldes é um traidor"));
    }

    @ExceptionHandler({ObjectNull.class})
    public ResponseEntity<Error> handlerNullObject(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Esse objeto não existe na base de dados"));
    }

    @ExceptionHandler({ForbiddenAccess.class})
    public ResponseEntity<Error> handlerForbiddenAccess(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Esse usuário não tem acesso."));
    }



}
