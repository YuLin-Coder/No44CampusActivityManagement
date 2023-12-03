/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分56秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.WebCountService;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分56秒
 */

@Service
public class WebCountServiceImpl extends BaseServiceImpl<WebCount> implements WebCountService{
	 
	@Autowired
	private WebCountDao webCountDao;
	@Override
	public BaseDao<WebCount> getBaseDao() {
		return webCountDao;
	}

}
