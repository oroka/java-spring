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
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(request.getContextPath());
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
