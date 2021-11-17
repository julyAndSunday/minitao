package com.minitao.common.exception;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-14 11:32
 **/
public class TaoException extends RuntimeException {

    public TaoException(String message) {
        super(message);
    }

    public static void fail(String message) {
        throw new TaoException(message);
    }
}
