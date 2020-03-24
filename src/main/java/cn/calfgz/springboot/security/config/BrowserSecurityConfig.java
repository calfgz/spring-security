package cn.calfgz.springboot.security.config;

import cn.calfgz.springboot.security.handler.MyAuthenticationFailureHandler;
import cn.calfgz.springboot.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author calfgz
 * @description: Security config
 * @date 2020-03-24 11:51
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()    //表单方式
        //http.httpBasic()    //Http Basic 方式
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL
                //.loginPage("/login.html")
                //.loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()      //
                .authorizeRequests()    //授权配置
                .antMatchers("/authentication/require", "/login.html", "/css/login.css").permitAll()
                .anyRequest()           //所有请求
                .authenticated()        //都需要认证
                .and()
                .csrf().disable();

    }


    /**
     * @Configuration
     * public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
     *     @Override
     *     protected void configure(HttpSecurity http) throws Exception {
     *         http.formLogin() // 表单方式
     *                 .and()
     *                 .authorizeRequests() // 授权配置
     *                 .anyRequest()  // 所有请求
     *                 .authenticated(); // 都需要认证
     *     }
     *     http.formLogin() // 表单登录
     *             // http.httpBasic() // HTTP Basic
     *             .loginPage("/login.html")
     *             .loginProcessingUrl("/login")
     *             .and()
     *             .authorizeRequests() // 授权配置
     *             .antMatchers("/login.html").permitAll()
     *             .anyRequest()  // 所有请求
     *             .authenticated(); // 都需要认证
     * }
     */
}
