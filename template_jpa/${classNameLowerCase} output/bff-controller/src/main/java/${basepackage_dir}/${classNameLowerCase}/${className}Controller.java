<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
<#assign createTmFlag=false>
<#assign createPersonFlag=false>
<#assign updateTmFlag=false>
<#assign updatePersonFlag=false>
<#assign delFlag=false>
<#list table.columns as column>
    <#if column.columnNameLower == "createTime">
        <#assign createTmFlag=true>
    <#elseif column.columnNameLower == "createPerson">
        <#assign createPersonFlag=true>
    <#elseif column.columnNameLower == "updateTime">
        <#assign updateTmFlag=true>
    <#elseif column.columnNameLower == "updatePerson">
        <#assign updatePersonFlag=true>
    <#elseif column.columnNameLower == "delFlag">
        <#assign delFlag=true>
    </#if>
</#list>
package ${basepackage}.${serviceModule}.${classNameLowerCase};

import com.alibaba.fastjson.JSON;
import ${basepackage}.common.base.BaseResponse;
import ${basepackage}.common.util.CommonErrorCode;
import ${basepackage}.common.util.excel.Column;
import ${basepackage}.common.util.excel.ExcelHelper;
import ${basepackage}.common.util.excel.impl.SpelColumnRender;
import ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase}.${className}QueryProvider;
import ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase}.${className}SaveProvider;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.*;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
<#if delFlag>
import ${basepackage}.common.enums.DeleteFlag;
</#if>
import ${basepackage}.common.exception.SbcRuntimeException;
import java.io.OutputStream;
import java.net.URLEncoder;
<#if createTmFlag || updateTmFlag>
import java.time.LocalDateTime;
</#if>
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
<#if createPersonFlag || updatePersonFlag>
import ${basepackage}.util.CommonUtil;
</#if>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(description = "${tableDesc}管理API", tags = "${className}Controller")
@RestController
@RequestMapping(value = "/${classNameLowerCase}")
public class ${className}Controller {

    @Autowired
    private ${className}QueryProvider ${classNameFirstLower}QueryProvider;

    @Autowired
    private ${className}SaveProvider ${classNameFirstLower}SaveProvider;
<#if createPersonFlag || updatePersonFlag>

    @Autowired
    private CommonUtil commonUtil;
</#if>

    @ApiOperation(value = "分页查询${tableDesc}")
    @PostMapping("/page")
    public BaseResponse<${className}PageResponse> getPage(@RequestBody @Valid ${className}PageRequest pageReq) {
<#if delFlag>
        pageReq.setDelFlag(DeleteFlag.NO);
</#if>
        pageReq.putSort("${table.idColumn.columnNameLower}", "desc");
        return ${classNameFirstLower}QueryProvider.page(pageReq);
    }

    @ApiOperation(value = "列表查询${tableDesc}")
    @PostMapping("/list")
    public BaseResponse<${className}ListResponse> getList(@RequestBody @Valid ${className}ListRequest listReq) {
<#if delFlag>
        listReq.setDelFlag(DeleteFlag.NO);
</#if>
        listReq.putSort("${table.idColumn.columnNameLower}", "desc");
        return ${classNameFirstLower}QueryProvider.list(listReq);
    }

    @ApiOperation(value = "根据id查询${tableDesc}")
    @GetMapping("/{${table.idColumn.columnNameLower}}")
    public BaseResponse<${className}ByIdResponse> getById(@PathVariable ${pkJavaType} ${table.idColumn.columnNameLower}) {
        if (${table.idColumn.columnNameLower} == null) {
            throw new SbcRuntimeException(CommonErrorCode.PARAMETER_ERROR);
        }
        ${className}ByIdRequest idReq = new ${className}ByIdRequest();
        idReq.set${table.idColumn.columnName}(${table.idColumn.columnNameLower});
        return ${classNameFirstLower}QueryProvider.getById(idReq);
    }

    @ApiOperation(value = "新增${tableDesc}")
    @PostMapping("/add")
    public BaseResponse<${className}AddResponse> add(@RequestBody @Valid ${className}AddRequest addReq) {
<#if delFlag>
        addReq.setDelFlag(DeleteFlag.NO);
</#if>
<#if createPersonFlag>
        addReq.setCreatePerson(commonUtil.getOperatorId());
</#if>
<#if createTmFlag>
        addReq.setCreateTime(LocalDateTime.now());
</#if>
        return ${classNameFirstLower}SaveProvider.add(addReq);
    }

    @ApiOperation(value = "修改${tableDesc}")
    @PutMapping("/modify")
    public BaseResponse<${className}ModifyResponse> modify(@RequestBody @Valid ${className}ModifyRequest modifyReq) {
<#if updatePersonFlag>
        modifyReq.setUpdatePerson(commonUtil.getOperatorId());
</#if>
<#if updateTmFlag>
        modifyReq.setUpdateTime(LocalDateTime.now());
</#if>
        return ${classNameFirstLower}SaveProvider.modify(modifyReq);
    }

    @ApiOperation(value = "根据id删除${tableDesc}")
    @DeleteMapping("/{${table.idColumn.columnNameLower}}")
    public BaseResponse deleteById(@PathVariable ${pkJavaType} ${table.idColumn.columnNameLower}) {
        if (${table.idColumn.columnNameLower} == null) {
            throw new SbcRuntimeException(CommonErrorCode.PARAMETER_ERROR);
        }
        ${className}DelByIdRequest delByIdReq = new ${className}DelByIdRequest();
        delByIdReq.set${table.idColumn.columnName}(${table.idColumn.columnNameLower});
        return ${classNameFirstLower}SaveProvider.deleteById(delByIdReq);
    }

    @ApiOperation(value = "根据idList批量删除${tableDesc}")
    @DeleteMapping("/delete-by-id-list")
    public BaseResponse deleteByIdList(@RequestBody @Valid ${className}DelByIdListRequest delByIdListReq) {
        return ${classNameFirstLower}SaveProvider.deleteByIdList(delByIdListReq);
    }

    @ApiOperation(value = "导出${tableDesc}列表")
    @GetMapping("/export/{encrypted}")
    public void exportData(@PathVariable String encrypted, HttpServletResponse response) {
        String decrypted = new String(Base64.getUrlDecoder().decode(encrypted.getBytes()));
        ${className}ListRequest listReq = JSON.parseObject(decrypted, ${className}ListRequest.class);
<#if delFlag>
        listReq.setDelFlag(DeleteFlag.NO);
</#if>
        listReq.putSort("${table.idColumn.columnNameLower}", "desc");
        List<${className}VO> dataRecords = ${classNameFirstLower}QueryProvider.list(listReq).getContext().get${className}VOList();

        try {
            String nowStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            String fileName = URLEncoder.encode(String.format("${tableDesc}列表_%s.xls", nowStr), "UTF-8");
            response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\";filename*=\"utf-8''%s\"", fileName, fileName));
            exportDataList(dataRecords, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new SbcRuntimeException(CommonErrorCode.FAILED);
        }
    }

<#assign columnSize=0>
<#list table.notPkColumns as column>
    <#if column.columnNameLower != "createTime" && column.columnNameLower != "createPerson" && column.columnNameLower != "updateTime" && column.columnNameLower != "updatePerson" && column.columnNameLower != "delFlag">
        <#assign columnSize=columnSize + 1>
    </#if>
</#list>
    /**
     * 导出列表数据具体实现
     */
    private void exportDataList(List<${className}VO> dataRecords, OutputStream outputStream) {
        ExcelHelper excelHelper = new ExcelHelper();
        Column[] columns = {
<#assign columnIndex=0>
<#list table.notPkColumns as column>
    <#if column.columnNameLower != "createTime" && column.columnNameLower != "createPerson" && column.columnNameLower != "updateTime" && column.columnNameLower != "updatePerson" && column.columnNameLower != "delFlag">
        <#assign columnIndex=columnIndex+1>
            new Column("${column.columnAlias}", new SpelColumnRender<${className}VO>("${column.columnNameLower}"))<#if columnIndex<columnSize>,</#if>
    </#if>
</#list>
        };
        excelHelper.addSheet("${tableDesc}列表", columns, dataRecords);
        excelHelper.write(outputStream);
    }

}
