package com.example.lytx.lytx.vm;

import java.io.Serializable;

public class ResultWithSuccess<T> implements Serializable {
    private T result = null;
    private boolean success = false;
    private String reason;

    public ResultWithSuccess(boolean success) {
        this.success = success;
    }

    public ResultWithSuccess(T result, boolean success, String reason) {
        this.result = result;
        this.success = success;
        this.reason = reason;
    }

    public ResultWithSuccess() {
    }

    public T getResult() {
        return result;
    }

    public void setResult(T value) {
        this.result = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResultWithSuccess{" +
                "result=" + result +
                ", success=" + success +
                ", reason='" + reason + '\'' +
                '}';
    }
}

