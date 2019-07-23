package com.project.springcloudzuul.ApiFilter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
    /*
     * filterType方法指定的是何时调用
     */
    @Override
    public String filterType() {
        //		pre：路由之前
        //		routing：路由之时
        //		post： 路由之后
        //		error：发送错误调用
        return "pre";
    }

    /*
     * filterOrder是有多个过滤器的时候如何排序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
     * shouldFilter可指定何时过滤
     * 这里可以写逻辑判断，是否要过滤，true为永远过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
     * run方法是具体逻辑
     * 过滤器的具体逻辑
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(request.getMethod() + "--" + request.getRequestURL().toString());
        Object username = request.getParameter("username");
        if (username != null && "YYF".equals(username.toString())) {
            ctx.setSendZuulResponse(false);
            try {
                ctx.getResponse().setContentType("text/html;charset=utf-8");
                ctx.getResponse().getWriter().write("监控到用户名是YYF");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
