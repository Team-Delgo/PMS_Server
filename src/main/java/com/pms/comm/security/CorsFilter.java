package com.pms.comm.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

   @Override
   public void init(FilterConfig filterConfig){
   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) res;

      response.setHeader("Access-Control-Allow-Origin", "*");
//      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Access-Control-Allow-Methods","*");
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Headers",
              "Origin, X-Requested-With, Content-Type, Accept, Authorization_Access, Authorization_Refresh");
      response.setHeader("Access-Control-Expose-Headers","Authorization_Access, Authorization_Refresh");

      if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
         response.setStatus(HttpServletResponse.SC_OK);
      }else {
         chain.doFilter(req, res);
      }
   }

   @Override
   public void destroy() {

   }
}