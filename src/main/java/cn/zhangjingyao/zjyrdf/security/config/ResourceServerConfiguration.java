package cn.zhangjingyao.zjyrdf.security.config;

import cn.zhangjingyao.zjyrdf.util.WriteJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final String []PERMIT_ALL_URI={"/static/**", "/error/**", "/oauth/**","/druid/**","/favicon.ico"};
    private final String []AUTHENTICATED_URI={"/role/listUserRights","/formToken","/user/editPassword"};
    @Autowired
    HttpServletRequest request;

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }


    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .expressionHandler(expressionHandler)
                .accessDeniedHandler(customAccessDeniedHandler())
                .authenticationEntryPoint(customAuthenticationEntryPoint())
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_URI)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(AUTHENTICATED_URI)
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .access("@rbacService.hasPermission(request,authentication) or hasRole('ROLE_admin')")
        ;
    }

    private class CustomAccessDeniedHandler implements AccessDeniedHandler{
        @Override
        public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            String message = e.getMessage();
            HashMap<String,Object> responseData=new HashMap<>(16);
            responseData.put("state","error");
            responseData.put("error",HttpStatus.FORBIDDEN.value());
            responseData.put("errorMessage",message);
            responseData.put("message", "该用户无权限访问:\""+httpServletRequest.getRequestURI()+"\"");
            WriteJsonUtil.writeJson(httpServletResponse, responseData);
        }
    }

    private class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
        @Override
        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            String message = e.getMessage();
            HashMap<String,Object> responseData=new HashMap<>(16);
            responseData.put("state","error");
            responseData.put("error",HttpStatus.UNAUTHORIZED.value());
            responseData.put("errorMessage",message);
            responseData.put("message","无效Token");
            WriteJsonUtil.writeJson(httpServletResponse, responseData);
        }
    }

}
