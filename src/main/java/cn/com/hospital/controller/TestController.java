package cn.com.hospital.controller;


import cn.com.hospital.constant.ReturnConst;
import cn.com.hospital.common.ResponseInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 区 前端控制器
 * </p>
 *
 * @author lijiaxing
 * @since 2020-07-06
 */
@Controller
@RequestMapping("/test")
@Api(tags = "test接口")
public class TestController {

    @ApiOperation(value = "查询数据", notes = "传入主键")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public @ResponseBody
    ResponseInfo<Object> getRecord(String id){
        ResponseInfo<Object> result=new ResponseInfo<Object>();
        try{
            result.setData(id);
            result.setCode(ReturnConst.CODE_OK);
            result.setMessage(ReturnConst.MSG_QUERY_OK);
        }catch (Exception e){
            result.setCode(ReturnConst.CODE_INTERNAL_SERVER_ERROR);
            result.setMessage(ReturnConst.MSG_QUERY_ERROR);
        }
        return result;
    }

}

