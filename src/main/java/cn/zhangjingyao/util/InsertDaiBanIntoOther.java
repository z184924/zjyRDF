package cn.zhangjingyao.util;

import cn.zhangjingyao.entity.PageData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

/** 
 * @author : Gavin 
 * @date :2016年12月24日
 * @version 1.0 
 * @function
 */
public class InsertDaiBanIntoOther {
	private static Logger logger = Logger.getLogger(InsertDaiBanIntoOther.class);
	
	
	public static String insertData(List<PageData> list){
		String msg = "";
		    Connection con = null;// 创建一个数据库连接
		    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		    try
		    {
		        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
		        logger.info("开始尝试连接数据库！");
		        String url = "jdbc:oracle:thin:@10.1.12.25:1521:eptdb";//
		        String user = "wxyth_az";// 用户名,系统默认的账户名
		        String password = "wxyth_az";// 你安装时选设置的密码
		        con = DriverManager.getConnection(url, user, password);// 获取连接
		        logger.info("连接成功！");
		        
		        for(PageData pd : list){
		        	String sql = "insert into SQ_TODO_INFO_T(ID,APP_URL,NAME,CREATE_TIME,PROC_INSTANCE_ID,USER_ID,CURRENTSTATE,WF_TYPE,FLAG) VALUES(?,?,?,?,?,?,?,?,?)";// 预编译语句，“？”代表参数
		        	pre = con.prepareStatement(sql);// 实例化预编译语句
		        	pre.setString(1, pd.getString("ID"));// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
		        	pre.setString(2, pd.getString("APP_URL"));
		        	pre.setString(3, pd.getString("NAME"));
		        	Timestamp create_time=(Timestamp) pd.get("CREATE_TIME");
		        	pre.setTimestamp(4,create_time);		
		        	pre.setString(5, pd.getString("PROC_INSTANCE_ID"));
		        	pre.setString(6, pd.getString("USER_ID"));
		        	//String CURRENTSTATE=  pd.getString("CURRENTSTATE");
		        	//int state=Integer.parseInt(CURRENTSTATE);
		        	pre.setInt(7, pd.getInt("CURRENTSTATE"));
		        	pre.setString(8, pd.getString("WF_TYPE"));
		        	//String FLAG = pd.getString("FLAG");
		        	//int flagNum = Integer.parseInt(FLAG);
		        	pre.setInt(9, pd.getInt("FLAG"));
		        	int status = pre.executeUpdate();// 执行插入
		        }
		        msg = "succ";
		    }
		    catch (Exception e)
		    {
		    	 msg = "fail";
		        e.printStackTrace();
		    }
		    finally
		    {
		        try
		        {
		            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
		            // 注意关闭的顺序，最后使用的最先关闭
		            if (pre != null) {
                        pre.close();
                    }
		            if (con != null) {
                        con.close();
                    }
		            logger.info("数据库连接已关闭！");
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		    }
		    return msg;
	}
	
	   
}

