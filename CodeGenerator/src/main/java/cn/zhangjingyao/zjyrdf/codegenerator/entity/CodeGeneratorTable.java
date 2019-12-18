package cn.zhangjingyao.zjyrdf.codegenerator.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class CodeGeneratorTable {
    private String tableName;
    private String packageName;
    private String className;
    private String objectName;
    private String classNameLowerWithMinus;
    private int columnListLength;
    private List<Map<String, Object>> columnList;
    private Map<String, Object> primaryKeyColumn;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className.substring(0, 1).toUpperCase() + className.substring(1);
        this.objectName = className.substring(0, 1).toLowerCase() + className.substring(1);
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < objectName.length(); i++) {
            char c = objectName.charAt(i);
            if (Character.isUpperCase(c)) {
                stringBuffer.append("-");
            }
            stringBuffer.append(Character.toLowerCase(c));
        }
        this.classNameLowerWithMinus = stringBuffer.toString();
    }

    public String getObjectName() {
        return objectName;
    }

    public String getClassNameLowerWithMinus() {
        return classNameLowerWithMinus;
    }

    public int getColumnListLength() {
        return columnListLength;
    }

    public void setColumnListLength(int columnListLength) {
        this.columnListLength = columnListLength;
    }

    public List<Map<String, Object>> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Map<String, Object>> columnList) {
        this.columnList = columnList;
    }

    public Map<String, Object> getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(Map<String, Object> primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableName);
        map.put("packageName", packageName);
        map.put("className", className);
        map.put("objectName", objectName);
        map.put("classNameLowerWithMinus", classNameLowerWithMinus);
        map.put("columnListLength", columnListLength);
        map.put("columnList", columnList);
        map.put("primaryKeyColumn", primaryKeyColumn);
        return map;
    }
}
