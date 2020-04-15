package com.ambow.Bean;

import java.util.List;

/**
 */

public class HttpResponse<T> {
    Integer code;
    String message;
    T data;
    boolean success;
    List<T> rows;

    public List<T> getRows() {
        return rows;
    }

    public void setRows( List<T> rows) {
        this.rows = rows;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
