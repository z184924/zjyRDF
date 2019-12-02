package cn.zhangjingyao.annotation;

import java.lang.annotation.*;

/**
 * 系统日志
 * @author
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String value() default "";
}
