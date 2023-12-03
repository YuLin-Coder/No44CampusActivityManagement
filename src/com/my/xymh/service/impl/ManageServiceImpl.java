package com.my.xymh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.xymh.base.BaseDao;
import com.my.xymh.base.BaseServiceImpl;
import com.my.xymh.dao.ManageDao;
import com.my.xymh.entity.Manage;
import com.my.xymh.service.ManageService;
import com.my.xymh.utils.ReturnParam;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 */

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService{
	     @Autowired
	     private ManageDao manageDao;
		 @Override
		 public BaseDao<Manage> getBaseDao() {
			return manageDao;
		 }  
	     /* @Override
	      @Resource(name = "manageDao")  
	      public void setDao(BaseDao<Manage> dao) {  
	        super.setDao(manageDao);  
	     }  */
	/*@Override
	public ReturnParam checkUser(Manage manage) {
		// TODO Auto-generated method stub
		ReturnParam rp = new ReturnParam(); 
		Manage manages=manageDao.checkUser(manage);
		if(manages!=null){
			rp.setStatus(1);
			rp.setResult(manages);
		}else{
			rp.setStatus(2);
			rp.setDesc("用户名或密码错误");
		}
		return rp;
	}

	@Override
	public String text() {
		// TODO Auto-generated method stub
		return "辉";
	}

	@Override
	public ReturnParam selectMan(List<Integer> ids) {
		// TODO Auto-generated method stub
		ReturnParam rp = new ReturnParam(); 
		List<Manage> mas=manageDao.selectMan(ids);
		rp.setResult(mas);
		return rp;
	}

	@Override
	public ReturnParam selectManString(String str) {
		ReturnParam rp = new ReturnParam(); 
		List<Manage> mas=manageDao.selectManString(str);
		rp.setResult(mas);
		return rp;
	}

	@Override
	public ReturnParam insert(Manage manage) {
		ReturnParam rp = new ReturnParam();
		int id = 5;
		manageDao.insert(manage);
		if(id!=0){
			rp.setStatus(1);
			rp.setResult(id);
		}else{
			rp.setStatus(2);
			rp.setDesc("用户名或密码错误");
		}
		return rp;
	}*/
		@Override
		public void insertDepart() {
		}

}
