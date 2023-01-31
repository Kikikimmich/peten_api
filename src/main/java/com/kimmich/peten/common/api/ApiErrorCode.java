package com.kimmich.peten.common.api;


public enum ApiErrorCode implements IErrorCode {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAILED(500, "操作失败"),
    /**
     * 未登录，Token过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 权限不足
     */
    FORBIDDEN(402, "权限不足"),
    /**
     * 参数校验错误
     */
    VALIDATE_FAILED(403, "参数检验失败");

    private final Integer code;
    private final String message;

    ApiErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
