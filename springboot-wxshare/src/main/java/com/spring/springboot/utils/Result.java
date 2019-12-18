
package com.spring.springboot.utils;



public class Result<T> {
    private T data;
    private Long total = Long.valueOf(1L);

    private boolean success = true;
    private String msg = "操作完成";

    public Result() {
    }

    public static Result genSuccess(String msg) {
        return (new Result()).setSuccess(true).setMsg(msg);
    }

    public static Result genSuccess(Object data, Long total) {
        return (new Result()).setSuccess(true).setData(data).setTotal(total);
    }

    public static Result genFail(String msg) {
        return (new Result()).setSuccess(false).setMsg(msg).setTotal(Long.valueOf(0L));
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Long getTotal() {
        return this.total;
    }

    public Result setTotal(Long total) {
        this.total = total;
        return this;
    }
}
