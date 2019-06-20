
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `${tabletop}${objectNameUpper}`
-- ----------------------------
DROP TABLE IF EXISTS `${tabletop}${objectNameUpper}`;
CREATE TABLE `${tabletop}${objectNameUpper}` (
	<#list fieldList as var>
		<#if var[4] == '是'>
		`${var[0]}` varchar(32) NOT NULL,
		<#elseif var[1] == 'String'>
		`${var[0]}` varchar(255) DEFAULT NULL COMMENT '${var[2]}',
		<#elseif var[1] == 'Number'>
		`${var[0]}` int(11) DEFAULT NULL COMMENT '${var[2]}',
		<#elseif var[1] == 'Boolean'>
		`${var[0]}` bit(1) DEFAULT NULL COMMENT '${var[2]}',
		<#elseif var[1] == 'DateTime'>
		`${var[0]}` datetime DEFAULT NULL COMMENT '${var[2]}',
		</#if>
	</#list>
  		PRIMARY KEY (`<#list fieldList as var><#if var[4] == '是'>${var[0]}</#if></#list>`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
