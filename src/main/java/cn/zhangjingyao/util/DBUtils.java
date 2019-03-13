package cn.zhangjingyao.util;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class DBUtils {
	// 参数定义
	    private static String url = "jdbc:mysql://10.1.254.251:3366/dbadapter?useUnicode=true&characterEncoding=utf-8"; // 数据库地址
	    private static String username = "mas"; // 数据库用户名
	    private static String password = "JXmas@2012"; // 数据库密码
	
//    private static String url = "jdbc:mysql://223.223.200.115:3306/dbadapter?useUnicode=true&characterEncoding=utf-8"; // 数据库地址
//    private static String username = "sunhao"; // 数据库用户名
//    private static String password = "sunhao"; // 数据库密码
	
    private DBUtils() {

    }
    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载出错!");
        }
    }

    // 获得连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放连接
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // 关闭结果集
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // 关闭Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // 关闭连接
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }
    
    // 通过结果集元数据封装List结果集
    public static List<Map<String, Object>> read(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = ps.getMetaData();

            // 取得结果集列数
            int columnCount = rsmd.getColumnCount();


            // 构造泛型结果集
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            Map<String, Object> data = null;

            // 循环结果集
            while (rs.next()) {
                data = new HashMap<String, Object>();
                // 每循环一条将列名和列值存入Map
                for (int i = 1; i <= columnCount; i++) {
                    data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd
                            .getColumnLabel(i)));
                }
                // 将整条数据的Map存入到List中
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            DBUtils.free(rs, ps, conn);
        }
    }

    // 通过数据库元数据获得服务器信息
    public static void DbMeta() {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            DatabaseMetaData dbma = conn.getMetaData();
            System.out.println("----------数据库信息------------");
            System.out.println("数据库名称: " + dbma.getDatabaseProductName());
            System.out.println("驱动版本: " + dbma.getDriverVersion());
        } catch (Exception e) {
            //throw new RuntimeException();
        	System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String sql){
    	try {
    		   Connection con = DBUtils.getConnection();; //定义一个MYSQL链接对象
               Statement stmt; //创建声明
               stmt = con.createStatement();

               //新增一条数据
               stmt.executeUpdate(sql);
               ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
               int ret_id;
               if (res.next()) {
                   ret_id = res.getInt(1);
                   System.out.print(ret_id);
                   System.out.println("执行完了");
               }

		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    
	/**
	 * 
	 * @param phone 13689520370;13689520371
	 * @param content  短信内容
	 * @param date 2008-07-02 14:06:53
	 * @throws UnsupportedEncodingException
	 */
	public static void insert(String phone, String content) throws UnsupportedEncodingException {
		System.out.println(content);
		int length = 60;//每条短信的长度
		List<String> ret = new ArrayList<String>();
		while(length<content.length())
		{ ret.add(content.substring(0,length));
		  content = "【续】" +content.substring(length,content.length());
		}
		ret.add(content);
		System.out.println(ret.toString());
		
		//插入短信数据库
		for(String sentcontent: ret){
			String sql="Insert into sms_outbox (sismsid, extcode, destaddr, messagecontent,"
			        +" reqdeliveryreport,msgfmt,sendmethod,requesttime,applicationid)VALUES "
			        +"(UUID(), '88', '"+phone+"', '"+sentcontent+"', "
			        +"1, 15, 0, now(), 'wxythaqpt')";
	        DBUtils.insert(sql);
	        System.out.println("发送短信sql：" + sql);
	        try {
				Thread.currentThread();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
