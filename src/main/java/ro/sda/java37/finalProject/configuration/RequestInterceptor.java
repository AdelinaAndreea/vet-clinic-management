package ro.sda.java37.finalProject.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ro.sda.java37.finalProject.controller.AuthRestController.APP_TOKEN;

@Component
public class RequestInterceptor implements HandlerInterceptor {
  public final static String APP_TOKEN_KEY = "myTokenHeaderKey";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(APP_TOKEN_KEY);
    boolean authorized = APP_TOKEN.equals(token);
    if (authorized) {
      return true;
    } else {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      return false;
    }
  }
}
