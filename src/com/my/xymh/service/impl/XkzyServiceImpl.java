/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月19日 21时18分33秒
 */
package com.my.xymh.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.my.xymh.base.*;
import com.my.xymh.service.XkzyService;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月19日 21时18分33秒
 */

@Service
public class XkzyServiceImpl extends BaseServiceImpl<Xkzy> implements XkzyService{
	 
	@Autowired
	private XkzyDao xkzyDao;
	@Override
	public BaseDao<Xkzy> getBaseDao() {
		return xkzyDao;
	}

}
