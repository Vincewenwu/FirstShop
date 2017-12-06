package me.ilt.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
          private static DruidDataSource dds=null;
          private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
          static {
        	  try{
        		  InputStream in=JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
        		  Properties prop = new Properties();
      			prop.load(in);
      			
      			dds=(DruidDataSource) DruidDataSourceFactory
      					.createDataSource(prop);
        	  }catch (Exception e) {
				// TODO: handle exception
        		  throw new ExceptionInInitializerError(e);
			}
          }
          public static DataSource getDataSource(){
      		return dds;
      	}


      	public static Connection getCon(){
      		
      		Connection conn = null;
      		try {
      			conn = dds.getConnection();
      		} catch (SQLException e) {
      			e.printStackTrace();
      		}
      		return conn;
      	}
      	
      	public static Connection getConnection(){
      			
      			Connection conn = threadLocal.get();
      			try {
      				if(conn==null){
      					conn = getDataSource().getConnection();
      					threadLocal.set(conn);
      				}
      			} catch (SQLException e) {
      				e.printStackTrace();
      			}
      			return conn;
      		}
      	/**
      	 * 开启线程
      	 */
      	public static void startTransaction(){
      		try {
      			Connection conn = threadLocal.get();
      			if(conn==null){
      				conn = getConnection();
      				threadLocal.set(conn);
      			}
      			conn.setAutoCommit(false);
      		} catch (Exception e) {
      			throw new RuntimeException(e);
      		}
      	}
      	
    	/**
      	 * 连接线程
      	 */
      	public static void commit(){
      		try {
      			Connection conn = threadLocal.get();
      			if(conn!=null){
      				conn.commit();
      			}
      		} catch (Exception e) {
      			throw new RuntimeException(e);
      		}
      	}
    	/**
      	 * 回滚
      	 */
      	public static void rollback(){
      		try {
      			Connection conn = threadLocal.get();
      			if(conn!=null){
      				conn.rollback();
      			}
      		} catch (Exception e) {
      			throw new RuntimeException(e);
      		}
      	}
      	
    	/**
      	 * 关闭线程
      	 */
      	public static void close(){
      		try {
      			Connection conn = threadLocal.get();
      			if(conn!=null){
      				conn.close();
      				threadLocal.remove();
      			}
      		} catch (Exception e) {
      			throw new RuntimeException(e);
      		}
      	}
      	
      	
      	
      	public static void main(String[] args) {
      		System.out.println(getCon());
      	}
      }

