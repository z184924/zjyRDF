package cn.zhangjingyao.zjyrdf.codegenerator.dao;


import cn.zhangjingyao.zjyrdf.codegenerator.entity.PageData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Mapper
@Repository
public interface DbDao {
    /**
     * 获取数据库名列表
     *
     * @return
     */
    @Select("show databases")
    List<String> getDatabaseList();

    /**
     * 根据数据库名获取表名列表
     *
     * @param dataBaseName 数据库名
     * @return
     */
    @Select("select table_name from information_schema.tables where table_schema=#{dataBaseName}")
    List<String> getTableList(String dataBaseName);

    /**
     * 根据数据库名及表名获取列信息列表
     *
     * @param dataBaseName
     * @param tableName
     * @return
     */
    @Select("select column_name,data_type,is_nullable,column_key,column_comment,column_type from information_schema.columns where table_schema = #{dataBaseName} and table_name = #{tableName} order by ordinal_position")
    List<PageData> getTableColumnList(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName);

}
