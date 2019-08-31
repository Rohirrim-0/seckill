package com.game.miaosha.config;

import com.game.miaosha.domain.MiaoshaUser;
import com.game.miaosha.service.MiaoshaUserService;
import com.game.miaosha.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by ls on 2019/8/29.
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //是该对象类型才执行解析
        Class<?> clazz = methodParameter.getParameterType();
        return clazz== MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest nativeRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse nativeResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = nativeRequest.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(nativeRequest,MiaoshaUserService.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return miaoshaUserService.getByToken(nativeResponse,token);
    }

    private String getCookieValue(HttpServletRequest nativeRequest, String cookieNameToken) {
        Cookie[] cookies = nativeRequest.getCookies();
        for (Cookie cookie:cookies
             ) {
            if (cookie.getName().equals(cookieNameToken)){
                return cookie.getValue();
            }

        }
        return null;
    }
}
