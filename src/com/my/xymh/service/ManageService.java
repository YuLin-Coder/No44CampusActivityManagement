package com.my.xymh.service;

import java.util.List;

import com.my.xymh.base.BaseService;
import com.my.xymh.entity.Manage;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2016年06月04日 13时47分42秒
 */
public interface ManageService extends BaseService<Manage>{

	void insertDepart();

	/*ReturnParam checkUser(Manage manage);

	String text();

	ReturnParam selectMan(List<Integer> ids);

	ReturnParam selectManString(String str);

	ReturnParam insert(Manage manage);*/

}
