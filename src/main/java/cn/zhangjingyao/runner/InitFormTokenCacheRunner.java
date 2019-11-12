package cn.zhangjingyao.runner;

import cn.zhangjingyao.util.toekn.FormTokenPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 表单Token启动缓存
 *
 * @author
 */
@Component
public class InitFormTokenCacheRunner implements ApplicationRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        FormTokenPool.getInstance(redisTemplate);
    }

}
