package com.market.maicheng.common.aop;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.market.maicheng.common.SysContext;

@Aspect
@Component
public class CheckLoginAop {
	
	
    @Before("@annotation(com.market.maicheng.common.annotation.CheckLogin)")
    public void beforeExec(JoinPoint joinPoint){
    	System.out.println("前");
    	try {
			SysContext.getResponse().sendRedirect("http://www.baidu.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
	
}
