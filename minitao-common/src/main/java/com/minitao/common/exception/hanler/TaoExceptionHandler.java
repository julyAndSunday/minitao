package com.minitao.common.exception.hanler;

import com.minitao.common.exception.ExceptionMessageEnum;
import com.minitao.common.exception.TaoException;
import com.minitao.common.response.CommonResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-14 15:07
 **/
@RestControllerAdvice
public class TaoExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        CommonResult result = new CommonResult();
        result.setCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        CommonResult result = new CommonResult();
        result.setCode(500);
        //区分是否为自定义异常
        if (e instanceof TaoException) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ExceptionMessageEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ExceptionMessageEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setCode(400);
            }
        } else {
            e.printStackTrace();
            result.setMessage("未知异常，请联系管理员");
        }
        return result;
    }

}
