/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分56秒
 */
package com.my.xymh.service.impl;

import com.my.xymh.base.BaseDao;
import com.my.xymh.base.BaseServiceImpl;
import com.my.xymh.dao.SchoolJobDao;
import com.my.xymh.dao.UserJobDao;
import com.my.xymh.entity.SchoolJob;
import com.my.xymh.entity.UserJob;
import com.my.xymh.service.SchoolJobService;
import com.my.xymh.service.UserJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分56秒
 */

@Service
public class UserJobServiceImpl extends BaseServiceImpl<UserJob> implements UserJobService {
	 
	@Autowired
	private UserJobDao userJobDao;
	@Override
	public BaseDao<UserJob> getBaseDao() {
		return userJobDao;
	}

}
