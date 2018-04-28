package com.market.maicheng.common.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	public static SqlSessionFactory getSessionFactory(String resource) {  
        // 加载 mybatis 的配置文件（它也加载关联的映射文件）  
        Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        // 构建 sqlSession 的工厂  
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()  
                .build(reader);  
          
        return sessionFactory;  
    } 
}
