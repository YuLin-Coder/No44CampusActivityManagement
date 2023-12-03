package com.my.xymh.service;
import com.my.xymh.base.BaseService;
import com.my.xymh.entity.Gz;
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
public interface GzService extends BaseService<Gz>{

	void send(int i, String string);

}
