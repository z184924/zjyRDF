package cn.zhangjingyao.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author
 */
public class WriteJsonUtil {

    public static boolean writeJson(HttpServletResponse response, String jsonString) {
        boolean flag = true;
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return flag;
    }

    public static boolean writeJson(HttpServletResponse response, Map jsonMap) {
        String jsonString = JSON.toJSONString(jsonMap);
        boolean flag = writeJson(response, jsonString);
        return flag;
    }

}
