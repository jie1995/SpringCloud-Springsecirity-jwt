package com.microservice.cloud.gateway.deal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @description  系统启动时将资源和权限的对应信息关联起来
 * @author  weili
 */
@Slf4j
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 在Web服务器启动时，提取系统中的所有权限。
     * 先找出所有的资源url列表
     * 然后遍历每个资源url，循环执行如下操作
     * 根据匹配关系找到对应的角色列表
     * 将匹配的角色列表封装，存入map
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
     * 一定要加上@PostConstruct注解
     */
    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        //TODO reids存放对应url及其所需权限集合
        /*List<Resource> resourceList = resourceDao.findAll();
        for(Resource resource : resourceList) {
            List<Role> roles = roleResourceDao.findRolesByResourceUrl(resource.getId());
            String url = resource.getUrl();
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
            for(Role role:roles) {
                ConfigAttribute ca = new SecurityConfig(role.getName());
                atts.add(ca);
            }
            resourceMap.put(url, atts);
        }*/
        //需要验证的url放在这里
        ConfigAttribute ca = new SecurityConfig("ADMIN");
        Collection<ConfigAttribute> atts = new ArrayList<>();
        atts.add(ca);
        resourceMap.put("auth/sys1", atts);
    }

    /**
     * 根据URL，找到相关的权限配置
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        log.info("判断请求的url是否在待验证权限集合里...");
        // object 是一个URL，被用户请求的url
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        log.info("resourceMap:{}",resourceMap);
        Iterator<String> ite = resourceMap.keySet().iterator();
       /* while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            log.info("requestMatcher:{}",requestMatcher);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
       }
        log.info("发起的该请求任何人可以访问...");
        return null;*/
      //所有的请求都进入decide决策层...
       while (ite.hasNext()){
           String resURL = ite.next();
           return resourceMap.get(resURL);
       }
       return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}