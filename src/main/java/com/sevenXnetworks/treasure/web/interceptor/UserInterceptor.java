package com.sevenXnetworks.treasure.web.interceptor;

import com.sevenXnetworks.treasure.dao.SecurityUserDao;
import com.sevenXnetworks.treasure.entity.SecurityUserEntity;
import com.sevenXnetworks.treasure.exception.BusinessException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserInterceptor extends HandlerInterceptorAdapter {
    public static Logger logger = Logger.getLogger(UserInterceptor.class);

    private static ThreadLocal<SecurityUserEntity> localUser = new ThreadLocal<>();

    private static ThreadLocal<CacheMode> localSecondLevelCache = new ThreadLocal<>();

    @Autowired
    private SecurityUserDao securityUserDao;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 解决跨域问题
        crossDomain(response);

        String requstURL = request.getRequestURL().toString();
        String access_token = request.getParameter("access_token");
        logger.info("requstURL:     " + requstURL);
        logger.info("access_token:     " + access_token);


        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new BusinessException(CustomerErrorConstants.TOKEN_AUTHENTICATION_ERROR);
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("username:     " + userDetails.getUsername());


        SecurityUserEntity userEntity = securityUserDao.findByName(userDetails.getUsername());
        if (userEntity == null) {
            throw new BusinessException(CustomerErrorConstants.TOKEN_AUTHENTICATION_ERROR);
        }

        localUser.set(userEntity);
        localSecondLevelCache.set(CacheMode.NORMAL);
        return true;
    }

    public static SecurityUserEntity getLocalUser() {
        return localUser.get();
    }

    public static ThreadLocal<CacheMode> getLocalSecondLevelCache() {
        return localSecondLevelCache;
    }

    // 跨域配置
    private void crossDomain(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "*");
//        response.addHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
    }
}