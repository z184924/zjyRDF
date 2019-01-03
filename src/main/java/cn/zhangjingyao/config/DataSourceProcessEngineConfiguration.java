package cn.zhangjingyao.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.activiti.spring.boot.ActivitiProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceProcessEngineConfiguration extends AbstractProcessEngineAutoConfiguration {
    public DataSourceProcessEngineConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager transactionManager() throws Exception {
        Map<String,Object> properties=new HashMap<>();
        properties.put("driverClassName","com.mysql.jdbc.Driver");
        properties.put("url","jdbc:mysql://192.168.0.102:3306/test_activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=true");
        properties.put("username","root");
        properties.put("password","root");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(PlatformTransactionManager transactionManager, SpringAsyncExecutor springAsyncExecutor) throws Exception {
        Map<String,Object> properties=new HashMap<>();
        properties.put("driverClassName","com.mysql.jdbc.Driver");
        properties.put("url","jdbc:mysql://192.168.0.102:3306/test_activiti?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=true");
        properties.put("username","root");
        properties.put("password","root");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
    }
}
