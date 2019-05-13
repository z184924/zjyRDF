<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${objectName}Mapper">
	
	
	<!-- 新增 -->
	<insert id="save" parameterType="PageData">
		insert into ${tabletop}${objectNameUpper}(
	<#list fieldList as var>
		<#if var[4] == "否">
			${var[0]},
		</#if>
	</#list>
	<#list fieldList as var>
		<#if var[4] == "是">
			${var[0]}
		</#if>
	</#list>
        ) values (
	<#list fieldList as var>
		<#if var[4] == "否">
			${r"#{"}${var[0]}${r"}"},
		</#if>
	</#list>
	<#list fieldList as var>
		<#if var[4] == "是">
			${r"#{"}${var[0]}${r"}"}
		</#if>
	</#list>
        )
    </insert>


    <!-- 删除 -->
    <delete id="delete" parameterType="PageData">
        delete from ${tabletop}${objectNameUpper}
        where
		<#list fieldList as var>
			<#if var[4] == "是">
			${var[0]}= ${r"#{"}${var[0]}${r"}"}
			</#if>
		</#list>
    </delete>


    <!-- 修改 -->
    <update id="edit" parameterType="PageData">
        update  ${tabletop}${objectNameUpper}
        set
	<#list fieldList as var>
		<#if var[3] == "是">
			${var[0]} = ${r"#{"}${var[0]}${r"}"},
		</#if>
	</#list>
        <#list fieldList as var>
			<#if var[4] == "是">
			${var[0]}= ${var[0]}
			</#if>
		</#list>
        where
        <#list fieldList as var>
			<#if var[4] == "是">
			${var[0]}= ${r"#{"}${var[0]}${r"}"}
			</#if>
		</#list>
    </update>


    <!-- 通过ID获取数据 -->
    <select id="findById" parameterType="PageData" resultType="PageData">
        select
	<#list fieldList as var>
		<#if var[4] == "否">
			a.${var[0]},
		</#if>
	</#list>
        <#list fieldList as var>
			<#if var[4] == "是">
			a.${var[0]}
			</#if>
		</#list>
        from
        	${tabletop}${objectNameUpper} a
        where
       <#list fieldList as var>
		   <#if var[4] == "是">
			a.${var[0]}= ${r"#{"}${var[0]}${r"}"}
		   </#if>
	   </#list>
    </select>


    <!-- 查询 -->
    <select id="datalistPage" parameterType="page" resultType="PageData">
        select
		<#list fieldList as var>
			<#if var[4] == "否">
			a.${var[0]},
			</#if>
		</#list>
        <#list fieldList as var>
			<#if var[4] == "是">
			a.${var[0]}
			</#if>
		</#list>
        from
        	${tabletop}${objectNameUpper} a
    </select>

    <!-- 查询(全部) -->
    <select id="listAll" parameterType="PageData" resultType="PageData">
        select
		<#list fieldList as var>
			<#if var[4] == "否">
			a.${var[0]},
			</#if>
		</#list>
        <#list fieldList as var>
			<#if var[4] == "是">
			a.${var[0]}
			</#if>
		</#list>
        from
        	${tabletop}${objectNameUpper} a
    </select>

    <!-- 批量删除 -->
    <delete id="deleteAll" parameterType="String">
        delete from ${tabletop}${objectNameUpper}
        where
        <#list fieldList as var>
			<#if var[4] == "是">
			a.${var[0]}
			</#if>
		</#list>
		in
        <foreach item="item" index="index" collection="array" open="(" separator="," close=")">
		${r"#{item}"}
        </foreach>
    </delete>

</mapper>