package xsscd.monitor.air.southwest.modules.core.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xsscd.monitor.air.southwest.common.utils.R;
import xsscd.monitor.air.southwest.modules.core.service.impl.ImportDateServiceImpl;

/**
 * 西南-公共信息模块
 * Created by lijie on 2018/5/02
 */
@Api(tags = "预报评估模块")
@Controller
public class ImportDateController {

    @Autowired
    private ImportDateServiceImpl service;

    @ApiOperation(value = "中长期分析数据导入", notes = "", produces = MediaType.TEXT_HTML_VALUE)
    @CrossOrigin(origins = "*")//解决跨域问题的标签
    @RequestMapping(value = { "/core/importPollutantAnalysisDayDate" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public R importPollutantAnalysisDayDate(String time) throws ParseException {
        boolean data=service.importPollutantAnalysisDayDate(time);
        return new R().put("data", data);
    }

   



}
