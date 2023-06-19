package cn.com.hospital.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestInfo <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageSize;

    private Integer pageNum;

    private T params;

    private Boolean isPage=false;

    private Boolean isLike=false;

    private String keyWords;

    private String keyWordsColumn;

    private String sortColumn;

    private String sortRule;
}
