/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分55秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.NewsService;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 12时25分55秒
 */

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService{
	 
	@Autowired
	private NewsDao newsDao;
	@Override
	public BaseDao<News> getBaseDao() {
		return newsDao;
	}

}
