package cn.zhangjingyao.zjyrdf.codegenerator.service;


import cn.zhangjingyao.zjyrdf.codegenerator.entity.CodeGeneratorTable;
import cn.zhangjingyao.zjyrdf.codegenerator.util.DelAllFile;
import cn.zhangjingyao.zjyrdf.codegenerator.util.FileZip;
import cn.zhangjingyao.zjyrdf.codegenerator.util.Freemarker;
import cn.zhangjingyao.zjyrdf.codegenerator.util.PathUtil;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class CodeGeneratorService {
    private static final String FILE_PATH = "static/file/codeGenerator/";
    private static final String FTL_PATH = "codeGenerator";

    public String generatingCode(CodeGeneratorTable codeGeneratorTable) throws Exception {
        //生成代码前,先清空之前生成的代码
        DelAllFile.delFolder(PathUtil.getClassResources()+FILE_PATH);
        //生成controller
        Freemarker.printFile("controllerTemplate.ftl", codeGeneratorTable.getMap(), "controller/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName()+"Controller.java", FILE_PATH, FTL_PATH);
        //生成service
        Freemarker.printFile("serviceTemplate.ftl", codeGeneratorTable.getMap(), "service/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName()+"Service.java", FILE_PATH, FTL_PATH);
        //生成mybatis xml
        Freemarker.printFile("mapperMysqlTemplate.ftl", codeGeneratorTable.getMap(), "mybatis_mysql/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName()+"Mapper.xml", FILE_PATH, FTL_PATH);
        Freemarker.printFile("mapperOracleTemplate.ftl", codeGeneratorTable.getMap(), "mybatis_oracle/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName()+"Mapper.xml", FILE_PATH, FTL_PATH);
        //生成SQL脚本
        Freemarker.printFile("mysql_SQL_Template.ftl", codeGeneratorTable.getMap(), "mysql_script/"+codeGeneratorTable.getTableName()+".sql", FILE_PATH, FTL_PATH);
        Freemarker.printFile("oracle_SQL_Template.ftl", codeGeneratorTable.getMap(), "oracle_script/"+codeGeneratorTable.getTableName()+".sql", FILE_PATH, FTL_PATH);
        //生成vue页面
        Freemarker.printFile("vue_list_Template.ftl", codeGeneratorTable.getMap(), "vue/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName().toLowerCase()+"/"+codeGeneratorTable.getClassName()+"List.vue", FILE_PATH, FTL_PATH);
        Freemarker.printFile("vue_form_Template.ftl", codeGeneratorTable.getMap(), "vue/"+codeGeneratorTable.getPackageName()+"/"+codeGeneratorTable.getClassName().toLowerCase()+"/"+codeGeneratorTable.getClassName()+"Form.vue", FILE_PATH, FTL_PATH);
        //生成的全部代码压缩成zip文件
        FileZip.zip(PathUtil.getClassResources()+FILE_PATH, PathUtil.getClassResources()+FILE_PATH+"code.zip");
        String createdFilePath = PathUtil.getClassResources()+FILE_PATH+"code.zip";
        return createdFilePath;
    }
}
