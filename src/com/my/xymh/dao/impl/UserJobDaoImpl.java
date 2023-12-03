package com.my.xymh.dao.impl;

import com.my.xymh.base.BaseDaoImpl;
import com.my.xymh.dao.UserJobDao;
import com.my.xymh.entity.UserJob;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public class UserJobDaoImpl extends BaseDaoImpl<UserJob> implements UserJobDao {

}
