package com.alp;

import net.minidev.json.JSONObject;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiu on 11/12/17.
 */
@Order(1)
//重点
@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        String token  = servletRequest.getParameter("token");

        System.out.println("token is"+token);

        filterChain.doFilter(servletRequest,servletResponse);


//        if("xiuxiu123456".equals(token)){
//            filterChain.doFilter(servletRequest,servletResponse);
//        }else{
//            servletResponse.setContentType("application/json");
//            servletResponse.setCharacterEncoding("utf-8");
//            PrintWriter out = servletResponse.getWriter();
//
//            //create Json Object
//            JSONObject json = new JSONObject();
//            json.put("token", "token is invalid");
//            out.print(json.toString());
//            out.flush();
//            out.close();
//            return;
//        }
    }

    @Override
    public void destroy() {

    }
}

