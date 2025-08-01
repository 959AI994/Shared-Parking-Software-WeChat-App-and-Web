package com.lbk.sharper.wx.interceptor;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.util.JwtUtil;
import com.lbk.sharper.wx.annotation.Login;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    public static final String USER_KEY = "openId";

    @Override
    //HttpServletRequest 是 Java Servlet API 中的一个接口，用于处理 HTTP 请求。
    //当一个客户端（例如，浏览器）向服务器发送一个 HTTP 请求时，服务器会将这个请求封装为一个 HttpServletRequest 对象，然后传递给后端应用程序。
    //这个 HttpServletRequest 对象包含了所有关于这个 HTTP 请求的信息，比如请求头（Headers）、请求参数（Parameters）、Cookies、HTTP 方法（如 GET 或 POST）、请求 URI 等等。
    //开发者就可以使用 HttpServletRequest 对象中的方法来获取和处理请求中的所有信息。
    // 可以使用 request.getHeader("Token") 来获取请求头中的 "Token" 值，或者使用 request.getParameter("Token") 来获取请求参数中的 "Token" 值。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if(handler instanceof HandlerMethod) { //检查传入的处理器是否是 HandlerMethod 类型。如果不是，它就直接放行请求。
            //获取处理器方法上的 @Login 注解。如果这个注解不存在，拦截器也会放行请求。
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }
        //获取用户凭证
        String token = request.getHeader("Token");
        //获取请求头中的 "Token"，如果没有，则尝试从请求参数中获取。
        // 如果找不到 "Token"，则会抛出一个 CustomException 异常。
        if (token == null) {
            token = request.getParameter("Token");
        }
        //凭证为空
        if (token == null) {
            throw new CustomException("token不能为空");
        }

        String openId = JwtUtil.getUsername(token);
        if (openId == null || !JwtUtil.verify(token, openId)) {
            throw new CustomException("token认证失败！");
        }


        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, openId);

        return true;
    }
}
