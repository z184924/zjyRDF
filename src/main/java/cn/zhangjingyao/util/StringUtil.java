package cn.zhangjingyao.util;
/**
 * 字符串相关方法
 *
 * @author
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] strList(String valStr){
	    int i = 0;
	    String tempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - tempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
}
