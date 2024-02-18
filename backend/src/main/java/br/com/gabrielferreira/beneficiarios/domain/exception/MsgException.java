package br.com.gabrielferreira.beneficiarios.domain.exception;

import java.io.Serial;

public class MsgException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MsgException(String msg){
        super(msg);
    }
}
