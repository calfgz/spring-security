package cn.calfgz.springboot.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhongwm
 * @description:
 * @date 2020-03-24 12:38
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }
        }
        return "访问的资源需要身分认证.";

    }

    /**
     *  private RequestCache requestCache = new HttpSessionRequestCache();
     *     private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
     *
     *     @GetMapping("/authentication/require")
     *     @ResponseStatus(HttpStatus.UNAUTHORIZED)
     *     public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
     *         SavedRequest savedRequest = requestCache.getRequest(request, response);
     *         if (savedRequest != null) {
     *             String targetUrl = savedRequest.getRedirectUrl();
     *             if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
     *                 redirectStrategy.sendRedirect(request, response, "/login.html");
     *         }
     *         return "访问的资源需要身份认证！";
     *     }
     */
}
