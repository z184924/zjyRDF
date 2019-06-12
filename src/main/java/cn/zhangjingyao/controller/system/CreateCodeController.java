package cn.zhangjingyao.controller.system;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.Page;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/** 
 * 类名称：FreemarkerController
 * @author
 * @version
 */
@Controller
@RequestMapping(value="/createCode")
public class CreateCodeController extends BaseController {
	
	/**
	 * 跳转列表页
	 */
	@RequestMapping(value="/list")
	public String toList()throws Exception{
        return "html/createCode";
	}
	
	@RequestMapping(value="/submit",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String submit(HttpServletResponse response) throws Exception{
		PageData datapd = this.getPageData();
		PageData pd = new PageData();
		
		pd.put("packageName", datapd.getString("packageName"));	//包名
		pd.put("objectName", datapd.getString("objectName"));		//类名
		pd.put("tabletop", datapd.getString("tabletop"));			//表开头字段
		String fieldListData = datapd.getString("fieldListData");
		fieldListData=fieldListData.substring(0, fieldListData.length()-1);
		String[] fields=fieldListData.split("\\|");
		for (int i = 0; i < fields.length; i++) {
			pd.put("field"+i, fields[i]);
		}
		pd.put("zindex", fields.length+"");
		//筛选主键
		for (int i = 0; i < fields.length; i++) {
			String[] setting = fields[i].split(",");
			if("是".equals(setting[4])){
				pd.put("primaryKey",setting[0]);
			}
		}
		String file = createCode(pd);
		System.out.println("Generated file put on :"+file);
		return this.jsonContent("success", "生成成功");
	}
	
	
	/**
	 * 生成代码
	 */
	@RequestMapping(value="/proCode")
	public void proCode(HttpServletResponse response) throws Exception{
		PageData pd = this.getPageData();

		String fileName = createCode(pd);
		
		/*下载代码*/
		FileDownload.fileDownload(response, fileName, "code.zip");
		
	}

	public String createCode(PageData pd) throws Exception {
		String createdFilePath = null;
		/* ============================================================================================= */
		String packageName = pd.getString("packageName");  			//包名				========1
		String objectName = pd.getString("objectName");	   			//类名				========2
		String tabletop = pd.getString("tabletop");	   				//表前缀				========3
		tabletop = null == tabletop?"":tabletop.toUpperCase();		//表前缀转大写
		String zindext = pd.getString("zindex");	   	   			//属性总数
		int zindex = 0;
		if(null != zindext && !"".equals(zindext)){
			zindex = Integer.parseInt(zindext);
		}
		List<String[]> fieldList = new ArrayList<String[]>();   	//属性集合						========4
		for(int i=0; i< zindex; i++){
			fieldList.add(pd.getString("field"+i).split(","));	//属性放到集合里面
		}
		String primaryKey = pd.getString("primaryKey");	   				//主键			========5
		Map<String,Object> root = new HashMap<String,Object>(16);		//创建数据模型
		root.put("fieldList", fieldList);
		root.put("packageName", packageName);						//包名
		root.put("objectName", objectName);							//类名
		root.put("objectNameFirstUpper", objectName.substring(0, 1).toUpperCase() + objectName.substring(1));//类名(首字母大写)
		root.put("objectNameFirstLower", objectName.substring(0, 1).toLowerCase() + objectName.substring(1));//类名(首字母小写)
		root.put("objectNameLower", objectName.toLowerCase());		//类名(全小写)
		root.put("objectNameUpper", objectName.toUpperCase());		//类名(全大写)
		root.put("primaryKey", primaryKey);							//主键
		root.put("tabletop", tabletop);								//表前缀
		root.put("nowDate", new Date());							//当前日期
		
		DelAllFile.delFolder(PathUtil.getClasspath()+"static/file/createCode"); //生成代码前,先清空之前生成的代码
		/* ============================================================================================= */
		
		String filePath = "static/file/createCode/";						//存放路径
		String ftlPath = "createCode";								//ftl路径
		
		/*生成controller*/
		Freemarker.printFile("controllerTemplate.ftl", root, "controller/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Controller.java", filePath, ftlPath);
		
		/*生成service*/
		Freemarker.printFile("serviceTemplate.ftl", root, "service/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Service.java", filePath, ftlPath);

		/*生成mybatis xml*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/"+packageName+"/"+objectName+"Mapper.xml", filePath, ftlPath);
		
		/*生成SQL脚本*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql_script/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle_script/"+tabletop+objectName.toUpperCase()+".sql", filePath, ftlPath);
		
		/*生成jsp页面*/
//		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_list.jsp", filePath, ftlPath);
//		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName.toLowerCase()+"_edit.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/list.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/form.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_detail_Template.ftl", root, "jsp/"+packageName+"/"+objectName.toLowerCase()+"/detail.jsp", filePath, ftlPath);

		/*生成vue页面*/
		Freemarker.printFile("vue_list_Template.ftl", root, "vue/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"List.vue", filePath, ftlPath);
		Freemarker.printFile("vue_form_Template.ftl", root, "vue/"+packageName+"/"+objectName.toLowerCase()+"/"+objectName+"Form.vue", filePath, ftlPath);
	
		/*生成说明文档*/
		Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);
		
		//this.print("oracle_SQL_Template.ftl", root);  控制台打印
		
		/*生成的全部代码压缩成zip文件*/
		FileZip.zip(PathUtil.getClasspath()+"static/file/createCode", PathUtil.getClasspath()+"static/file/createCode/code.zip");
		createdFilePath =  PathUtil.getClasspath()+"static/file/createCode/code.zip";
		
		return createdFilePath;
	}
	
	public static void main(String[] args) {
		PageData pd = new PageData();
		pd.put("packageName", "test");	//包名
		pd.put("objectName", "Test");		//类名
		pd.put("tabletop", "TEST_");			//表开头字段
		
		/**
		 * fields
		 * "表字段名(主键ID字段必须为XXX_ID格式),数据类型(如String),中文注释,是否可修改,是否主键"
		 * 例:
		 * "USER_ID,String,用户ID,否,是",
		 * "USER_NAME,String,用户名称,是,否"
		 */
		String[] fields = new String[] {
				"TEST_ID,String,测试ID,否,是",
				"NAME,String,名称,是,否",
				"FILE_A,String,测试字段A,是,否",
				"FILE_B,Integer,测试字段B,是,否"
				};
				
		for (int i = 0; i < fields.length; i++) {
			pd.put("field"+i, fields[i]);
		}
		pd.put("zindex", fields.length+"");
		try {
			String file = new CreateCodeController().createCode(pd );
			System.out.println("Generated file put on :"+file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
