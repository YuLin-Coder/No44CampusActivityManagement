/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 23时46分29秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.XlService;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月18日 23时46分29秒
 */

@Service
public class XlServiceImpl extends BaseServiceImpl<Xl> implements XlService{
	 
	@Autowired
	private XlDao xlDao;
	@Override
	public BaseDao<Xl> getBaseDao() {
		return xlDao;
	}

}
