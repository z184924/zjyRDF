package cn.zhangjingyao.zjyrdf.util.toekn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 表单Token池
 * 单例
 * redisTemplate属性为null时,使用内存存储,否则使用Redis存储
 * @author
 */
@Component
@EnableScheduling
public class FormTokenPool extends ConcurrentHashMap<String,Long> {

    private Logger logger= LogManager.getLogger(this.getClass());
    private static final long EXPIRY_TIME=60*60*1000;
    private static final String REDIS_KEY_TAG="form_token:";
    private static FormTokenPool formTokenPool;
    private RedisTemplate redisTemplate;

    private FormTokenPool() {}

    public static synchronized FormTokenPool getInstance() {
        if(formTokenPool==null) {
            formTokenPool=new FormTokenPool();
        }
        return formTokenPool;
    }

    public static synchronized FormTokenPool getInstance(RedisTemplate redisTemplate) {
        if(formTokenPool==null) {
            formTokenPool=new FormTokenPool();
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
            redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
            formTokenPool.redisTemplate = redisTemplate;
        }
        return formTokenPool;
    }

    public String addToken(String userId) {
        String uuid = this.get32UUID();
        if(redisTemplate != null){
            redisTemplate.opsForValue().set(REDIS_KEY_TAG+userId+":"+uuid,System.currentTimeMillis());
            redisTemplate.expire(REDIS_KEY_TAG+userId+":"+uuid, EXPIRY_TIME, TimeUnit.MILLISECONDS);
        }else {
            formTokenPool.put(userId+":"+uuid,System.currentTimeMillis());
        }
        return uuid;
    }

    public boolean checkToken(String userId,String uuid) {
        if(redisTemplate != null){
            Long createTime = (Long)redisTemplate.opsForValue().get(REDIS_KEY_TAG+userId+":"+uuid);
            if(createTime!=null){
                return true;
            }
        }else{
            Long createTime = formTokenPool.get(userId+":"+uuid);
            if(createTime!=null){
                return true;
            }
        }
        return false;
    }

    public boolean checkAndRemoveToken(String userId,String uuid) {
        if(redisTemplate != null){
            Long createTime = (Long)redisTemplate.opsForValue().get(REDIS_KEY_TAG+userId+":"+uuid);
            if(createTime!=null){
                redisTemplate.delete(REDIS_KEY_TAG+userId+":"+uuid);
                return true;
            }
        }else{
            Long createTime = formTokenPool.get(userId+":"+uuid);
            if(createTime!=null){
                formTokenPool.remove(userId+":"+uuid);
                return true;
            }
        }
        return false;
    }

    /**
     * 定时清理过期Token
     * @return
     */
    @Scheduled(cron = "0 0 3 * * *")
    public synchronized boolean cleanPool() {
        if(formTokenPool!=null) {
            logger.info("FormTokenPool Clean Start");
            Set<String> keySet = formTokenPool.keySet();
            for (String tokenStr : keySet) {
                Long createTime = formTokenPool.get(tokenStr);
                if(System.currentTimeMillis()>createTime+EXPIRY_TIME) {
                    formTokenPool.remove(tokenStr);
                }
            }
        }
        return true;
    }

    private String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
