package com.game.miaosha.exception;

import com.game.miaosha.result.CodeMsg;
import com.game.miaosha.result.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;

import java.util.List;

/**
 * Created by ls on 2019/8/22.
 */

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if (e instanceof GlobleException){
            GlobleException globleException = (GlobleException) e;
            return Result.error(globleException.getCodeMsg());
        }
        else if (e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError objectError = errors.get(0);
            String message = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
