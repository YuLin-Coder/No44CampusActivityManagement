/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月19日 17时44分11秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.GzService;
import com.my.xymh.utils.MapUtils;
import com.my.xymh.utils.RCSCloudAPITest;

import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月19日 17时44分11秒
 */

@Service
public class GzServiceImpl extends BaseServiceImpl<Gz> implements GzService{
	 
	@Autowired
	private GzDao gzDao;
	private UserService UserService;
	@Override
	public BaseDao<Gz> getBaseDao() {
		return gzDao;
	}
	@Override
	public void send(int type,String title) {
		//根据类型查询
		Map<String, Object> map = MapUtils.getMap();
		map.put("type", type);
		List<Gz> list = gzDao.list(map);
		String y = "";
		if(type == 1){
			y = "新闻快讯";
		}
		if(type == 2){
			y = "通知公告";
		}
		if(type == 3){
			y = "活动列表";
		}
		if(type == 4){
			y = "校园风采";
		}
		if(!isEmpty(list)){
			for(Gz g:list){
				User load = g.getUser();
				String content="@1@="+load.getNickName();
				content+="||@2@="+y;
				content+="||@3@="+title;
				RCSCloudAPITest.sendTplSms("cbf541a8f9624068b046587bd6110756", load.getPhone(), content, null);
				
				
			}
			
		}
		
	}

}
