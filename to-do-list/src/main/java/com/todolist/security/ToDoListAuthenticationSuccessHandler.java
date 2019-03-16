package com.todolist.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 認証が成功した時の処理
 */
@Slf4j
public class ToDoListAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public ToDoListAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
    	System.out.println("onAuthenticationSuccess");
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }
        Enumeration<String> ess = request.getHeaders("Cache-Control");
        while(ess.hasMoreElements())
        	System.out.println("request.getHeaders(\"Cache-Control\") : " + ess.nextElement());
        ess = request.getHeaders("Referer");
        while(ess.hasMoreElements())
        	System.out.println("request.getHeaders(\"Referer\") : " + ess.nextElement());
        response.setStatus(HttpStatus.OK.value());
        //System.out.println("request.getRequestURI() : " + request.getRequestURI());
        System.out.println("request.getAttribute : " + request.getAttribute("javax.servlet.forward.request_uri"));
        Enumeration<String> keys = request.getAttributeNames();
        while(keys.hasMoreElements()) {
        	System.out.println(keys.nextElement());//nai
        }
        
        ToDoListLoginUser user = (ToDoListLoginUser)auth.getPrincipal();
        System.out.println("user - preUrl : " + user.getUser().getName());
        
        //for(String s : request.getAttributeNames())
        	//System.out.println("")
        response.sendRedirect(request.getContextPath());//　ログイン前のページに遷移したい。。。
        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored in the
     * session during the authentication process.
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
    	/**getSession : 
    	 * true to create a new session for this request ifnecessary; 
    	 * false to return null ifthere's no current session
    	 */
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
