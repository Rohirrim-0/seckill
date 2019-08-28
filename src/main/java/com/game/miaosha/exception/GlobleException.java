package com.game.miaosha.exception;

import com.game.miaosha.result.CodeMsg;

/**
 * Created by ls on 2019/8/22.
 */
public class GlobleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobleException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
