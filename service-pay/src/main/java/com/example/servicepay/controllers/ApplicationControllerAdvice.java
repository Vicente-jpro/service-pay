package com.example.servicepay.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.exceptions.MunicipioException;
import com.example.servicepay.exceptions.RegraNegocioException;
import com.example.servicepay.exceptions.UsuarioException;
import com.example.servicepay.util.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
    
    @ExceptionHandler(MunicipioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMunicipioException(MunicipioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
    
    
    

    @ExceptionHandler(UsuarioException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUsuarioException( UsuarioException ex ){
        return new ApiErrors(ex.getMessage());
    }
    

    @ExceptionHandler(EnderecoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleEnderecoException( EnderecoException ex ){
        return new ApiErrors(ex.getMessage());
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
