package cn.zhangjingyao.util;

import java.util.HashMap;

/**
 * @author
 */
public class RightsCache extends HashMap <String,Integer>{
    public static RightsCache rightsCache;
    private RightsCache() {}
    public static synchronized RightsCache getInstance() {
        if(rightsCache==null) {
            rightsCache=new RightsCache();
        }
        return rightsCache;
    }
}
