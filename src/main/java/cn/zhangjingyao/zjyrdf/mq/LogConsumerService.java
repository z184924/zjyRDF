package cn.zhangjingyao.zjyrdf.mq;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.entity.log.SystemLog;
import cn.zhangjingyao.zjyrdf.service.log.SystemLogService;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author
 */
@Component
public class LogConsumerService {
    private Logger logger = LogManager.getLogger(this.getClass());
    private DefaultMQPushConsumer consumer = null;
    @Autowired
    private SystemLogService systemLogService;

    @PostConstruct
    public void initMQConsumer() {
        consumer = new DefaultMQPushConsumer("zjyrdf");
        consumer.setNamesrvAddr("localhost:9876");
        try {
            consumer.subscribe("TopicLog", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgList, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgList) {
                        String msgBody = new String(msg.getBody());
                        SystemLog systemLog = JSON.parseObject(msgBody, SystemLog.class);
                        logger.info("Message Received: " + JSON.toJSON(systemLog));
                        PageData systemLogPd = new PageData();
                        try {
                            systemLogPd.putEntity(systemLog, systemLog.getClass());
                            systemLogService.save(systemLogPd);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutDownConsumer() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }

}
