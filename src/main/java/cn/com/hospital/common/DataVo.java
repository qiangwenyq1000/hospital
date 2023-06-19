package cn.com.hospital.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataVo<T> implements Serializable {
    private List<T> list;
    private long total;
}
