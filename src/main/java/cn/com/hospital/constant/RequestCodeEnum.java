package cn.com.hospital.constant;

public enum RequestCodeEnum {
    CODE_OK(200,"请求成功"),
    CODE_BAD_REQUEST(400,"请求无效"),
    CODE_UNAUTHORIZED(401,"未授权"),
    CODE_FORBIDDEN(403,"禁止访问"),
    CODE_NOT_FOUND(404,"未找到服务"),
    CODE_INTERNAL_SERVER_ERROR(500,"服务异常"),
    USER_NAME_ERROR(10001,"用户名错误"),
    USER_PSW_ERROR(10002,"密码错误"),
    USER_NOT_FOUND(10003,"未发现该用户信息")
    ;

    private Integer code;

    private String message;

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

    RequestCodeEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
