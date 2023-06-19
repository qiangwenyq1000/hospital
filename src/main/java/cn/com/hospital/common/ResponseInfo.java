package cn.com.hospital.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;

    private long total;
}
