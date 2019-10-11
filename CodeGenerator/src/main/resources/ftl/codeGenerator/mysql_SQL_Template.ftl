/*==============================================================*/
/* Table: ${tableName} */
/*==============================================================*/
DROP TABLE IF EXISTS `${tableName}`;
CREATE TABLE `${tableName}` (
		<#if primaryKeyColumn.dataType == 'String'>
		`${primaryKeyColumn.columnName}` varchar(32) NOT NULL comment '${primaryKeyColumn.columnComment}',
		<#else>
		`${primaryKeyColumn.columnName}` int unsigned NOT NULL auto_increment comment '${primaryKeyColumn.columnComment}',
		</#if>
	<#list columnList as column>
		<#if column.isPK == 'YES'>
		<#elseif column.dataType == 'String'>
		`${column.columnName}` varchar(255) <#if column.isNullable == 'NO'>NOT NULL</#if> COMMENT '${column.columnComment}',
		<#elseif column.dataType == 'Number'>
		`${column.columnName}` int(11) <#if column.isNullable == 'NO'>NOT NULL</#if> COMMENT '${column.columnComment}',
		<#elseif column.dataType == 'Boolean'>
		`${column.columnName}` bit(1) <#if column.isNullable == 'NO'>NOT NULL</#if> COMMENT '${column.columnComment}',
		<#elseif column.dataType == 'DateTime'>
		`${column.columnName}` datetime <#if column.isNullable == 'NO'>NOT NULL</#if> COMMENT '${column.columnComment}',
		</#if>
	</#list>
  		PRIMARY KEY (`${primaryKeyColumn.columnName}`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
