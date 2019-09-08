package com.idwxy.fli.common;

public class ResultObject {

    // 状态码
    private Integer code;
    // 返回信息
    private String msg;
    // 返回封装数据
    private Object result;

    // 构造函数
    public ResultObject() {
        super();
    }

    // 处理其他状态的构造函数
    public ResultObject(Integer code, String msg, Object result){
        super();
        this.code = code;
        this.msg = msg;
        this.result =result;
    }

    // 处理成功时使用的构造函数
    public ResultObject(Object result) {
        super();
        this.code = 200;
        this.msg = "success";
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    // 重写 toString 方法
    public String toString(){
        return "ResultObject [code=" +
                code + ",msg=" +
                msg + ",result=" +
                result + "]";
    }
}
