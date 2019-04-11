package cn.zhangjingyao;

import cn.zhangjingyao.util.toekn.FormTokenPool;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTests {

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void createPassword() {
        System.out.print(new BCryptPasswordEncoder().encode("admin"));
    }

    public String addFormTokenPool() {
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        String token = formTokenPool.addToken();
        logger.info("add:" + token + " - " + JSON.toJSON(formTokenPool));
        return token;
    }

    public Boolean removeFormTokenPool(String token) {
        FormTokenPool formTokenPool = FormTokenPool.getInstance();
        boolean b = formTokenPool.checkAndReomveToken(token);
        logger.info(b + ":" + token + " - " + JSON.toJSON(formTokenPool));
        return b;
    }

    @Test
    public void testFormToken() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(500);
        final CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 500; i++) {
            threadPool.execute(() -> {
                String s = addFormTokenPool();
                countDownLatch.countDown();
                threadPool.execute(() -> {
                    removeFormTokenPool(s);
                    countDownLatch.countDown();
                });
            });
        }
        long count = countDownLatch.getCount();
        System.out.println(count);
        countDownLatch.await();
        count = countDownLatch.getCount();
        System.out.println(count);
    }
}
