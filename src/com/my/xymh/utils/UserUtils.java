package com.my.xymh.utils;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.my.xymh.entity.Manage;
import com.my.xymh.entity.User;



/**
 * 判断当前登陆角色和信息
 * @author 
 *
 */
public class UserUtils {
	@Autowired  
	private static HttpSession session;
	
	public static HttpSession getSession(){
		return session;
	}
	
	/**
	 * 获取当前登陆的用户
	 * @return
	 */
	public static User getUser(){
		//将用户信息放入session
		try {
			User user = (User)session.getAttribute("user");
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 获取当前登陆的管理员
	 * @return
	 */
	public static Manage getManage(){
		//将用户信息放入session
		try {
			Manage manage = (Manage)session.getAttribute("manage");
			return manage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获取当前登陆的id
	 * @return
	 */
	public static Integer getLoginId(){
		//将用户信息放入session
		try {
			Integer id = Integer.valueOf(session.getAttribute("userId").toString());
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 获取当前登陆的名字
	 * @return
	 */
	public static String  getLoginName(){
		//将用户信息放入session
		try {
			String name = session.getAttribute("userName").toString();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
