package com.freekai.global;

public class FreekaiResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> FreekaiResponse ok(T data){
        FreekaiResponse res = new FreekaiResponse();
        res.setCode(200);
        res.setMessage("成功");
        res.setData(data);
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
