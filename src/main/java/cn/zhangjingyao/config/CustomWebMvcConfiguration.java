package cn.zhangjingyao.config;

import cn.zhangjingyao.interceptor.FormInterceptor;
import cn.zhangjingyao.interceptor.LogInterceptor;
import cn.zhangjingyao.resolver.CustomExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 自定义WebMvc配置类
 *
 * 说明:本类implements WebMvcConfigurer接口,不会覆盖@EnableAutoConfiguration中的设置
 * 如不想使用springBoot的@EnableAutoConfiguration中的设置,使用extends WebMvcConfigurationSupport将完全接管Mvc配置
 *
 * @author
 */
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {

    //在此处，将拦截器注册为一个 Bean 使拦截器可使用Autowired
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    public FormInterceptor formInterceptor() {
        return new FormInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(logInterceptor())
                .addPathPatterns("/**")
                .order(Ordered.LOWEST_PRECEDENCE);
        registry.addInterceptor(formInterceptor())
                .addPathPatterns("/**/save*")
                .addPathPatterns("/**/edit*")
                .addPathPatterns("/**/delete*")
                .addPathPatterns("/**/saveOrUpdate*")
                .order(Ordered.LOWEST_PRECEDENCE-1);
//        super.addInterceptors(registry);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        super.configureHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(new CustomExceptionResolver());
    }

}
