<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta name="viewport" content="width=device-width" />
		<title>列表</title>
		<meta charset="UTF-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="static/NIFine/bootstrap.min.css" rel="stylesheet" />
		<link href="static/NIFine/framework-font.css" rel="stylesheet" />
		<link href="static/NIFine/framework-theme.css?v=1.0" rel="stylesheet" />
		<link href="static/NIFine/jqgrid.css" rel="stylesheet" />
		<link href="static/NIFine/framework-ui.css" rel="stylesheet" />
		<link href="static/NIFine/dialog/dialog.css" rel="stylesheet" />
		<link href="static/NIFine/dialog/layer.css" rel="stylesheet" />
		<script src="static/NIFine/jquery-2.1.1.min.js"></script>
		<script src="static/NIFine/bootstrap.js"></script>
		<script src="static/NIFine/jqgrid.min.js"></script>
		<script src="static/NIFine/grid.locale-cn.js"></script>
		<script src="static/NIFine/datepicker/WdatePicker.js"></script>
		<script src="static/NIFine/framework-ui.js?v=1.0"></script>
	</head>
<body>
	<script type="text/javascript">
		$(function () {
	        gridList();
	    });
	    function gridList() {
	        var $gridList = $("#gridList");
	        $gridList.dataGrid({
	            url: "<%=basePath%>${objectNameFirstLower}/getGridListJson.do",
	            height: $(window).height() - 139,
	            colModel: [
	            	<#list fieldList as var>
							<#if var[3] == "是">
					{ label: '${var[2]}', name: '${var[0]}', width: 150, align: 'left'},
							</#if>
					</#list>
	            	{ label: "主键", name: "${primaryKey}", hidden: true, key: true }
	            ],
		        pager: "#gridPager",
	            viewrecords: true
	        });
		   $("#btn_search").click(function () {
	            $gridList.jqGrid('setGridParam', {
	                postData: { "${objectNameUpper}_NAME": $("#txt_keyword").val()},
	            }).trigger('reloadGrid');
	        });
	    }
	    
	    function btn_add() {
			$.modalOpen({
				id: "Form",
				title: "新增",
				url: "<%=basePath%>${objectNameFirstLower}/toAdd.do",
				width: "600px",
				height: "450px",
				callBack: function (iframeId) {
				    top.frames[iframeId].submitForm();
				}
	    	});
		}
		function btn_edit() {
		    if(!$("#gridList").jqGridRowValue().${primaryKey}){
		    	$.modalMsg("请选择数据", "warning");
		    }else{
		    	var ${primaryKey} = $("#gridList").jqGridRowValue().${primaryKey};
		     	$.modalOpen({
					id: "Form",
					title: "编辑",
					url: "<%=basePath%>${objectNameFirstLower}/toEdit.do?${primaryKey}=" + ${primaryKey},
					width: "600px",
					height: "450px",
					callBack: function (iframeId) {
					    top.frames[iframeId].submitForm();
					}
		     	});
		    }
		}
	    function btn_delete() {
	        if(!$("#gridList").jqGridRowValue().${primaryKey}){
	        	$.modalMsg("请选择数据", "warning");
	        }else{
	        	var ${primaryKey} = $("#gridList").jqGridRowValue().${primaryKey};
		        $.deleteForm({
		            url: "<%=basePath%>${objectNameFirstLower}/delete.do",
		            param: { ${primaryKey}: ${primaryKey} },
		            success: function () {
		            	$.currentWindow().$("#gridList").trigger("reloadGrid");
		            }
		        })
	        }
	    }
	    function btn_details() {
	    	if(!$("#gridList").jqGridRowValue().${primaryKey}){
	        	$.modalMsg("请选择数据", "warning");
	        }else{
	        	var ${primaryKey}=$("#gridList").jqGridRowValue().${primaryKey};
		        $.modalOpen({
		            id: "Form",
		            title: "查看",
		            url: "<%=basePath%>${objectNameFirstLower}/toDetail.do?${primaryKey}=" + ${primaryKey} ,
		            width: "600px",
		            height: "400px",
		            callBack: function (iframeId) {
		                top.frames[iframeId].submitForm();
		            },
		            btn:null
		        });
	        }
	    }
	</script>

	<div class="topPanel">
		<div class="toolbar">
			<div class="btn-group">
				<a class="btn btn-primary " onclick="$.reload()"><span class="glyphicon glyphicon-refresh"></span></a>
			</div>
			<div class="btn-group">           
	            <a id="NF-add" authorize="yes" class="btn btn-primary dropdown-text" onclick="btn_add()"><i class="fa fa-plus"></i>添加</a>
	        </div>
	        <div class="btn-group">           
	            <a id="NF-edit" authorize="yes" class="btn btn-primary dropdown-text" onclick="btn_edit()"><i class="fa fa-pencil-square-o"></i>编辑</a>
	        </div>
	        <div class="btn-group">           
	            <a id="NF-delete" authorize="yes" class="btn btn-primary dropdown-text" onclick="btn_delete()"><i class="fa fa-trash-o"></i>删除</a>
	        </div>
	        <div class="btn-group">           
	            <a id="NF-details" authorize="yes" class="btn btn-primary dropdown-text" onclick="btn_details()"><i class="fa fa-search-plus"></i>查看</a>
	        </div>
			<script>$('.toolbar').authorizeButton()</script>
		</div>
		<div class="search">
			<table>
				<tbody>
					<tr>
						<td>
							<div class="input-group">
								<input id="txt_keyword" type="text" class="form-control" placeholder="名称" style="width: 200px;">
								<span class="input-group-btn">
									<button id="btn_search" type="button" class="btn  btn-primary">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="gridPanel">
		<table id="gridList"></table>
		<div id="gridPager"></div>
	</div>
</body>
</html>

