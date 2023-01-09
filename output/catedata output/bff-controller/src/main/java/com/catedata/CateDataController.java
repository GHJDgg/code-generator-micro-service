package com.data.catedata;

import com.alibaba.fastjson.JSON;
import com.common.base.BaseResponse;
import com.common.util.CommonErrorCode;
import com.common.util.excel.Column;
import com.common.util.excel.ExcelHelper;
import com.common.util.excel.impl.SpelColumnRender;
import com.data.api.provider.catedata.CateDataQueryProvider;
import com.data.api.provider.catedata.CateDataSaveProvider;
import com.data.api.request.catedata.*;
import com.data.api.response.catedata.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.common.exception.SbcRuntimeException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import com.data.bean.vo.CateDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(description = "系统日志管理API", tags = "CateDataController")
@RestController
@RequestMapping(value = "/catedata")
public class CateDataController {

    @Autowired
    private CateDataQueryProvider cateDataQueryProvider;

    @Autowired
    private CateDataSaveProvider cateDataSaveProvider;

    @ApiOperation(value = "分页查询系统日志")
    @PostMapping("/page")
    public BaseResponse<CateDataPageResponse> getPage(@RequestBody @Valid CateDataPageRequest pageReq) {
        pageReq.putSort("id", "desc");
        return cateDataQueryProvider.page(pageReq);
    }

    @ApiOperation(value = "列表查询系统日志")
    @PostMapping("/list")
    public BaseResponse<CateDataListResponse> getList(@RequestBody @Valid CateDataListRequest listReq) {
        listReq.putSort("id", "desc");
        return cateDataQueryProvider.list(listReq);
    }

    @ApiOperation(value = "根据id查询系统日志")
    @GetMapping("/{id}")
    public BaseResponse<CateDataByIdResponse> getById(@PathVariable Long id) {
        if (id == null) {
            throw new SbcRuntimeException(CommonErrorCode.PARAMETER_ERROR);
        }
        CateDataByIdRequest idReq = new CateDataByIdRequest();
        idReq.setId(id);
        return cateDataQueryProvider.getById(idReq);
    }

    @ApiOperation(value = "新增系统日志")
    @PostMapping("/add")
    public BaseResponse<CateDataAddResponse> add(@RequestBody @Valid CateDataAddRequest addReq) {
        addReq.setCreateTime(LocalDateTime.now());
        return cateDataSaveProvider.add(addReq);
    }

    @ApiOperation(value = "修改系统日志")
    @PutMapping("/modify")
    public BaseResponse<CateDataModifyResponse> modify(@RequestBody @Valid CateDataModifyRequest modifyReq) {
        modifyReq.setUpdateTime(LocalDateTime.now());
        return cateDataSaveProvider.modify(modifyReq);
    }

    @ApiOperation(value = "根据id删除系统日志")
    @DeleteMapping("/{id}")
    public BaseResponse deleteById(@PathVariable Long id) {
        if (id == null) {
            throw new SbcRuntimeException(CommonErrorCode.PARAMETER_ERROR);
        }
        CateDataDelByIdRequest delByIdReq = new CateDataDelByIdRequest();
        delByIdReq.setId(id);
        return cateDataSaveProvider.deleteById(delByIdReq);
    }

    @ApiOperation(value = "根据idList批量删除系统日志")
    @DeleteMapping("/delete-by-id-list")
    public BaseResponse deleteByIdList(@RequestBody @Valid CateDataDelByIdListRequest delByIdListReq) {
        return cateDataSaveProvider.deleteByIdList(delByIdListReq);
    }

    @ApiOperation(value = "导出系统日志列表")
    @GetMapping("/export/{encrypted}")
    public void exportData(@PathVariable String encrypted, HttpServletResponse response) {
        String decrypted = new String(Base64.getUrlDecoder().decode(encrypted.getBytes()));
        CateDataListRequest listReq = JSON.parseObject(decrypted, CateDataListRequest.class);
        listReq.putSort("id", "desc");
        List<CateDataVO> dataRecords = cateDataQueryProvider.list(listReq).getContext().getCateDataVOList();

        try {
            String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            String fileName = URLEncoder.encode(String.format("系统日志列表_%s.xls", nowStr), "UTF-8");
            response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\";filename*=\"utf-8''%s\"", fileName, fileName));
            exportDataList(dataRecords, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new SbcRuntimeException(CommonErrorCode.FAILED);
        }
    }

    /**
     * 导出列表数据具体实现
     */
    private void exportDataList(List<CateDataVO> dataRecords, OutputStream outputStream) {
        ExcelHelper excelHelper = new ExcelHelper();
        Column[] columns = {
            new Column("一级分类id", new SpelColumnRender<CateDataVO>("cateFirstParentId")),
            new Column("一级分类名称", new SpelColumnRender<CateDataVO>("cateFirstParentName")),
            new Column("二级分类id", new SpelColumnRender<CateDataVO>("cateSecondParentId")),
            new Column("二级分类名称", new SpelColumnRender<CateDataVO>("cateSecondParentName")),
            new Column("分类id", new SpelColumnRender<CateDataVO>("cateId")),
            new Column("分类名称", new SpelColumnRender<CateDataVO>("cateName")),
            new Column("销售额", new SpelColumnRender<CateDataVO>("totalPrice")),
            new Column("销量", new SpelColumnRender<CateDataVO>("totalCount")),
            new Column("订单量", new SpelColumnRender<CateDataVO>("orderCount")),
            new Column("购买人数", new SpelColumnRender<CateDataVO>("buyerCount")),
            new Column("日期", new SpelColumnRender<CateDataVO>("day"))
        };
        excelHelper.addSheet("系统日志列表", columns, dataRecords);
        excelHelper.write(outputStream);
    }

}
