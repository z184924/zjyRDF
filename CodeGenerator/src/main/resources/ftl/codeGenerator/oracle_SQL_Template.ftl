-- Create table
create table ${tableName}
(
<#if primaryKeyColumn.dataType == 'String'>
	${primaryKeyColumn.columnName} NVARCHAR2(32) NOT NULL,
<#else>
	${primaryKeyColumn.columnName} INTEGER NOT NULL,
</#if>
<#list columnList as column>
	<#if column.isPK == 'YES'>
	<#elseif column.dataType == 'String'>
	${column.columnName} NVARCHAR2(255) <#if column.isNullable == 'NO'>NOT NULL</#if>,
	<#elseif column.dataType == 'Number'>
	${column.columnName} INTEGER <#if column.isNullable == 'NO'>NOT NULL</#if>,
	<#elseif column.dataType == 'Boolean'>
	${column.columnName} NUMBER(1) <#if column.isNullable == 'NO'>NOT NULL</#if>,
	<#elseif column.dataType == 'DateTime'>
	${column.columnName} TIMESTAMP(6) <#if column.isNullable == 'NO'>NOT NULL</#if>,
	</#if>
</#list>
);
-- Add comments to the columns
<#list columnList as column>
comment on column ${tableName}.${column.columnName}
  is '${column.columnComment}';
</#list>
-- Create/Recreate primary, unique and foreign key constraints 
alter table ${tableName}
  add constraint ${tableName}_PK primary key (${primaryKeyColumn.columnName})