/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 16时21分26秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.UserNoticeService;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 16时21分26秒
 */

@Service
public class UserNoticeServiceImpl extends BaseServiceImpl<UserNotice> implements UserNoticeService{
	 
	@Autowired
	private UserNoticeDao userNoticeDao;
	@Override
	public BaseDao<UserNotice> getBaseDao() {
		return userNoticeDao;
	}

}
