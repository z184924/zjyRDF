package cn.zhangjingyao.controller;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.CodeGeneratorTable;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.service.CodeGeneratorService;
import cn.zhangjingyao.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */

@Controller
@RequestMapping(value = "/codeGenerator")
public class CodeGeneratorController extends BaseController {
    @Autowired
    private DbService dbService;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @RequestMapping(value = "/getDatabaseList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getDatabaseList() {
        List databaseList = dbService.getDatabaseList();
        return this.jsonContent("success", databaseList);
    }

    @RequestMapping(value = "/getTableList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTableList() {
        PageData pageData = this.getPageData();
        List tableList = dbService.getTableList(pageData.getString("dataBaseName"));
        return this.jsonContent("success", tableList);
    }

    @RequestMapping(value = "/getTableColumnList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTableColumnList() {
        PageData pageData = this.getPageData();
        List tableList = dbService.getTableColumnList(pageData.getString("dataBaseName"), pageData.getString("tableName"));
        return this.jsonContent("success", tableList);
    }

    @RequestMapping(value = "/generatingCode", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String generatingCode() {
        PageData pageData = this.getPageData();
        CodeGeneratorTable codeGeneratorTable = new CodeGeneratorTable();
        //设置表名、包名、类名
        codeGeneratorTable.setTableName(pageData.getString("tableName"));
        codeGeneratorTable.setPackageName(pageData.getString("packageName"));
        codeGeneratorTable.setClassName(pageData.getString("className"));
        int columnListLength = pageData.getInt("columnListLength");
        codeGeneratorTable.setColumnListLength(columnListLength);
        //设置列名、属性名、列信息
        List<Map<String, Object>> columnList = new ArrayList<>();
        for (int i = 0; i < columnListLength; i++) {
            Map<String, Object> column = new HashMap<>(16);
            String columnName = pageData.getString("columnListData[" + i + "][columnName]");
            column.put("columnName", columnName);
            String[] split = columnName.split("_");
            StringBuffer fieldNameBuffer = new StringBuffer("");
            for (String word : split) {
                fieldNameBuffer.append(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
            }
            String fieldName = fieldNameBuffer.toString();
            column.put("fieldName", fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1));
            String dataType = pageData.getString("columnListData[" + i + "][dataType]");
            column.put("dataType", dataType);
            String columnComment = pageData.getString("columnListData[" + i + "][columnComment]");
            column.put("columnComment", columnComment);
            String isNullable = pageData.getString("columnListData[" + i + "][isNullable]");
            column.put("isNullable", isNullable);
            String isEdit = pageData.getString("columnListData[" + i + "][isEdit]");
            column.put("isEdit", isEdit);
            String isPK = pageData.getString("columnListData[" + i + "][isPK]");
            column.put("isPK", isPK);
            columnList.add(column);
            if (isPK.equals("YES")) {
                codeGeneratorTable.setPrimaryKeyColumn(column);
            }
        }
        codeGeneratorTable.setColumnList(columnList);
        String createdFilePath;
        //生成及返回
        try {
            createdFilePath = codeGeneratorService.generatingCode(codeGeneratorTable);
        } catch (Exception e) {
            e.printStackTrace();
            return this.jsonContent("error", "生成失败!");
        }
        return this.jsonContent("success", "生成成功!文件位置:" + createdFilePath);
    }

}
